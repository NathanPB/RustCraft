package cf.nathanpb.RustCrafto.item.minerable;

import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;

/**
 * Created by nathanpb on 8/17/17.
 */
public class ItemMinerableWood extends RustCraftItem implements Listener{
    public ItemMinerableWood(){
        super();
        material = Material.LOG;
        name = ChatColor.BLACK+"OreWood";
    }
}
