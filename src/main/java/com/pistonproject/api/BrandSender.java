package com.pistonproject.api;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BrandSender {

    public static void sendBrandMessage(JavaPlugin javaPlugin, String state){
        List<String> message = new ArrayList<>();
        message.add("");
        message.add("&4Piston&cProject §7©" + Calendar.getInstance().get(Calendar.YEAR) + "§r §fAll rights reserved.");
        message.add("" + javaPlugin.getDescription().getWebsite());
        message.add("");
        for(String line : message){
            javaPlugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', line));
        }
    }

}