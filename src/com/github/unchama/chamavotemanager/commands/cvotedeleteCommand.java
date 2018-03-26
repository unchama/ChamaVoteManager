package com.github.unchama.chamavotemanager.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
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

		//コンソール以外なら処理終了
		if(!(sender instanceof ConsoleCommandSender)){
			sender.sendMessage(ChatColor.GREEN + "このコマンドはコンソールから実行して下さい");
			return true;
		}
		//Bukkitを信用してないわけではないですけど念のため
		try{
			//Configのvoter,candidateの中身を削除
			plugin.getConfig().set("voter", null);
			plugin.getConfig().set("candidate", null);
			plugin.saveConfig();
		}catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "投票データの削除に失敗しました");
			e.printStackTrace();
		}
		sender.sendMessage(ChatColor.GREEN + "投票データを削除しました");
		return true;
	}
}
