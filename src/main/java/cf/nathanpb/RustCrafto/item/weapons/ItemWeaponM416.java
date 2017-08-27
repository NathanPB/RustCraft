package cf.nathanpb.RustCrafto.item.weapons;

import cf.nathanpb.RustCrafto.guns.GunM416;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by nathanpb on 8/17/17.
 */
public class ItemWeaponM416 extends RustCraftItem implements Listener{
    public ItemWeaponM416(){
        super();
        material = Material.DIAMOND_SPADE;
        matchDurability = true;
        durability = 6;
        name = ChatColor.GOLD+"M416";
        hideDurability = true;
    }
    @Override
    protected void onRightClick(PlayerInteractEvent e){
        new GunM416(e.getPlayer()).tryToShot(e.getPlayer());
    }
}
