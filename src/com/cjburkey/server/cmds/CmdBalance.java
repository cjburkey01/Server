package com.cjburkey.server.cmds;

import java.util.UUID;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.cjburkey.server.ChatUtil;
import com.cjburkey.server.cmd.Cmd;
import com.cjburkey.server.module.Modules;
import com.cjburkey.server.modules.ModuleCurrency;

public class CmdBalance extends Cmd {

	public String getName() {
		return "balance";
	}

	public void onCall(CommandSender executor, String[] args) {
		if(executor instanceof Player) player((Player) executor, args);
		else console(executor, args);
	}
	
	private void console(CommandSender executor, String[] args) {
		if(args.length != 1) {
			ChatUtil.send(executor, "&4Usage: /balance <player>");
			return;
		}
		sendOther(executor, args[0]);
	}
	
	private void player(Player player, String[] args) {
		if(args.length == 1) {
			if(args[0].trim().equals(player.getDisplayName().trim())) sendSelf(player);
			else sendOther(player, args[0].trim());
		} else if(args.length == 0) sendSelf(player);
		else ChatUtil.send(player, "&4Usage: /balance [player]");
	}
	
	private void sendSelf(Player ply) {
		ChatUtil.send(ply, "&2You have " + ModuleCurrency.stylize(Modules.getModuleCurrency().getMoney(ply.getUniqueId())) + " in your account.");
	}
	
	private void sendOther(CommandSender executor, String name) {
		UUID id = Modules.getModuleCacher().fromName(name);
		if(id != null) {
			ChatUtil.send(executor, "&2That player has " + ModuleCurrency.stylize(Modules.getModuleCurrency().getMoney(id)) + " in their account.");
			return;
		}
		ChatUtil.send(executor, "&4That player has not joined the server.");
	}
	
}