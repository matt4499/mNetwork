package com.github.matt4499.mNetwork.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {

  public static void AlertStaff(String message) {
    for (Player p : Bukkit.getOnlinePlayers()) {
      if (p.hasPermission("staff.alerts")) {
        p.sendMessage(color(message));
      }
    }
  }

  public static String color(String input) {
    return ChatColor.translateAlternateColorCodes('&', input);
  }


  public static Boolean filter(String string) {
    Boolean isblocked = false;
    String message = string;
    String fullmessage = message.replaceAll(" ", "");
    for (String word : string.split(" ")) {
      if (ChatFilter.badwords.contains(word) || ChatFilter.BypassWords.contains(word)) {
        isblocked = true;
        break;
      } else {
        isblocked = false;
        break;
      }
    }
    if (ChatFilter.badwords.contains(fullmessage) || ChatFilter.BypassWords.contains(fullmessage)) {
      return true;
    }
    return isblocked;
  }


}
