package cf.nathanpb.RustCrafto.item.weapons;

import cf.nathanpb.RustCrafto.guns.GunColt;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by nathanpb on 8/17/17.
 */
public class ItemWeaponColt extends RustCraftItem implements Listener{
    public ItemWeaponColt(){
        super();
        material = Material.DIAMOND_SPADE;
        matchDurability = true;
        durability = 4;
        name = ChatColor.GOLD+"Colt Python";
        hideDurability = true;
    }
    @Override
    protected void onRightClick(PlayerInteractEvent e){
        new GunColt(e.getPlayer()).tryToShot(e.getPlayer());
    }
}
