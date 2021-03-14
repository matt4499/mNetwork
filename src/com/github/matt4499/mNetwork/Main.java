package com.github.matt4499.mNetwork;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.matt4499.mNetwork.commands.ClearChatCommand;
import com.github.matt4499.mNetwork.commands.MaintenanceModecommand;
import com.github.matt4499.mNetwork.commands.StaffChat;
import com.github.matt4499.mNetwork.listeners.ChatMessages;
import com.github.matt4499.mNetwork.listeners.CreeperExplodeEvent;
import com.github.matt4499.mNetwork.utils.ChatFilter;
import com.github.matt4499.mNetwork.utils.SQL;


public class Main extends JavaPlugin {

  public static JavaPlugin instance;
  public static Boolean debug = false;
  public static String server = "economy";

  @Override
  public void onEnable() {
    instance = this;
    ConsoleLog("[mNetwork] [SQL] Initliaizing SQL...");
    if (SQL.init()) {
      ConsoleLog("[mNetwork] [SQL] Success!");
    } else {
      ConsoleLog("[mNetwork] [SQL] Failure! See error above.");
      Bukkit.getServer().getPluginManager().disablePlugin(this);
    }
    ConsoleLog("[mNetwork] [Main] Registering hooks: ChatMessages");
    getServer().getPluginManager().registerEvents(new ChatMessages(), this);
    ConsoleLog("[mNetwork] [Main] Registering hooks: ChatFilter");
    getServer().getPluginManager().registerEvents(new ChatFilter(), this);
    ConsoleLog("[mNetwork] [Main] Registering hooks: CreeperExplode");
    getServer().getPluginManager().registerEvents(new CreeperExplodeEvent(), this);
    ConsoleLog("[mNetwork] [Main] Registering commands: StaffChat");
    getServer().getPluginManager().registerEvents(new StaffChat(), this);
    ConsoleLog("[mNetwork] [Main] Registering commands: MaintenanceMode");
    getServer().getPluginManager().registerEvents(new MaintenanceModecommand(), this);
    ConsoleLog("[mNetwork] [Main] Registering commands: ClearChatCommand");
    getCommand("cc").setExecutor(new ClearChatCommand());
    getCommand("mm").setExecutor(new MaintenanceModecommand());
    ChatFilter.init();
  }

  public void ConsoleLog(String text) {
    getServer().getConsoleSender().sendMessage(text);
  }

}
