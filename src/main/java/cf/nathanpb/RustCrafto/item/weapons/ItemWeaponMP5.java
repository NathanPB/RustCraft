package cf.nathanpb.RustCrafto.item.weapons;

import cf.nathanpb.RustCrafto.guns.GunMP5;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by nathanpb on 8/17/17.
 */
public class ItemWeaponMP5 extends RustCraftItem implements Listener{
    public ItemWeaponMP5(){
        super();
        material = Material.DIAMOND_SPADE;
        matchDurability = true;
        durability = 8;
        name = ChatColor.GOLD+" H&K MP5";
        hideDurability = true;
    }
    @Override
    protected void onRightClick(PlayerInteractEvent e){
        new GunMP5(e.getPlayer()).tryToShot(e.getPlayer());
    }
}
