package com.pistonproject.api.scheduler;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public abstract class Task{

    private final JavaPlugin plugin;
    private BukkitTask task;
    private final long ticks;

    public Task(JavaPlugin plugin, long ticks){
        this.plugin = plugin;
        this.ticks = ticks;
    }

    public void startScheduler() {
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        task = scheduler.runTaskTimer(plugin, this::actions, 0L, ticks);
    }

    public void stopScheduler(){
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        scheduler.cancelTask(task.getTaskId());
        stopActions();
    }

    public abstract void actions();

    public void stopActions(){}

    public boolean isRunning()
    {
        return plugin.getServer().getScheduler().isCurrentlyRunning(task.getTaskId());
    }
}
