package cf.nathanpb.RustCrafto.commands;

import cf.nathanpb.RustCrafto.Utils.SystemUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by nathanpb on 8/18/17.
 */
public class Update implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!commandSender.isOp()) return false;
        new Thread() {
            @Override
            public void run() {
                super.run();
                String s2 = SystemUtils.shellCommand("./autobuild.sh");
                if (s2.contains("BUILD SUCCESSFUL")) {
                    commandSender.sendMessage(ChatColor.GOLD + "Atualizado com sucesso");
                } else {
                    commandSender.sendMessage(ChatColor.GOLD + "Falha ao compilar");
                }
            }
        }.start();
        return true;
    }
}
