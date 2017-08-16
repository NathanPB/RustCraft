package cf.nathanpb.RustCrafto;

import cf.nathanpb.RustCrafto.item.ItemWeaponAk47;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by nathanpb on 8/15/17.
 */
public class getCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = Bukkit.getPlayerExact(commandSender.getName());
        ItemWeaponAk47 ak = RustCraftItem.getInstance(ItemWeaponAk47.class);
        p.getInventory().addItem(ak.get());
        return true;
    }
}
