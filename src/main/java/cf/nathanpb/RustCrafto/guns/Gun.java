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

    protected ProjectMetadata profile;
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
            profile = new ProjectMetadata(getUUID().toString(), Core.WeaponsDatabase);
        }
    }

    public void tryToShot(HumanEntity en){
        if(isReloading()) return;
        if(getMagazine() <= 0){
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
        Bukkit.getScheduler().runTaskLater(Core.getInstance(),  ()->{
                setReloading(false);
                setMagazine(getMaxMagazine());
            }, getReloadingTime());
    }

    public void setBullet(Class<? extends Bullet> bullet) {
        try{
            profile.put("BULLET_TYPE", bullet.getName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setMagazine(Integer ammo) {
        try {
            profile.put("AMMO", ammo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setMaxMagazine(Integer maxMagazine) {
        try {
            profile.put("MAX_AMMO", maxMagazine);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setReloadingTime(Integer Rt){
        try {
            profile.put("RELOADING_TIME", Rt);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setReloading(Boolean flag){
        try {
            profile.put("RELOADING", flag);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Class<? extends Bullet> getBullet() {
        try {
            if (!profile.hasKey("BULLET_TYPE")) profile.put("BULLET_TYPE", Bullet9mm.class.getName());
            return (Class<? extends Bullet>)Class.forName((String) profile.get("BULLET_TYPE", String.class));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Long getUUID(){
        return getStack().getTag().getLong("UUID");
    }
    public Integer getMagazine() {
        try {
            if (!profile.hasKey("AMMO")) profile.put("AMMO", 0);
            return  profile.get("AMMO", Integer.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Integer getMaxMagazine() {
        try {
            if (!profile.hasKey("MAX_AMMO")) profile.put("MAX_AMMO", 10);
            return profile.get("MAX_AMMO", Integer.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public ItemStack getStack() {
        return this.item;
    }
    public Integer getReloadingTime(){
        try {
            if (!profile.hasKey("RELOADING_TIME")) profile.put("RELOADING_TIME", 20);
            return profile.get("RELOADING_TIME", Integer.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Boolean isReloading(){
        try {
            if (!profile.hasKey("RELOADING")) profile.put("RELOADING", false);
            return profile.get("RELOADING", Boolean.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void applyRecoil(HumanEntity p){
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

}
