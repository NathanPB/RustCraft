package cf.nathanpb.RustCrafto;

import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by nathanpb on 8/14/17.
 */
public class Core extends JavaPlugin{
    public static Core getInstance(){
        return (Core) Bukkit.getServer().getPluginManager().getPlugin("RustCraft");
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.getCommand("bullet").setExecutor(new getCommand());
        RustCraftItem.init();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
