package cf.nathanpb.RustCrafto.item.materials;

import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;

/**
 * Created by nathanpb on 8/17/17.
 */
public class ItemMaterialIron extends RustCraftItem implements Listener{
    public ItemMaterialIron(){
        super();
        material = Material.IRON_INGOT;
        name = ChatColor.YELLOW+"Iron Ingot";
    }
}
