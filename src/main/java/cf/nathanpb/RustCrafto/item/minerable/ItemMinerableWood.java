package cf.nathanpb.RustCrafto.item.minerable;

import cf.nathanpb.RustCrafto.Core;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import cf.nathanpb.RustCrafto.item.ores.ItemOreWoodLog;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

/**
 * Created by nathanpb on 8/17/17.
 */
public class ItemMinerableWood extends RustCraftItem implements Listener{
    public ItemMinerableWood(){
        super();
        material = Material.LOG;
        name = ChatColor.BLACK+"OreWood";
    }
    @Override
    protected void onBlockBreak(BlockBreakEvent e) {
        super.onBlockBreak(e);
        Bukkit.broadcastMessage("quebrado");
        if(!e.getBlock().hasMetadata("cooldown")) {
            org.bukkit.inventory.ItemStack drop1 = RustCraftItem.getInstance(ItemOreWoodLog.class).get();
            drop1.setAmount(new Random().nextInt(5));
            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), drop1);
            e.getBlock().setMetadata("cooldown", new FixedMetadataValue(Core.getInstance(), 20 * 60 * 10));
            new BukkitRunnable() {
                @Override
                public void run() {
                    e.getBlock().removeMetadata("cooldown", Core.getInstance());
                }
            }.runTaskLater(Core.getInstance(), e.getBlock().getMetadata("cooldown").get(0).asInt());
        }
        if(!metadata.hasKey(e.getBlock().toString())) {
            metadata.put(e.getBlock().toString(), this.getClass().getName());
        }
        e.setCancelled(true);
    }
}
