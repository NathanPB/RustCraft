package cf.nathanpb.RustCrafto.item.weapons;

import cf.nathanpb.RustCrafto.PlayerInfo;
import cf.nathanpb.RustCrafto.guns.GunAK47;
import cf.nathanpb.RustCrafto.guns.GunM1911;
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
public class ItemWeaponM1911 extends RustCraftItem implements Listener{
    public ItemWeaponM1911(){
        super();
        material = Material.DIAMOND_SPADE;
        matchDurability = true;
        durability = 7;
        name = ChatColor.GOLD+"M1911";
        hideDurability = true;
    }
    @Override
    protected void onRightClick(PlayerInteractEvent e){
        new GunM1911(e.getPlayer()).tryToShot(e.getPlayer());
    }

    @Override
    protected void onServerTick() {
        super.onServerTick();
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(instanceOf(p.getItemInHand())) PlayerInfo.gunInHand.replace(p, new GunM1911(p)); else PlayerInfo.gunInHand.replace(p, null);
        }
    }
}
