package InvShuffle.invShuffle;

import InvShuffle.invShuffle.commands.ShuffleCommands;
import InvShuffle.invShuffle.manager.ConfigManager;
import InvShuffle.invShuffle.tasks.ShuffleTask;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class InvShuffle extends JavaPlugin {

    private ConfigManager configManager;
    private BukkitTask shuffleTask;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        ShuffleCommands commandExecutor = new ShuffleCommands(this);
        PluginCommand command = getCommand("invshuffle");
        command.setExecutor(commandExecutor);
        command.setTabCompleter(commandExecutor);

        startShuffleTask();
        getLogger().info("InvShuffle 플러그인이 활성화되었습니다.");
    }

    @Override
    public void onDisable() {
        stopShuffleTask();
        getLogger().info("InvShuffle 플러그인이 비활성화되었습니다.");
    }

    public void startShuffleTask() {
        if (shuffleTask != null && !shuffleTask.isCancelled()) {
            return; // Task is already running
        }
        int intervalInSeconds = configManager.getShuffleInterval() * 60;
        shuffleTask = new ShuffleTask(this, intervalInSeconds).runTaskTimer(this, 0L, 20L); // Run every second
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
