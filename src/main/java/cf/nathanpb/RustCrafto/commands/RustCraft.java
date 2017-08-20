package cf.nathanpb.RustCrafto.commands;

import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by nathanpb on 8/15/17.
 */
public class RustCraft implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings){
        try {
            if(strings[0].equalsIgnoreCase("get")) {
                if (!commandSender.isOp()) return false;
                Player p = Bukkit.getPlayerExact(commandSender.getName());
                String s2 = RustCraftItem.class.getPackage().getName() + "." + strings[1];
                RustCraftItem ak = RustCraftItem.getInstanceFrom(Class.forName(s2));
                ak.add(p.getInventory());
                commandSender.sendMessage(ChatColor.BLUE+"Item "+ChatColor.GOLD+ak.getClass().getName()+ChatColor.BLUE+" recebido");
                return true;
            }
            if(strings[0].equalsIgnoreCase("help")) {
                commandSender.sendMessage(ChatColor.BLUE+"Use "+ChatColor.BLUE+"/rustscraft get <package.classname>"+ChatColor.BLUE+" para pegar um item");
                commandSender.sendMessage(ChatColor.BLUE+"Use "+ChatColor.BLUE+"/rustscraft craft"+ChatColor.BLUE+" para abrir a tela de crafting");
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }
}
