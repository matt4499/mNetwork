package com.github.matt4499.mNetwork.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;
import com.github.matt4499.mNetwork.utils.Utils;

public class ClearChatCommand implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
    Player player = (Player) sender;
    if (!player.hasPermission("staff.clearchat")) {
      player.sendMessage(Utils.color("&cYou do not have permission to do that."));
      return true;
    } else {
      Bukkit.broadcastMessage(StringUtils.repeat(" \n", 100));
      Bukkit.broadcastMessage(
          Utils.color("&cChat: &e" + player.getDisplayName() + " &7has cleared the chat."));
      return true;
    }
  }
}
