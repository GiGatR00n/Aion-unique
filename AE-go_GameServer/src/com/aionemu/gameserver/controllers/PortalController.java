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
package com.aionemu.gameserver.controllers;

import java.util.Collection;
import java.util.Collections;

import com.aionemu.gameserver.configs.main.CustomConfig;
import com.aionemu.gameserver.dataholders.PortalData;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.group.PlayerGroup;
import com.aionemu.gameserver.model.templates.portal.PortalTemplate;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.network.aion.serverpackets.SM_USE_OBJECT;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.WorldMapInstance;
import com.google.inject.Inject;

/**
 * @author ATracer
 *
 */
public class PortalController extends NpcController
{
	@Inject
	private PortalData portalData;
	
	private PortalTemplate portalTemplate;
	
	@Override
	public void setOwner(Creature owner)
	{
		super.setOwner(owner);
		portalTemplate = portalData.getPortalTemplate(owner.getObjectTemplate().getTemplateId());
	}



	@Override
	public void onDialogRequest(Player player)
	{
		if(portalTemplate == null)
			return;
		
		if(portalTemplate.isInstance() && CustomConfig.ENABLE_INSTANCES)
			portGroup(player);
		else
			port(player);
	}



	/**
	 * @param player
	 */
	private void port(Player player)
	{
		Collection<Player> players = Collections.singletonList(player);
		if(!checkPlayersLevel(players))
			return;
		
		transfer(player, players);
	}


	/**
	 * @param player
	 */
	private void portGroup(Player player)
	{
		PlayerGroup group = player.getPlayerGroup();
		if(group == null || group.getGroupLeader().getObjectId() != player.getObjectId())
			return;
		
		Collection<Player> players = group.getMembers();
		if(!checkPlayersLevel(players))
			return;
		
		transfer(player, players);		
	}

	/**
	 * @param players
	 */
	private void transfer(final Player leader, final Collection<Player> players)
	{
		final int defaultUseTime = 3000;
		PacketSendUtility.sendPacket(leader, new SM_USE_OBJECT(leader.getObjectId(), 
			getOwner().getObjectId(), defaultUseTime, 1));
		PacketSendUtility.broadcastPacket(leader, new SM_EMOTION(leader, 37, 0, getOwner().getObjectId()), true);
		
		ThreadPoolManager.getInstance().schedule(new Runnable(){
			@Override
			public void run()
			{
				PacketSendUtility.sendPacket(leader, new SM_USE_OBJECT(leader.getObjectId(), 
					getOwner().getObjectId(), defaultUseTime, 0));
				
				WorldMapInstance newInstance = sp.getWorld().getNextAvailableInstance(portalTemplate.getMapId());
				newInstance.setDestroyTime(60 * 30);
				
				for(Player player : players)
				{
					sp.getTeleportService().teleportTo(player, portalTemplate.getMapId(), newInstance.getInstanceId(),
						portalTemplate.getX(), portalTemplate.getY(), portalTemplate.getZ(), 0);
				}
			}
		}, defaultUseTime);
	}

	
	/**
	 * 
	 * @param players
	 * @return
	 */
	private boolean checkPlayersLevel(Collection<Player> players)
	{
		int minLevel = portalTemplate.getMinLevel();
		int maxLevel = portalTemplate.getMaxLevel();
		
		for(Player player : players)
		{
			int playerLevel = player.getLevel();
			if(playerLevel > maxLevel || playerLevel < minLevel)
				return false;
		}
		return true;
	}
	
	
}