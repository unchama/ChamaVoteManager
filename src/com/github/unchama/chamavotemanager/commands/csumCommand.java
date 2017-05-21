package com.github.unchama.chamavotemanager.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;

import com.github.unchama.chamavotemanager.ChamaVoteManager;

public class csumCommand implements TabExecutor {

	private ChamaVoteManager plugin;

	public csumCommand(ChamaVoteManager plugin) {
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

		Map<String, Integer> voteMap = new HashMap<String, Integer>();

		// 獲得票をuuidごとに集計
		ConfigurationSection voter = plugin.getConfig().getConfigurationSection("voter");
		sender.sendMessage("voter");
		for(String name : voter.getKeys(false)){
			String uuid = voter.getString(name);
			int vote = 0;
			if(voteMap.containsKey(uuid)){
				vote = voteMap.get(uuid);
			}
			vote++;
			voteMap.put(uuid, vote);
		}

		// ソート
        List<Map.Entry<String,Integer>> entries =
                new ArrayList<Map.Entry<String,Integer>>(voteMap.entrySet());
          Collections.sort(entries, new Comparator<Map.Entry<String,Integer>>() {

              @Override
              public int compare(
                    Entry<String,Integer> entry1, Entry<String,Integer> entry2) {
                  return ((Integer)entry2.getValue()).compareTo((Integer)entry1.getValue());
              }
          });

		// 上位数名を表示する場合のリミット
		int limit = -1;
		if(args.length == 1){
			try {
				limit = Integer.valueOf(args[0]);
			} catch (NumberFormatException nfex) {
			}
		}

        // 表示
		ConfigurationSection candidate = plugin.getConfig().getConfigurationSection("candidate");
        for (Entry<String,Integer> s : entries) {
        	sender.sendMessage(candidate.getString(s.getKey()) + " : " + s.getValue() + " 票(" + s.getKey() + ")");
        	limit--;
        	if(limit == 0){
        		break;
        	}
        }
		return true;
	}

}
