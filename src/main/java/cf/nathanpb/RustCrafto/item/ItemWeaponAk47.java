package cf.nathanpb.RustCrafto.item;

import cf.nathanpb.RustCrafto.guns.GunAK47;
import org.bukkit.Bukkit;
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
        material = Material.STICK;
        name = ChatColor.GOLD+"AK-47";
    }
    @Override
    protected void onRightClick(PlayerInteractEvent e){
        new GunAK47(get(), e.getPlayer()).tryToShot(e.getPlayer());
    }
}
