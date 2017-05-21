package com.cjburkey.server.modules;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.cjburkey.server.ChatUtil;
import com.cjburkey.server.MainServer;
import com.cjburkey.server.cmd.CommandHandler;
import com.cjburkey.server.cmds.CmdTpDeny;
import com.cjburkey.server.cmds.CmdTpa;
import com.cjburkey.server.cmds.CmdTpaccept;
import com.cjburkey.server.data.ModuleDataHandler;
import com.cjburkey.server.module.Module;
import com.cjburkey.server.teleport.TeleportRequest;

public class ModuleTeleport extends Module {
	
	private ModuleDataHandler data;
	private CommandHandler cmds;
	private List<TeleportRequest> requests;
	private CmdTpa cmdTpa;
	private CmdTpaccept cmdTpaccept;
	private CmdTpDeny cmdTpDeny;
	
	public void addRequest(Player from, Player to) {
		removeRequest(to);
		TeleportRequest tpr = new TeleportRequest(from, to);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MainServer.instance, () -> removeRequest(tpr), 20 * MainServer.instance.getConfig().getInt("tpTimeout"));
		requests.add(tpr);
		ChatUtil.send(from, "&2Request sent.");
		ChatUtil.send(to, "&2" + from.getDisplayName() + " has requested to teleport to you. Use &l&3/tpaccept&r&2 to allow them.");
	}
	
	public void acceptRequest(Player to) {
		TeleportRequest req = removeRequest(to);
		if(req == null) { ChatUtil.send(to, "&4You have no pending teleport requests."); return; }
		boolean worked = req.doTeleport();
		ChatUtil.send(to, (worked) ? "&2Request accepted." : "&4That player is offline."); return;
	}
	
	public void decline(Player to) {
		TeleportRequest req = removeRequest(to);
		if(req == null) { ChatUtil.send(to, "&4You have no pending teleport requests."); return; }
		ChatUtil.send(to, "&2Request declined."); return;
	}
	
	public TeleportRequest removeRequest(Player to) {
		for(int i = 0; i < requests.size(); i ++) {
			if(requests.get(i).getTo().equals(to.getUniqueId())) return requests.remove(i);
		}
		return null;
	}
	
	public void removeRequest(TeleportRequest tpr) {
		requests.remove(tpr);
	}
	
	public String getName() {
		return "ModuleTeleport";
	}

	public Module[] getRequiredModules() {
		return new Module[] {  };
	}

	public ModuleDataHandler getDataHandler() {
		return data;
	}

	public CommandHandler getCommandHandler() {
		return cmds;
	}

	public void onLoad() {
		data = new ModuleDataHandler(this);
		cmds = new CommandHandler();
		requests = new ArrayList<>();
		cmdTpa = new CmdTpa();
		cmdTpaccept = new CmdTpaccept();
		cmdTpDeny = new CmdTpDeny();
		
		cmds.addCmd(cmdTpa);
		cmds.addCmd(cmdTpaccept);
		cmds.addCmd(cmdTpDeny);
		
		data.loadFromDisk();
	}

	public void onUnload() {
		data.saveToDisk();
	}
	
}