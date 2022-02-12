package com.pistonproject.api;

import org.bukkit.plugin.java.JavaPlugin;
import com.pistonproject.api.discord.DiscordUtil;

public final class PistonProjectAPI extends JavaPlugin {

    private DiscordUtil discordUtil;

    @Override
    public void onEnable() {
        enableDiscordUtil();
        BrandSender.sendBrandMessage(this, "&aEnabled");
    }

    @Override
    public void onDisable() {
        BrandSender.sendBrandMessage(this, "&cDisabled");
    }

    private void enableDiscordUtil() {
        try {
            Class.forName("net.dv8tion.jda.api.JDA");
            discordUtil = new DiscordUtil(this);
        } catch (ClassNotFoundException ignored) {}
    }

    public DiscordUtil getDiscordUtil() {
        return discordUtil;
    }

}