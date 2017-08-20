package cf.nathanpb.RustCrafto;

import cf.nathanpb.RustCrafto.Utils.PlayerUtils;
import cf.nathanpb.RustCrafto.commands.RustCraft;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.Timer;

/**
 * Created by nathanpb on 8/14/17.
 */
public class Core extends JavaPlugin{
    public static Core getInstance(){
        return (Core) Bukkit.getServer().getPluginManager().getPlugin("RustCraft");
    }
    public static final File PlayerProfileDatabase = new File("ProjectMetadataDatabase/PlayerProfile");
    @Override
    public void onEnable() {
        super.onEnable();
        if(!PlayerProfileDatabase.exists()) PlayerProfileDatabase.mkdirs();
        this.getCommand("rustcraft").setExecutor(new RustCraft());
        RustCraftItem.init();
        new BukkitRunnable(){
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    PlayerUtils.sendInformation(p);
                }
            }
        }.runTaskTimerAsynchronously(getInstance(), 1, 0);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
