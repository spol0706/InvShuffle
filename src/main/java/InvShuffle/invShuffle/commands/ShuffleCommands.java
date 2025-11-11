package InvShuffle.invShuffle.commands;

import InvShuffle.invShuffle.InvShuffle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ShuffleCommands implements CommandExecutor {

    private final InvShuffle plugin;

    public ShuffleCommands(InvShuffle plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§cUsage: /invshuffle <start|stop|reload>");
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "start":
                plugin.startShuffleTask();
                sender.sendMessage("§aInventory shuffling started.");
                break;
            case "stop":
                plugin.stopShuffleTask();
                sender.sendMessage("§eInventory shuffling stopped.");
                break;
            case "reload":
                plugin.reload();
                sender.sendMessage("§aInvShuffle configuration reloaded.");
                break;
            default:
                sender.sendMessage("§cUnknown subcommand. Usage: /invshuffle <start|stop|reload>");
                break;
        }
        return true;
    }
}
