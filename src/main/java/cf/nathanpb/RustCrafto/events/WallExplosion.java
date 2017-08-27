package cf.nathanpb.RustCrafto.events;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 * Created by nathanpb on 8/27/17.
 */
public class WallExplosion implements Listener{
    @EventHandler
    public static void onExplode(ExplosionPrimeEvent e){
        e.setCancelled(true);
        sendRustCraftExplosion(e.getEntity().getLocation());
    }
    public static void sendRustCraftExplosion(Location l){
        Firework fw = (Firework) l.getWorld().spawnEntity(l, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
        FireworkEffect effect = FireworkEffect.builder()
                .flicker(false)
                .withColor(Color.ORANGE)
                .withFade(Color.RED)
                .with(FireworkEffect.Type.BALL)
                .trail(false)
                .build();
        fwm.addEffect(effect);
        fwm.setPower(0);
        fw.setFireworkMeta(fwm);
        fw.detonate();
        for(int x=-3; x<=3; x++){
            for(int y=-3; y<=3; y++){
                for(int z=-3; z<=3; z++){
                    Location l2 = new Location(l.getWorld(), l.getX()+x, l.getY()+y, l.getZ()+z);
                    l2.getBlock().setType(Material.BEDROCK);
                }
            }
        }
    }
}
