package cf.nathanpb.RustCrafto.item.weapons;

import cf.nathanpb.RustCrafto.guns.GunDeagle;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by nathanpb on 8/17/17.
 */
public class ItemWeaponDeagle extends RustCraftItem implements Listener{
    public ItemWeaponDeagle(){
        super();
        material = Material.DIAMOND_SPADE;
        matchDurability = true;
        durability = 5;
        name = ChatColor.GOLD+"Desert Eagle";
        hideDurability = true;
    }
    @Override
    protected void onRightClick(PlayerInteractEvent e){
        new GunDeagle(e.getPlayer()).tryToShot(e.getPlayer());
    }
}
