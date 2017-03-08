package com.github.unchama.chamavotemanager.util;

import org.bukkit.plugin.Plugin;

import com.github.unchama.chamavotemanager.ChamaVoteManager;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Util {
	//ワールドガードAPIを返す
	public static WorldGuardPlugin getWorldGuard() {
		Plugin plugin = ChamaVoteManager.plugin.getServer().getPluginManager().getPlugin("WorldGuard");

	    // WorldGuard may not be loaded
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	        return null; // Maybe you want throw an exception instead
	    }

	    return (WorldGuardPlugin) plugin;
	}
}
