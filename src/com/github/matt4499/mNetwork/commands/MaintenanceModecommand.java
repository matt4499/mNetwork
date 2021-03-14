package com.github.matt4499.mNetwork.commands;

import java.util.concurrent.Callable;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import com.github.matt4499.mNetwork.Main;
import com.github.matt4499.mNetwork.events.MaintenanceModeEvent;
import com.github.matt4499.mNetwork.utils.Utils;

public class MaintenanceModecommand implements CommandExecutor, Listener {
  @Override
  public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
    Player player = (Player) sender;
    if (!player.hasPermission("staff.mmode")) {
      player.sendMessage(Utils.color("&cYou do not have permission to do that."));
      return true;
    } else {
      if (Bukkit.hasWhitelist()) {
        Bukkit.setWhitelist(false);
        MaintenanceModeEvent mmevent = new MaintenanceModeEvent(player, false);
        Bukkit.getScheduler().callSyncMethod(Main.instance, new Callable<Boolean>() {
          @Override
          public Boolean call() {
            Bukkit.getPluginManager().callEvent(mmevent);
            return true;
          }
        });
        player.sendMessage(Utils.color("&aDisabled Maintenance Mode."));
        return true;

      } else {
        Bukkit.setWhitelist(true);
        MaintenanceModeEvent mmevent = new MaintenanceModeEvent(player, true);
        Bukkit.getScheduler().callSyncMethod(Main.instance, new Callable<Boolean>() {
          @Override
          public Boolean call() {
            Bukkit.getPluginManager().callEvent(mmevent);
            return true;
          }
        });
        player.sendMessage(Utils.color("&cEnabled Maintenance Mode."));
        return true;
      }
    }
  }
}
