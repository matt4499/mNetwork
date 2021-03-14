package com.github.matt4499.mNetwork.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MaintenanceModeEvent extends Event implements Cancellable {

  private Player player;
  private static final HandlerList HANDLERS_LIST = new HandlerList();
  private boolean isCancelled;
  private boolean whitelist;

  public MaintenanceModeEvent(Player player, Boolean whitelist) {
    this.player = player;
    this.isCancelled = false;
    this.whitelist = whitelist;
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

  public Boolean getWhitelist() {
    return this.whitelist;
  }

  public Player getPlayer() {
    return this.player;
  }

}
