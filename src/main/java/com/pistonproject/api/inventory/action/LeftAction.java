package com.pistonproject.api.inventory.action;

import org.bukkit.entity.Player;

public interface LeftAction {
    void action(Player player, int slot);
}