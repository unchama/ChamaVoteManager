package com.github.unchama.chamavotemanager.commands;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import com.github.unchama.chamavotemanager.ChamaVoteManager;
import com.github.unchama.chamavotemanager.util.Util;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class cvoteCommand implements TabExecutor {

	private ChamaVoteManager plugin;

	public cvoteCommand(ChamaVoteManager plugin) {
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
			sender.sendMessage(ChatColor.GREEN + "このコマンドはゲーム内から実行して下さい");
			return true;
		}else if(args.length == 0){
			Player player = (Player)sender;

			// プレイヤーが今立っている場所の保護のOwnerを取得
			Set<ProtectedRegion> s = Util.getWorldGuard().getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).getRegions();
			// 保護が1つじゃなかったらreturn
			if(s.size()!=1){
				sender.sendMessage(ChatColor.GREEN + "保護が1つのみの場所で実行して下さい");
				return true;
			}
			ProtectedRegion r = s.iterator().next();

			if(r.getOwners().getUniqueIds().size() != 1){
				sender.sendMessage(ChatColor.GREEN + "保護のOwnerが複数人います！投票するには保護のOwnerは一人だけである必要があります");
				return true;
			}
			UUID u = r.getOwners().getUniqueIds().iterator().next();

			plugin.getConfig().set(player.getUniqueId().toString(),
					u.toString());
			plugin.saveConfig();
			sender.sendMessage(ChatColor.GREEN + u.toString() + "に投票しました。再度コマンドを実行すると投票先をいつでも変更出来ます");
			return true;
		}
		return false;
	}

}
