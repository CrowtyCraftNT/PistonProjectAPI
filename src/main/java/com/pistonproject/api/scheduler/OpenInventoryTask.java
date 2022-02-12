package com.pistonproject.api.scheduler;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class OpenInventoryTask extends Task {

    private final Player player;
    private final Inventory inventory;

    public OpenInventoryTask(JavaPlugin plugin, Player player, Inventory inventory, long ticks) {
        super(plugin, ticks);
        this.player = player;
        this.inventory = inventory;
    }

    @Override
    public void actions() {
        if (player.isOnline()) {
            player.openInventory(inventory);
        }
        stopScheduler();
    }

}