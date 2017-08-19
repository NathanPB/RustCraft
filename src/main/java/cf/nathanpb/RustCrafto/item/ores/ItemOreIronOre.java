package cf.nathanpb.RustCrafto.item.ores;

import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;

/**
 * Created by nathanpb on 8/17/17.
 */
public class ItemOreIronOre extends RustCraftItem implements Listener{
    public ItemOreIronOre(){
        super();
        material = Material.IRON_ORE;
        name = ChatColor.GREEN+"Iron Ore";
    }
}
