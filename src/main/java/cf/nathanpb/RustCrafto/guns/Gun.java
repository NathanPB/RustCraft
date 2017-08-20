package cf.nathanpb.RustCrafto.guns;

import cf.nathanpb.ProjectMetadata.ProjectMetadata;
import cf.nathanpb.RustCrafto.Core;
import cf.nathanpb.RustCrafto.Utils.PlayerUtils;
import cf.nathanpb.RustCrafto.bullets.Bullet;
import cf.nathanpb.RustCrafto.bullets.Bullet9mm;
import net.minecraft.server.v1_9_R1.ItemStack;
import net.minecraft.server.v1_9_R1.NBTTagCompound;
import net.minecraft.server.v1_9_R1.PacketPlayOutEntity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Random;


/**
 * Created by nathanpb on 8/14/17.
 */
public class Gun{
    private ItemStack item;

    protected double recoilLeft = 0;
    protected double recoilRight = 0;
    protected double recoilUp = 0;
    protected double recoilDown = 0;
    protected JSONObject meta;

    protected ProjectMetadata profile = new ProjectMetadata(".weapons");
    protected Gun(ItemStack item){
        this.item = item;

        if(getStack().getTag().equals(null)){
            getStack().setTag(new NBTTagCompound());
        }
        if(!item.getTag().hasKey("UUID")){
            long l;
            do{
                l = new Random().nextLong();
            }while (l <= Integer.MAX_VALUE);
            getStack().getTag().setLong("UUID", l);
            profile.put(getUUID().toString(), new JSONObject());
        }
        this.meta = profile.get(getUUID().toString(), JSONObject.class);
    }

    public void tryToShot(HumanEntity en){
        PlayerUtils.sendActionbar(en, ChatColor.BLUE+"Ammo: "+ChatColor.GOLD+getMagazine()+ChatColor.BLUE+"/"+ChatColor.GOLD+getMaxMagazine());
        if(isReloading()) return;
        if(getMagazine() <= 0){
            ((Player)en).sendTitle(ChatColor.RED+"Magazine is empty!", "");
            reload(en);
            return;
        }
        shot(en);
    }
    protected void shot(HumanEntity en){
        try {
            getBullet().newInstance().spawn(en.getEyeLocation());
            setMagazine(getMagazine() - 1);
            applyRecoil(en);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void reload(HumanEntity e){
        setReloading(true);
        ((Player)e).sendTitle("", ChatColor.YELLOW+"Reloading gun...");
        Bukkit.getScheduler().runTaskLater(Core.getInstance(),  ()->{
            ((Player)e).sendTitle(ChatColor.GREEN + "Gun reloaded!", "");
                setReloading(false);
                setMagazine(getMaxMagazine());
            }, getReloadingTime());
    }

    protected void setBullet(Class<? extends Bullet> bullet) {
        try{
            meta.put("BULLET_TYPE", bullet.getName());
            update();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void setMagazine(Integer ammo) {
        try {
            meta.put("AMMO", ammo);
            update();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void setMaxMagazine(Integer maxMagazine) {
        try {
            meta.put("MAX_AMMO", maxMagazine);
            update();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void setReloadingTime(Integer Rt){
        try {
            meta.put("RELOADING_TIME", Rt);
            update();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    protected void setReloading(Boolean flag){
        try {
            meta.put("RELOADING", flag);
            update();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected Class<? extends Bullet> getBullet() {
        try {
            if (!meta.has("BULLET_TYPE")) meta.put("BULLET_TYPE", Bullet9mm.class.getName());
            return (Class<? extends Bullet>)Class.forName((String) meta.get("BULLET_TYPE"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    protected Long getUUID(){
        return getStack().getTag().getLong("UUID");
    }
    protected Integer getMagazine() {
        try {
            if (!meta.has("AMMO")) meta.put("AMMO", 0);
            return (Integer) meta.get("AMMO");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    protected Integer getMaxMagazine() {
        try {
            if (!meta.has("MAX_AMMO")) meta.put("MAX_AMMO", 10);
            return (Integer) meta.get("MAX_AMMO");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    protected ItemStack getStack() {
        return this.item;
    }
    protected Integer getReloadingTime(){
        try {
            if (!meta.has("RELOADING_TIME")) meta.put("RELOADING_TIME", 20);
            return (Integer) meta.get("RELOADING_TIME");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    protected Boolean isReloading(){
        try {
            if (!meta.has("RELOADING")) meta.put("RELOADING", false);
            return (Boolean) meta.get("RELOADING");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    protected void applyRecoil(HumanEntity p){
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10, 0));
        float pitch = p.getLocation().getPitch();
        float yaw = p.getLocation().getYaw();
        yaw += recoilRight;
        yaw -= recoilLeft;
        pitch -= recoilUp;
        pitch += recoilDown;

        Location l = p.getLocation();
        l.setYaw(yaw);
        l.setPitch(pitch);
        p.teleport(l);
    }

    protected void update(){
        profile.put(getUUID().toString(), meta);
    }
}
