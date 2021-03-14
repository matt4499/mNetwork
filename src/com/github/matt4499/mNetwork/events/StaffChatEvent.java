package com.github.matt4499.mNetwork.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class StaffChatEvent extends Event implements Cancellable {

  private String message;
  private Player player;
  private static final HandlerList HANDLERS_LIST = new HandlerList();
  private boolean isCancelled;

  public StaffChatEvent(Player player, String message) {
    this.player = player;
    this.message = message;
    this.isCancelled = false;
  }

  @Override
  public boolean isCancelled() {
    return isCancelled;
  }

  @Override
  public void setCancelled(boolean cancelled) {
    this.isCancelled = cancelled;
  }

  @Override
  public HandlerList getHandlers() {
    return HANDLERS_LIST;
  }

  public static HandlerList getHandlerList() {
    return HANDLERS_LIST;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Player getPlayer() {
    return this.player;
  }

}
