package admincommands;

import com.aionemu.gameserver.model.PlayerClass;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_TITLE_SET;
import com.aionemu.gameserver.network.aion.serverpackets.SM_TITLE_UPDATE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.AdminCommand;

import java.util.Arrays;

/**
 * @author Nemiroff, ATracer, IceReaper
 *         Date: 11.12.2009
 */
public class Set extends AdminCommand {

    public Set() {
        super("set");
    }

    /*
	 *  (non-Javadoc)
	 * @see com.aionemu.gameserver.utils.chathandlers.admincommands.AdminCommand#executeCommand(com.aionemu.gameserver.gameobjects.player.Player, java.lang.String[])
	 */

    @Override
    public void executeCommand(Player admin, String[] params) {


        if (params == null || params.length < 1)
        {
            PacketSendUtility.sendMessage(admin, "syntax //set <class|exp|level|title>");
            return;
        }

        Player target = null;
        VisibleObject creature = admin.getTarget();

        if (admin.getTarget() instanceof Player)
        {
            target = (Player) creature;
        }

        if (target == null)
        {
            PacketSendUtility.sendMessage(admin, "You should select a target first!");
            return;
        }

        if (params[1] == null)
        {
            PacketSendUtility.sendMessage(admin, "You should enter second params!");
            return;
        }
        String paramValue = params[1];

        if (params[0].equals("class"))
        {
            byte newClass;
            try
            {
                newClass = Byte.parseByte(paramValue);
            }
            catch (NumberFormatException e)
            {
                PacketSendUtility.sendMessage(admin, "You should enter valid second params!");
                return;
            }

            if (target instanceof Player)
            {
                Player player = (Player) target;
                PlayerClass oldClass = ((Player) target).getPlayerClass();
                setClass(player, oldClass, newClass);
                PacketSendUtility.sendMessage(admin, "You have success switch class");
            }
        }
        else if (params[0].equals("exp"))
        {
            long exp;
            try
            {
                exp = Long.parseLong(paramValue);
            }
            catch (NumberFormatException e)
            {
                PacketSendUtility.sendMessage(admin, "You should enter valid second params!");
                return;
            }

            if (target instanceof Player)
            {
                Player player = (Player) target;
                player.getCommonData().setExp(exp);
                PacketSendUtility.sendMessage(admin, "Set your exp to " + paramValue);
            }

        }
        else if (params[0].equals("level"))
        {
            int level;
            try
            {
                level = Integer.parseInt(paramValue);
            }
            catch (NumberFormatException e)
            {
                PacketSendUtility.sendMessage(admin, "You should enter valid second params!");
                return;
            }

            if (target instanceof Player)
            {
                Player player = (Player) target;
                if (level <= 51)
                    player.getCommonData().setLevel(level);
                PacketSendUtility.sendMessage(admin, "Set target level to " + level);
            }
        }
        else if (params[0].equals("title"))
        {
            int titleId;
            try
            {
                titleId = Integer.parseInt(paramValue);
            }
            catch (NumberFormatException e)
            {
                PacketSendUtility.sendMessage(admin, "You should enter valid second params!");
                return;
            }

            if (target instanceof Player)
            {

                Player player = (Player) target;
                if (titleId <= 106)
                    setTitle(player, titleId);
                PacketSendUtility.sendMessage(admin, "Set title to " + titleId);
            }
        }
    }

    private void setTitle(Player player, int value) {
        PacketSendUtility.sendPacket(player, new SM_TITLE_SET(value));
        PacketSendUtility.broadcastPacket(player, (new SM_TITLE_UPDATE(player, value)));
        player.getCommonData().setTitleId(value);
    }

    private void setClass(Player player, PlayerClass oldClass, byte value) {
        PlayerClass playerClass = PlayerClass.getPlayerClassById(value);
        int level = player.getLevel();
        if (level < 9)
        {
            PacketSendUtility.sendMessage(player, "You can switch class after your level reach 9");
            return;
        }
        if (Arrays.asList(new int[]{1, 2, 4, 5, 7, 8, 10, 11}).contains(oldClass.ordinal()))
        {
            PacketSendUtility.sendMessage(player, "You already switched class");
            return;
        }
        int newClassId = playerClass.ordinal();
        switch (oldClass.ordinal())
        {
            case 0:
                if (newClassId == 1 || newClassId == 2)
                    break;
            case 3:
                if (newClassId == 4 || newClassId == 5)
                    break;
            case 6:
                if (newClassId == 7 || newClassId == 8)
                    break;
            case 9:
                if (newClassId == 10 || newClassId == 11)
                    break;
            default:
                PacketSendUtility.sendMessage(player, "Invalid class switch chosen");
                return;
        }
        player.getCommonData().setPlayerClass(playerClass);
        player.getCommonData().upgradePlayer();
    }
}