package cf.nathanpb.RustCrafto.item.ores;

import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;

/**
 * Created by nathanpb on 8/17/17.
 */
public class ItemOreSulphur extends RustCraftItem implements Listener{
    public ItemOreSulphur(){
        super();
        material = Material.SULPHUR;
        name = ChatColor.GREEN+"Sulphur";
    }
}
