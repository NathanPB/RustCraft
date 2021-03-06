package cf.nathanpb.RustCrafto.item.weapons;

import cf.nathanpb.RustCrafto.guns.GunScarH;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by nathanpb on 8/17/17.
 */
public class ItemWeaponScarH extends RustCraftItem implements Listener{
    public ItemWeaponScarH(){
        super();
        material = Material.DIAMOND_SPADE;
        matchDurability = true;
        durability = 9;
        name = ChatColor.GOLD+"FN Scar-H";
        hideDurability = true;
    }
    @Override
    protected void onRightClick(PlayerInteractEvent e){
        new GunScarH(e.getPlayer()).tryToShot(e.getPlayer());
    }
}
