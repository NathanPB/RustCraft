package cf.nathanpb.RustCrafto.item.ores;

import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;

/**
 * Created by nathanpb on 8/17/17.
 */
public class ItemOreStone extends RustCraftItem implements Listener{
    public ItemOreStone(){
        super();
        material = Material.COBBLESTONE;
        name = ChatColor.GREEN+"Stone";
    }
}
