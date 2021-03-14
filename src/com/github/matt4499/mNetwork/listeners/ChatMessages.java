package com.github.matt4499.mNetwork.listeners;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import com.github.matt4499.mNetwork.Main;
import com.github.matt4499.mNetwork.utils.SQL;

public class ChatMessages implements Listener {

  @EventHandler(ignoreCancelled = false, priority = EventPriority.HIGHEST)
  public void onPlayerMessage(AsyncPlayerChatEvent event) {

    if (event.isCancelled()) {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY hh:mm:ss.SSS a");
      LocalDateTime now = LocalDateTime.now();
      insert(event.getPlayer().getName(), event.getMessage(), dtf.format(now),
          event.getPlayer().getLocation().toString(), true, Main.server);
    } else {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY hh:mm:ss.SSS a");
      LocalDateTime now = LocalDateTime.now();
      insert(event.getPlayer().getName(), event.getMessage(), dtf.format(now),
          event.getPlayer().getLocation().toString(), false, Main.server);
    }

  }

  public void insert(String player, String message, String time, String location, Boolean cancelled,
      String server) {

    String sql =
        "INSERT INTO chatmessages(id,player,message,time,location,cancelled,server) VALUES(?,?,?,?,?,?,?)";
    if (Main.debug) {
      Main.instance.getServer().getConsoleSender().sendMessage("[mNetwork] [SQL] Running: " + sql);
    }
    Connection connection = null;
    try {
      connection = SQL.openConnection();
    } catch (ClassNotFoundException | SQLException e1) {
      e1.printStackTrace();
    }
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
      pstmt.setNull(1, 0);
      pstmt.setString(2, player);
      pstmt.setString(3, message);
      pstmt.setString(4, time);
      pstmt.setString(5, location);
      pstmt.setBoolean(6, cancelled);
      pstmt.setString(7, server);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

}
