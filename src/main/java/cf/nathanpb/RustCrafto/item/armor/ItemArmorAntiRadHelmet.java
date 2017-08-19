package cf.nathanpb.RustCrafto.item.armor;

import cf.nathanpb.RustCrafto.Radiation;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.LeatherArmorMeta;

/**
 * Created by nathanpb on 8/19/17.
 */
public class ItemArmorAntiRadHelmet extends RustCraftItem implements Listener{
    public ItemArmorAntiRadHelmet(){
        super();
        material = Material.LEATHER_HELMET;
        name = ChatColor.GOLD+"AntiRad Helmet";
        LeatherArmorColor = Color.ORANGE;
    }

    @Override
    protected void onServerTick() {
        super.onServerTick();
        for(Player p : Bukkit.getOnlinePlayers()){
            if(isWearing(p)) Radiation.removeRadiation(p, (float) 0.25);
        }
    }
}
