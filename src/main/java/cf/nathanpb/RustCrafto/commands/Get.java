package cf.nathanpb.RustCrafto.commands;

import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by nathanpb on 8/15/17.
 */
public class Get implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings){
        try {
            if(!commandSender.isOp()) return false;
            Player p = Bukkit.getPlayerExact(commandSender.getName());
            String s2 = RustCraftItem.class.getPackage().getName() + "."+strings[0];
            RustCraftItem ak = RustCraftItem.getInstanceFrom(Class.forName(s2));
            ak.add(p.getInventory());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
