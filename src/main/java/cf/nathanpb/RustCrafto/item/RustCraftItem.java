package cf.nathanpb.RustCrafto.item;

import cf.nathanpb.ProjectMetadata.ProjectMetadata;
import cf.nathanpb.RustCrafto.Core;
import com.mojang.authlib.GameProfileRepository;
import jdk.internal.org.objectweb.asm.Opcodes;
import net.minecraft.server.v1_9_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_9_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by nathanpb on 8/15/17.
 */
public class RustCraftItem implements Listener{

    protected ProjectMetadata metadata = new ProjectMetadata(".blocks");
    protected Material material = Material.AIR;
    protected String name = "";
    protected byte data = 0;
    protected List<Enchantment> enchantments = new ArrayList<>();
    public ItemStack get(){
        ItemStack item = new ItemStack(material, 1, (short) 0, data);

        MaterialData data = item.getData();
        data.setData(this.data);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);

        if(LeatherArmorColor != null) {
            LeatherArmorMeta meta2 = (LeatherArmorMeta) meta;
            meta2.setColor(LeatherArmorColor);
            meta = meta2;
        }
        item.setItemMeta(meta);

        item.setData(data);
        if(hideDurability){
            net.minecraft.server.v1_9_R1.ItemStack i2 = CraftItemStack.asNMSCopy(item);
            if(i2.getTag().equals(null)) i2.setTag(new NBTTagCompound());
            i2.getTag().setBoolean("Unbreakable", true);
            item = CraftItemStack.asBukkitCopy(i2);
        }
        item.setDurability((short) durability);
        return item;
    }
    protected RustCraftItem(){
        itemHashMap.put(this.getClass(), this);
        onSTE();
    }
    protected boolean matchDurability = false;
    protected boolean matchData = false;
    protected int durability = 0;
    protected boolean hideDurability = false;
    protected Color LeatherArmorColor = null;

    @EventHandler
    public void onPIE(PlayerInteractEvent e) throws Exception{
        if(!instanceOf(e.getItem())) return;
        if(e.getAction().name().contains("RIGHT_CLICK_")) onRightClick(e);
        if(e.getAction().name().contains("LEFT_CLICK_")) onLeftClick(e);
    }
    @EventHandler
    public void onISE(ItemSpawnEvent e){
        if(!instanceOf(e.getEntity().getItemStack())) return;
        onDropped(e);
    }
    @EventHandler
    public void onPDIE(PlayerDropItemEvent e){
        if(!instanceOf(e.getItemDrop().getItemStack())) return;
        onDroppedByPlayer(e);
    }
    @EventHandler
    public void onICE(InventoryClickEvent e){
        if(!instanceOf(e.getCurrentItem())) return;
        onClickedOnInventory(e);
    }
    @EventHandler
    public void onBPE(BlockPlaceEvent e){
        if(instanceOf(e.getItemInHand())){
            onPlacedEvent(e);
        }
    }
    @EventHandler
    public void onBBE(BlockBreakEvent e){
        if(instanceOf(e.getBlock())){
            onBlockBreak(e);
        }
    };
    @EventHandler
    public void onIBE(PlayerItemDamageEvent e){
        if(instanceOf(e.getItem())) onDurabilityChange(e);
    }
    public void onSTE(){
        new BukkitRunnable(){
            @Override
            public void run() {
                onServerTick();
            }
        }.runTaskTimerAsynchronously(Core.getInstance(), 1, 0);
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
    public static RustCraftItem getInstanceFrom(Class c){
        return itemHashMap.get(c);
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

    protected void onRightClick(PlayerInteractEvent e){}
    protected void onLeftClick(PlayerInteractEvent e){}
    protected void onDropped(ItemSpawnEvent e){}
    protected void onDroppedByPlayer(PlayerDropItemEvent e){}
    protected void onClickedOnInventory(InventoryClickEvent e){}
    protected void onPlacedEvent(BlockPlaceEvent e){
        metadata.put(e.getBlock().toString(), this.getClass().getName());
    }
    protected void onBlockBreak(BlockBreakEvent e){
        metadata.removeKey(e.getBlock().toString());
    }
    protected void onDurabilityChange(PlayerItemDamageEvent e){};
    protected void onServerTick(){};

    public void add(Inventory i){
        i.addItem(get());
    }
    protected boolean instanceOf(ItemStack i){
        if(i == null)return false;
        if(!(i.hasItemMeta() && i.getItemMeta().hasDisplayName())) return false;
        if(!i.getType().equals(material)) return false;
        if (matchData) if(!((Byte)i.getData().getData()).equals(data)) return false;
        if(LeatherArmorColor != null) {
            if(!(i.getItemMeta() instanceof LeatherArmorMeta)) return false;
            LeatherArmorMeta meta = (LeatherArmorMeta) i.getItemMeta();
            if(!meta.getColor().equals(LeatherArmorColor)) return false;
        }
        if(!i.getItemMeta().getDisplayName().equals(name)) return false;
        if(matchDurability) if(!((Integer)((int)i.getDurability())).equals(((Integer) durability))) return false;
        return true;
    }
    protected boolean instanceOf(Block b){
        if(b == null) return false;
        if(!metadata.hasKey(b.toString())) return false;
        return metadata.get(b.toString(), String.class).equals(this.getClass().getName());
    }
    public static RustCraftItem getInstanceFrom(ItemStack i){
        for(Map.Entry<Class, RustCraftItem> entry : itemHashMap.entrySet()){
            if(entry.getValue().instanceOf(i)) return entry.getValue();
        }
        return null;
    }

    protected boolean isWearing(LivingEntity p){
        if(Arrays.asList(p.getEquipment().getArmorContents()).contains(get())) return true;
        return false;
    }
}
