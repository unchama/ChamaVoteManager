package com.github.unchama.chamavotemanager.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import com.github.unchama.chamavotemanager.ChamaVoteManager;

public class cvotedeleteCommand implements TabExecutor{
	private ChamaVoteManager plugin;

	public cvotedeleteCommand(ChamaVoteManager plugin) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.plugin = plugin;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd,
			String label, String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args) {

		return true;
	}
}
