package cf.nathanpb.RustCrafto.item.minerable;

import cf.nathanpb.RustCrafto.Core;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import cf.nathanpb.RustCrafto.item.ores.ItemOreIronOre;
import cf.nathanpb.RustCrafto.item.ores.ItemOreStone;
import cf.nathanpb.RustCrafto.item.ores.ItemOreSulphur;
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
public class ItemMinerableStone extends RustCraftItem implements Listener{
    public ItemMinerableStone(){
        super();
        material = Material.STONE;
        name = ChatColor.BLACK+"OreStone";
    }
    @Override
    protected void onBlockBreak(BlockBreakEvent e) {
        super.onBlockBreak(e);
        Bukkit.broadcastMessage("quebrado");
        if(!e.getBlock().hasMetadata("cooldown")) {
            org.bukkit.inventory.ItemStack drop1 = RustCraftItem.getInstance(ItemOreIronOre.class).get();
            org.bukkit.inventory.ItemStack drop2 = RustCraftItem.getInstance(ItemOreSulphur.class).get();
            org.bukkit.inventory.ItemStack drop3 = RustCraftItem.getInstance(ItemOreStone.class).get();
            drop1.setAmount(new Random().nextInt(3));
            drop2.setAmount(new Random().nextInt(4));
            drop3.setAmount(new Random().nextInt(7));
            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), drop1);
            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), drop2);
            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), drop3);
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
