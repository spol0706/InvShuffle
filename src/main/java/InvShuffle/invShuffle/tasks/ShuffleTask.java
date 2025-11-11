package InvShuffle.invShuffle.tasks;

import InvShuffle.invShuffle.InvShuffle;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleTask extends BukkitRunnable {

    private final InvShuffle plugin;
    private int timeLeft;
    private final int initialTime;

    public ShuffleTask(InvShuffle plugin, int durationInSeconds) {
        this.plugin = plugin;
        this.initialTime = durationInSeconds;
        this.timeLeft = durationInSeconds;
    }

    @Override
    public void run() {
        if (timeLeft <= 0) {
            shuffleInventories();
            timeLeft = initialTime; // Reset timer
            return;
        }

        updateActionBar();
        timeLeft--;
    }

    private void updateActionBar() {
        int minutes = timeLeft / 60;
        int seconds = timeLeft % 60;
        String timeString = String.format("§e다음 인벤토리 교체까지... §f%02d:%02d", minutes, seconds);

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(timeString));
        }
    }

    private void shuffleInventories() {
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        if (players.size() < 2) {
            // Not enough players to shuffle inventories.
            return;
        }

        List<ItemStack[]> inventories = new ArrayList<>();
        for (Player player : players) {
            inventories.add(player.getInventory().getContents());
        }

        Collections.shuffle(inventories);

        for (int i = 0; i < players.size(); i++) {
            players.get(i).getInventory().setContents(inventories.get(i));
            players.get(i).sendMessage("§a플레이어들의 인벤토리가 무작위로 교체되었습니다!");
        }
    }
}
