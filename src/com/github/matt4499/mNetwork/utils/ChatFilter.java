package com.github.matt4499.mNetwork.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import com.github.matt4499.mNetwork.Main;

public class ChatFilter implements Listener {

  public static ArrayList<String> badwords = new ArrayList<String>();
  public static ArrayList<String> BypassWords = new ArrayList<String>();

  public static void init() {
    badwords.add("gay");
    badwords.add("cunt");
    badwords.add("queer");
    badwords.add("fag");
    badwords.add("faggot");
    badwords.add("homo");
    badwords.add("homosexual");
    badwords.add("horny");
    badwords.add("nigger");
    badwords.add("penis");
    badwords.add("dick");
    badwords.add("cock");
    badwords.add("schlong");
    badwords.add("balls");
    badwords.add("testicles");
    badwords.add("testicle");

    BypassWords.add("g4y");
    BypassWords.add("g@y");
    BypassWords.add("gae");
    BypassWords.add("f@g");
    BypassWords.add("f@gg");
    BypassWords.add("f@ggot");
    BypassWords.add("h@m@");
    BypassWords.add("n!gger");
    BypassWords.add("n@gger");
    BypassWords.add("nigg3r");
    BypassWords.add("nig");
    BypassWords.add("hornny");
    BypassWords.add("hornie");
    BypassWords.add("d!ick");
    BypassWords.add("diick");
    BypassWords.add("c@ck");
    BypassWords.add("ballz");
    BypassWords.add("testical");
    BypassWords.add("testicall");
    BypassWords.add("testicalz");
    BypassWords.add("testicalls");
    BypassWords.add("ga y");

    Bukkit.getConsoleSender().sendMessage("[mNetwork] [ChatFilter] Loaded the chat filter!");
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onChat(AsyncPlayerChatEvent event)
      throws ClassNotFoundException, SQLException, InterruptedException, ExecutionException {
    for (String word : event.getMessage().split(" ")) {
      if (word.equalsIgnoreCase("nigger")) {
        event.setCancelled(true);
        banRacism(event.getPlayer(), event.getMessage());
        break;
      }
      if (badwords.contains(word)) {
        event.setCancelled(true);
        cancelAlert(event.getPlayer(), event.getMessage());
        break;
      }
      if (BypassWords.contains(word)) {
        event.setCancelled(true);
        tempbanBypassFilter(event.getPlayer(), event.getMessage());
        break;
      }
    }

  }

  public void insert(String text, String server) throws ClassNotFoundException, SQLException {
    Connection connection = SQL.getConnection();
    String sql = "INSERT INTO audit_logs(id,text,date,server) VALUES(?,?,?,?)";
    if (Main.debug) {
      Main.instance.getServer().getConsoleSender().sendMessage("[mNetwork] [SQL] Running: " + sql);
    }
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setNull(1, 0);
      pstmt.setString(2, text);
      pstmt.setString(3, getTime());
      pstmt.setString(4, server);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void tempbanBypassFilter(Player player, String message)
      throws ClassNotFoundException, SQLException {
    ConsoleCommandSender console = Bukkit.getConsoleSender();
    Bukkit.getScheduler().callSyncMethod(Main.instance, new Callable<Boolean>() {
      @Override
      public Boolean call() {
        return Bukkit.dispatchCommand(console,
            "tempban " + player.getName() + " 1h [AutoBan] Bypassing Chat Filter.");
      }
    });
    insert("[TempBan] " + player.getName() + " was auto-tempbanned for bypassing chat filter: "
        + message, Main.server);
  }

  public void banRacism(Player player, String message)
      throws ClassNotFoundException, SQLException, InterruptedException, ExecutionException {
    ConsoleCommandSender console = Bukkit.getConsoleSender();
    Bukkit.getScheduler().callSyncMethod(Main.instance, new Callable<Boolean>() {
      @Override
      public Boolean call() {
        return Bukkit.dispatchCommand(console, "ban " + player.getName() + " [AutoBan] Racism");
      }
    });
    insert("[Ban] " + player.getName() + " was autobanned for racism: " + message, Main.server);
  }

  public void cancelAlert(Player player, String message)
      throws ClassNotFoundException, SQLException {
    insert("[Cancel] " + player.getName() + " tried to say: " + message, Main.server);
    player.sendMessage(
        Utils.color("&cWatch your language! Bypassing the filter will result in a ban."));
    Utils.AlertStaff("&cAlerts: &e" + player.getName() + " &7tried to say: &e" + message);
  }

  public String getTime() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY hh:mm:ss.SSS a");
    LocalDateTime now = LocalDateTime.now();
    return dtf.format(now);
  }

}
