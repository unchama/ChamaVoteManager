package com.github.unchama.chamavotemanager.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import com.github.unchama.chamavotemanager.ChamaVoteManager;
import com.github.unchama.chamavotemanager.util.Util;
import com.sk89q.worldguard.protection.ApplicableRegionSet;

public class csumCommand implements TabExecutor {

	private ChamaVoteManager plugin;

	public csumCommand(ChamaVoteManager plugin) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.plugin = plugin;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command,
			String label, String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		//プレイヤーからの送信でない時処理終了
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.GREEN + "このコマンドはゲーム内から実行してください");
			return true;
		}else if(args.length == 0){
			Player player = (Player)sender;
			player.getLocation();
			
			
			// worldguard呼び出して引数にプレイヤー座標突っ込んで保護あるん？
			Util.getWorldGuard().getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation());
			Util.getWorldGuard().getRegionManager(player.getWorld()).;
			
			ApplicableRegionSet s = Util.getWorldGuard().getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation());
			for(s : ProtectedRegion){
				
			}
		}
		return false;
	}

}
