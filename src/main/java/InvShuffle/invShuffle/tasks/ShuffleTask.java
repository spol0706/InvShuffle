package InvShuffle.invShuffle.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleTask extends BukkitRunnable {

    @Override
    public void run() {
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        if (players.size() < 2) {
            return; // Not enough players to shuffle inventories.
        }

        List<ItemStack[]> inventories = new ArrayList<>();
        for (Player player : players) {
            inventories.add(player.getInventory().getContents());
        }

        Collections.shuffle(inventories);

        for (int i = 0; i < players.size(); i++) {
            players.get(i).getInventory().setContents(inventories.get(i));
            players.get(i).sendMessage("§aYour inventory has been shuffled!");
        }
    }
}
