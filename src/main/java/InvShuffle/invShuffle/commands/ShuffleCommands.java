package InvShuffle.invShuffle.commands;

import InvShuffle.invShuffle.InvShuffle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ShuffleCommands implements CommandExecutor, TabCompleter {

    private final InvShuffle plugin;

    public ShuffleCommands(InvShuffle plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§c사용법: /invshuffle <start|stop|reload>");
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "start":
                plugin.startShuffleTask();
                sender.sendMessage("§a인벤토리 셔플을 시작합니다.");
                break;
            case "stop":
                plugin.stopShuffleTask();
                sender.sendMessage("§e인벤토리 셔플을 중지합니다.");
                break;
            case "reload":
                plugin.reload();
                sender.sendMessage("§aInvShuffle 설정을 새로고침했습니다.");
                break;
            default:
                sender.sendMessage("§c알 수 없는 명령어입니다. 사용법: /invshuffle <start|stop|reload>");
                break;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            String input = args[0].toLowerCase();
            if ("start".startsWith(input)) {
                completions.add("start");
            }
            if ("stop".startsWith(input)) {
                completions.add("stop");
            }
            if ("reload".startsWith(input)) {
                completions.add("reload");
            }
            return completions;
        }
        return null;
    }
}
