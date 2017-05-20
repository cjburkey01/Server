package com.cjburkey.server.cmd;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.server.Logger;
import com.cjburkey.server.MainServer;

public class CommandHandler {
	
	private List<Cmd> cmds;
	
	public CommandHandler() {
		cmds = new ArrayList<>();
	}
	
	public void addCmd(Cmd cmd) {
		cmds.add(cmd);
	}
	
	public boolean hasCommand(String cmd) {
		return fromName(cmd) != null;
	}
	
	public Cmd fromName(String cmd) {
		for(Cmd c : cmds) {
			if(c.getName().equals(cmd)) return c;
		}
		return null;
	}
	
	public void register() {
		for(Cmd cmd : cmds) {
			MainServer.instance.getCommand(cmd.getName()).setExecutor(new CmdExecutor(cmd));
			Logger.log("Registered cmd: " + cmd.getName());
		}
	}
	
}