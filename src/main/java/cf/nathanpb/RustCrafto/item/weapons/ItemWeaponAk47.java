package cf.nathanpb.RustCrafto.item.weapons;

import cf.nathanpb.RustCrafto.guns.GunAK47;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by nathanpb on 8/15/17.
 */
public class ItemWeaponAk47 extends RustCraftItem implements Listener{
    public ItemWeaponAk47(){
        super();
        material = Material.DIAMOND_SPADE;
        matchDurability = true;
        name = ChatColor.GOLD+"AK-47";
        hideDurability = true;
    }
    @Override
    protected void onRightClick(PlayerInteractEvent e){
        new GunAK47(e.getPlayer()).tryToShot(e.getPlayer());
    }
}
