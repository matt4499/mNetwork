package com.github.matt4499.mNetwork.commands;

import java.util.concurrent.Callable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import com.github.matt4499.mNetwork.Main;
import com.github.matt4499.mNetwork.events.StaffChatEvent;
import net.md_5.bungee.api.ChatColor;

public class StaffChat implements Listener {

  @EventHandler
  public void onChat(AsyncPlayerChatEvent event) {
    if (event.getMessage().startsWith("!")) {
      if (!event.getPlayer().hasPermission("staff.chat")) {
        return;
      }
      event.setCancelled(true);
      String player = event.getPlayer().getDisplayName();
      String message = event.getMessage().replaceFirst("!", "");
      StaffChatEvent scevent = new StaffChatEvent(event.getPlayer(), message);
      Bukkit.getScheduler().callSyncMethod(Main.instance, new Callable<Boolean>() {
        @Override
        public Boolean call() {
          Bukkit.getPluginManager().callEvent(scevent);
          return true;
        }
      });
      for (Player p : Bukkit.getOnlinePlayers()) {
        if (p.hasPermission("staff.chat")) {
          p.sendMessage(ChatColor.translateAlternateColorCodes('&',
              "&cSC(&e" + player + "&c) &7> " + message));
        }
      }
    }
  }

}
