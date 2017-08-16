package cf.nathanpb.RustCrafto.item;

import cf.nathanpb.RustCrafto.Core;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nathanpb on 8/15/17.
 */
public class RustCraftItem implements Listener{

    protected Material material = Material.AIR;
    protected String name = "";
    protected byte data = 0;
    protected List<Enchantment> enchantments = new ArrayList<>();
    public ItemStack get(){
        ItemStack item = new ItemStack(material);
        MaterialData data = item.getData();
        data.setData(this.data);
        item.setData(data);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
    protected RustCraftItem(){
        itemHashMap.put(this.getClass(), this);
    }

    @EventHandler
    public void onPIE(PlayerInteractEvent e) throws Exception{
        if(!(e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName())) return;
        if(!e.getItem().getType().equals(material)) return;
        if(e.getItem().getData().getData() != data) return;
        if(!e.getItem().getItemMeta().getDisplayName().equals(name)) return;
        if(e.getAction().name().contains("RIGHT_CLICK_")) onRightClick(e);
        if(e.getAction().name().contains("LEFT_CLICK_")) onLeftClick(e);
    }
    private static List<Class> getAllItems(){
        return new ArrayList<>(new Reflections("cf.nathanpb.RustCrafto.item").getSubTypesOf(RustCraftItem.class));
    }
    private static Method getMethod(Class c, Class an){
        for(Method m : c.getDeclaredMethods()){
            if(m.isAnnotationPresent(an)) return m;
        }
        return null;
    }

    private static HashMap<Class, RustCraftItem> itemHashMap = new HashMap<>();
    public static <T>T getInstance(Class<T> c){
        return (T)itemHashMap.get(c);
    }
    public static void init(){
        try {
            for (Class c : getAllItems()) {
                Bukkit.getServer().getPluginManager().registerEvents((Listener) c.newInstance(), Core.getInstance());
                Bukkit.getLogger().info("[RustCraft] Registered RustCraftItem "+c.getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void onRightClick(PlayerInteractEvent e){};
    protected void onLeftClick(PlayerInteractEvent e){};
}
