package com.pistonproject.api.input;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import com.pistonproject.api.input.inputs.*;

import java.util.HashMap;
import java.util.UUID;

public class InputManager implements Listener {

    private final HashMap<UUID, ChatInput> chats = new HashMap<>();
    private final HashMap<UUID, DropInput> drops = new HashMap<>();
    private final HashMap<UUID, ShiftInput> shifts = new HashMap<>();

    public void addInput(Player player, ChatInput input) {
        chats.put(player.getUniqueId(), input);
    }
    public void addInput(Player player, DropInput input) {
        drops.put(player.getUniqueId(), input);
    }
    public void addInput(Player player, ShiftInput input) {
        shifts.put(player.getUniqueId(), input);
    }

    public boolean isChatInput(Player player) {
        return chats.containsKey(player.getUniqueId());
    }

    public boolean isDropInput(Player player) {
        return drops.containsKey(player.getUniqueId());
    }

    public boolean isShiftInput(Player player) {
        return shifts.containsKey(player.getUniqueId());
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (chats.containsKey(uuid)) {
            event.setCancelled(true);
            ChatInput chatInput = chats.get(uuid);
            if (chatInput.onChatInput(player, event.getMessage())) {
                chats.remove(uuid);
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (drops.containsKey(uuid)) {
            event.setCancelled(true);
            DropInput chatInput = drops.get(uuid);
            chatInput.onPLayerDrop(player, event.getItemDrop().getItemStack());
            drops.remove(uuid);
        }
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if (chats.containsKey(uuid)) {
            ChatInput chatInput = chats.get(uuid);
            chatInput.onPlayerSneak(player);
            chats.remove(uuid);
        }
        if (drops.containsKey(uuid)) {
            DropInput chatInput = drops.get(uuid);
            chatInput.onPlayerSneak(player);
            drops.remove(uuid);
        }
        if (shifts.containsKey(uuid)) {
            ShiftInput shiftInput = shifts.get(uuid);
            shiftInput.onShift(player);
            shifts.remove(uuid);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        chats.remove(event.getPlayer().getUniqueId());
        drops.remove(event.getPlayer().getUniqueId());
    }

}