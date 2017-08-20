package cf.nathanpb.RustCrafto;

import cf.nathanpb.ProjectMetadata.ProjectMetadata;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by nathanpb on 8/19/17.
 */
public class Radiation {
    public static void init(){
        new BukkitRunnable(){
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    for(BlockState bs : p.getLocation().getChunk().getTileEntities()){
                        if(bs instanceof Sign){
                            if(((Sign)bs).getLine(0).contains("[RUSTCRAFT]")){
                                String[] s2 = ((Sign)bs).getLine(1).split("=");
                                if(s2[0].contains("RAD")){
                                    addRadiation(p, Integer.valueOf(s2[1]));
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimerAsynchronously(Core.getInstance(), 0, 1);
    }
    public static void addRadiation(Player p, float rad){
        ProjectMetadata p2 = new ProjectMetadata(p.getUniqueId().toString(), Core.PlayerProfileDatabase);
        if(!p2.hasKey("RADIATION")) p2.put("RADIATION", 0);
        p2.put("RADIATION", p2.get("RADIATION", Integer.class)+rad);
    }
    public static void removeRadiation(Player p, float rad){
        ProjectMetadata p2 = new ProjectMetadata(p.getUniqueId().toString(), Core.PlayerProfileDatabase);
        if(!p2.hasKey("RADIATION")) p2.put("RADIATION", 0.0);
        if(p2.get("RADIATION", Integer.class)-rad < 0) return;
        p2.put("RADIATION", p2.get("RADIATION", Integer.class)-rad);
    }
    public static Integer getRadiation(Player p){
        ProjectMetadata p2 = new ProjectMetadata(p.getUniqueId().toString(), Core.PlayerProfileDatabase);
        if(!p2.hasKey("RADIATION")) p2.put("RADIATION", 0);
        return p2.get("RADIATION", Integer.class);
    }
}
