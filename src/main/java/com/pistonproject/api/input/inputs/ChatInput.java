package com.pistonproject.api.input.inputs;

import org.bukkit.entity.Player;

public interface ChatInput {

    boolean onChatInput(Player player, String input);

    void onPlayerSneak(Player player);

}