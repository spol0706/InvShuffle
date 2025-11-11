package InvShuffle.invShuffle;

import InvShuffle.invShuffle.commands.ShuffleCommands;
import InvShuffle.invShuffle.manager.ConfigManager;
import InvShuffle.invShuffle.tasks.ShuffleTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class InvShuffle extends JavaPlugin {

    private ConfigManager configManager;
    private BukkitTask shuffleTask;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        getCommand("invshuffle").setExecutor(new ShuffleCommands(this));
        startShuffleTask();
        getLogger().info("InvShuffle plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        stopShuffleTask();
        getLogger().info("InvShuffle plugin has been disabled.");
    }

    public void startShuffleTask() {
        if (shuffleTask != null && !shuffleTask.isCancelled()) {
            return; // Task is already running
        }
        long interval = configManager.getShuffleInterval() * 20L * 60L; // Convert minutes to ticks
        shuffleTask = new ShuffleTask().runTaskTimer(this, 0L, interval);
    }

    public void stopShuffleTask() {
        if (shuffleTask != null) {
            shuffleTask.cancel();
            shuffleTask = null;
        }
    }

    public void reload() {
        stopShuffleTask();
        configManager.reloadConfig();
        startShuffleTask();
    }
}
