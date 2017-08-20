package cf.nathanpb.RustCrafto.item.weapons;

import cf.nathanpb.RustCrafto.PlayerInfo;
import cf.nathanpb.RustCrafto.guns.GunAK47;
import cf.nathanpb.RustCrafto.guns.GunAR15;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by nathanpb on 8/16/17.
 */
public class ItemWeaponAR15 extends RustCraftItem implements Listener{
    public ItemWeaponAR15(){
        super();
        material = Material.DIAMOND_SPADE;
        matchDurability = true;
        durability = 1;
        name = ChatColor.GOLD+"AR 15";
        hideDurability = true;
    }
    @Override
    protected void onRightClick(PlayerInteractEvent e){
        new GunAR15(e.getPlayer()).tryToShot(e.getPlayer());
    }

    @Override
    protected void onServerTick() {
        super.onServerTick();
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(instanceOf(p.getItemInHand())) PlayerInfo.gunInHand.put(p, new GunAR15(p)); else PlayerInfo.gunInHand.put(p, null);
        }
    }
}
