package com.github.unchama.chamavotemanager;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.unchama.chamavotemanager.commands.csumCommand;
import com.github.unchama.chamavotemanager.commands.cvoteCommand;



public class ChamaVoteManager extends JavaPlugin implements Listener {
	private static FileConfiguration config;
	public static ChamaVoteManager plugin;
	private HashMap<String, TabExecutor> commandlist;

	public ChamaVoteManager() {

	}

	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		config = getConfig();

		getServer().getPluginManager().registerEvents(this, this);


		//コマンドの登録
		commandlist = new HashMap<String, TabExecutor>();
		commandlist.put("cvote",new cvoteCommand(plugin));
		commandlist.put("csum",new csumCommand(plugin));
	}

	public void onDisable() {
		saveConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		return commandlist.get(cmd.getName()).onCommand(sender, cmd, label, args);
	}

	@EventHandler
	public void SRPEvent(PlayerResourcePackStatusEvent event) {
		if (PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED == event
				.getStatus()) {
			config.set(event.getPlayer().getUniqueId().toString(),
					Boolean.valueOf(true));
		} else {
			config.set(event.getPlayer().getUniqueId().toString(),
					Boolean.valueOf(false));
		}
	}

}
