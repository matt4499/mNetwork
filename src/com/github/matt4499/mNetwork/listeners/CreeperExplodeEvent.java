package com.github.matt4499.mNetwork.listeners;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class CreeperExplodeEvent implements Listener {

  @EventHandler
  public void onEntityExplode(EntityExplodeEvent e) {
    if (e.getEntity() instanceof Creeper) {
      e.blockList().clear();
    }
  }

  @EventHandler
  public void onEndermenPickup(EntityChangeBlockEvent event) {
    if (event.getEntityType() == EntityType.ENDERMAN) {
      event.setCancelled(true);
    }
  }

}
