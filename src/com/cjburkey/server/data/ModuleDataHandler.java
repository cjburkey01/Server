package com.cjburkey.server.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import com.cjburkey.server.Logger;
import com.cjburkey.server.MainServer;
import com.cjburkey.server.module.Module;

public class ModuleDataHandler {

	private static final String separator = ";;;";
	private static final String newLine = "\n";
	public static final int FAIL_INT = Integer.MIN_VALUE;
	public static final double FAIL_DOUBLE = Double.MIN_VALUE;
	
	private final File dataFile;
	private Map<String, String> data;
	
	public ModuleDataHandler(Module m) {
		dataFile = new File(MainServer.instance.getDataFolder(), "/data/" + m.getName().toLowerCase() + ".dat");
		data = new HashMap<>();
	}
	
	public String get(String key) {
		return data.get(key);
	}
	
	public int getInt(String key) {
		try {
			int val = Integer.parseInt(get(key));
			return val;
		} catch(Exception e) { Logger.log("Couldn't parse to int: " + key); }
		return FAIL_INT;
	}
	
	public double getDouble(String key) {
		try {
			double val = Double.parseDouble(get(key));
			return val;
		} catch(Exception e) { Logger.log("Couldn't parse to double: " + key); }
		return FAIL_DOUBLE;
	}
	
	public void set(String key, Object value) {
		data.put(key, value + "");
	}
	
	public void unset(String key) {
		data.remove(key);
	}
	
	public void saveToDisk() {
		try {
			StringBuilder builder = new StringBuilder();
			for(Entry<String, String> entry : data.entrySet()) {
				builder.append(entry.getKey());
				builder.append(separator);
				builder.append(entry.getValue());
				builder.append(newLine);
			}
			String out = builder.toString();
			if(!dataFile.exists()) dataFile.getParentFile().mkdirs();
			FileWriter writer = new FileWriter(dataFile, false);
			writer.write(out);
			writer.close();
			Logger.log("Wrote data to file: " + dataFile.getName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadFromDisk() {
		try {
			if(dataFile.exists()) {
				StringBuilder builder = new StringBuilder();
				BufferedReader reader = new BufferedReader(new FileReader(dataFile));
				String l;
				while((l = reader.readLine()) != null) {
					builder.append(l);
					builder.append(newLine);
				}
				reader.close();
				String out = builder.toString();
				String[] entries = out.split(newLine);
				data.clear();
				for(String entry : entries) {
					String[] split = entry.split(separator);
					if(split.length == 2) {
						set(split[0], split[1]);
					}
				}
				Logger.log("Read data from file: " + dataFile.getName());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}