package InvShuffle.invShuffle.manager;

import InvShuffle.invShuffle.InvShuffle;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private final InvShuffle plugin;
    private int shuffleInterval;

    public ConfigManager(InvShuffle plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        config.options().copyDefaults(true);
        plugin.saveConfig();
        shuffleInterval = config.getInt("shuffle-interval-minutes", 5);
    }

    public int getShuffleInterval() {
        return shuffleInterval;
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        loadConfig();
    }
}
