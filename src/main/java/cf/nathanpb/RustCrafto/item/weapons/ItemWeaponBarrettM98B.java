package cf.nathanpb.RustCrafto.item.weapons;

import cf.nathanpb.RustCrafto.PlayerInfo;
import cf.nathanpb.RustCrafto.guns.GunAK47;
import cf.nathanpb.RustCrafto.guns.GunBarrettM98B;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by nathanpb on 8/17/17.
 */
public class ItemWeaponBarrettM98B extends RustCraftItem {
    public ItemWeaponBarrettM98B(){
        super();
        material = Material.DIAMOND_SPADE;
        matchDurability = true;
        durability = 2;
        name = ChatColor.GOLD+"Barrett M98B";
        hideDurability = true;
    }
    @Override
    protected void onRightClick(PlayerInteractEvent e){
        new GunBarrettM98B(e.getPlayer()).tryToShot(e.getPlayer());
    }

    @Override
    protected void onServerTick() {
        super.onServerTick();
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(instanceOf(p.getItemInHand())) PlayerInfo.gunInHand.replace(p, new GunBarrettM98B(p)); else PlayerInfo.gunInHand.replace(p, null);
        }
    }
}
