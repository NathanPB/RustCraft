package cf.nathanpb.RustCrafto;

import cf.nathanpb.RustCrafto.commands.RustCraft;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

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
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
