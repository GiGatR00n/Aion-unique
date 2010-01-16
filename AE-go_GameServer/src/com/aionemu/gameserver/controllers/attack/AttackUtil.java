/*
 * This file is part of aion-unique <aion-unique.org>.
 *
 *  aion-unique is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-unique is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.controllers.attack;

import java.util.ArrayList;
import java.util.List;

import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.model.SkillElement;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.stats.CreatureGameStats;
import com.aionemu.gameserver.model.gameobjects.stats.StatEnum;
import com.aionemu.gameserver.utils.stats.StatFunctions;

/**
 * @author ATracer
 * 
 * Probably this is a temporary class for attack calculation
 * cause i need it during refactoring
 */
public class AttackUtil
{

	/**
	 *  TODO: 1) diff between physical and magical attacks - diff status
	 *  
	 *  
	 * @param attacker
	 * @param attacked
	 * @return
	 */
	public static List<AttackResult> calculateAttackResult(Creature attacker, Creature attacked)
	{
		int damage = StatFunctions.calculateBaseDamageToTarget(attacker, attacked);
		CreatureGameStats<?> gameStats = attacker.getGameStats();

		int hitCount = Rnd.get(1,gameStats.getCurrentStat(StatEnum.MAIN_HAND_HITS) + gameStats.getCurrentStat(StatEnum.OFF_HAND_HITS));

		List<AttackResult> attackList = new ArrayList<AttackResult>();

		for (int i=0; (i<hitCount); i++) 
		{
			int damages;
			if (i==0)
			{
				damages = Math.round(damage*0.75f);
			}
			else
			{
				damages = Math.round(damage/(hitCount-1));
			}
			damage -= damages;

			AttackStatus status = calculatePhysicalStatus(attacker, attacked);
			//TODO this is very basic calcs, for initial testing only
			switch(status)
			{
				case BLOCK:
					int shieldDamageReduce = ((Player)attacked).getGameStats().getCurrentStat(StatEnum.DAMAGE_REDUCE);
					damages -= Math.round((damages * shieldDamageReduce) / 100);
					break;
				case DODGE:
					damages = 0;
					break;
				case CRITICAL:
					damages *= 2;
					break;
				case PARRY:
					damages *= 0.5;
					break;
				default:
					break;
			}
			attackList.add(new AttackResult(damages, status));
		}
		return attackList;
	}

	public static SkillAttackResult calculatePhysicalSkillAttackResult(Creature attacker, Creature attacked, int skillDamage)
	{
		int damage = StatFunctions.calculatePhysicDamageToTarget(attacker, attacked, skillDamage);

		AttackStatus status = calculatePhysicalStatus(attacker, attacked);

		switch(status)
		{
			case BLOCK:
				int shieldDamageReduce = ((Player)attacked).getGameStats().getCurrentStat(StatEnum.DAMAGE_REDUCE);
				damage -= Math.round((damage * shieldDamageReduce) / 100);
				break;
			case DODGE:
				damage = 0;
				break;
			case CRITICAL:
				damage *= 2;
				break;
			case PARRY:
				damage *= 0.5;
				break;
			default:
				break;
		}
		SkillAttackResult skillAttackResult = new SkillAttackResult(attacked, damage, status);

		return skillAttackResult;
	}


	public static SkillAttackResult calculateMagicalSkillAttackResult(Creature attacker, Creature attacked, int skillDamage, SkillElement element)
	{
		int damage = StatFunctions.calculateMagicDamageToTarget(attacker, attacked, skillDamage, element);  //TODO SkillElement

		AttackStatus status = calculateMagicalStatus(attacker, attacked);

		switch(status)
		{
			case RESIST:
				damage = 0;
				break;
			default:
				break;
		}

		SkillAttackResult skillAttackResult = new SkillAttackResult(attacked, damage, status);

		return skillAttackResult;
	}

	/**
	 * Manage attack status rate
	 * @see http://www.aionsource.com/forum/mechanic-analysis/42597-character-stats-xp-dp-origin-gerbator-team-july-2009-a.html
	 * @return
	 */
	public static AttackStatus calculatePhysicalStatus(Creature attacker, Creature attacked)
	{		
		if( Rnd.get( 0, 100 ) < StatFunctions.calculatePhysicalDodgeRate(attacker, attacked) ) 
		{
			return AttackStatus.DODGE;
		}

		if( Rnd.get( 0, 100 ) < StatFunctions.calculatePhysicalParryRate(attacker, attacked) ) {
			return AttackStatus.PARRY;
		}

		if( ( attacked instanceof Player && ((Player) attacked).getInventory().isShieldEquipped() ) ) 
		{
			if( Rnd.get( 0, 100 ) < StatFunctions.calculatePhysicalBlockRate(attacker, attacked) )
			{
				return AttackStatus.BLOCK;
			}
		}

		if( Rnd.get( 0, 100 ) < StatFunctions.calculatePhysicalCriticalRate(attacker) ) 
		{
			return AttackStatus.CRITICAL;
		}

		return AttackStatus.NORMALHIT;
	}


	public static AttackStatus calculateMagicalStatus(Creature attacker, Creature attacked)
	{
		if(Rnd.get( 0, 100 ) < StatFunctions.calculateMagicalResistRate(attacker, attacked))
			return AttackStatus.RESIST;

		return AttackStatus.NORMALHIT;
	}
}

