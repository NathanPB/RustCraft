package cf.nathanpb.RustCrafto.item.weapons;

import cf.nathanpb.RustCrafto.PlayerInfo;
import cf.nathanpb.RustCrafto.guns.GunAK47;
import cf.nathanpb.RustCrafto.guns.GunBerettaM9;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by nathanpb on 8/17/17.
 */
public class ItemWeaponBerettaM9 extends RustCraftItem implements Listener{
    public ItemWeaponBerettaM9(){
        super();
        material = Material.DIAMOND_SPADE;
        matchDurability = true;
        durability = 3;
        name = ChatColor.GOLD+"Beretta M9";
        hideDurability = true;
    }
    @Override
    protected void onRightClick(PlayerInteractEvent e){
        new GunBerettaM9(e.getPlayer()).tryToShot(e.getPlayer());
    }

    @Override
    protected void onServerTick() {
        super.onServerTick();
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(instanceOf(p.getItemInHand())) PlayerInfo.gunInHand.put(p, new GunBerettaM9(p)); else PlayerInfo.gunInHand.put(p, null);
        }
    }
}
