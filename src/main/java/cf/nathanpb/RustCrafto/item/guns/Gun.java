package cf.nathanpb.RustCrafto.item.guns;

import cf.nathanpb.RustCrafto.Core;
import cf.nathanpb.RustCrafto.bullets.Bullet;
import net.minecraft.server.v1_8_R1.ItemStack;
import net.minecraft.server.v1_8_R1.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;


/**
 * Created by nathanpb on 8/14/17.
 */
public class Gun implements Listener{
    private ItemStack item;
    private boolean reloading = false;

    protected Gun(){}
    protected Gun(ItemStack stack){
        Bukkit.getServer().getPluginManager().registerEvents(this, Core.getInstance());
        this.item = stack;
        if(stack.getTag() == null){
            stack.setTag(new NBTTagCompound());
        }
        if(!stack.getTag().hasKey("AMMO")){
            setMagazine(10);
            setMaxMagazine(10);
        }
    }

    @EventHandler
    public void click(PlayerInteractEvent e){
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (CraftItemStack.asNMSCopy(e.getItem()).getTag().hasKey("AMMO")) tryToShot(e.getPlayer());
        }
    }

    protected void tryToShot(HumanEntity en){
        if(reloading) return;
        if(getMagazine() <= 0){
            reload(en);
            return;
        }
        shot(en);
    }
    protected void shot(HumanEntity en){
        getBullet().spawn(en.getEyeLocation());
        this.setMagazine(getMagazine()-1);
    }
    protected void reload(HumanEntity e){
        reloading = true;
        new BukkitRunnable(){
            @Override
            public void run() {
                reloading = false;
                setMagazine(getMaxMagazine());
                e.sendMessage("recarregado");
                this.cancel();
            }
        }.runTaskTimer(Core.getInstance(), getReloadingTime(),0);
    }

    protected void setBullet(Bullet bullet) {
        this.item.getTag().setString("BULLET_TYPE", bullet.getClass().getName());
    }
    protected void setMagazine(Integer magazine) {
        this.item.getTag().remove("AMMO");
        this.item.getTag().setInt("AMMO", magazine);
    }
    protected void setMaxMagazine(Integer maxMagazine) {
        this.item.getTag().remove("max_AMMO");
        this.item.getTag().setInt("MAX_AMMO", maxMagazine);
    }
    protected void setReloadingTime(Integer Rt){
        this.item.getTag().remove("RELOADING_TIME");
        this.item.getTag().setInt("RELOADING_TIME", Rt);
    }

    protected Bullet getBullet() {
        if(!this.item.getTag().hasKey("BULLET_TYPE")) return null;
        try {
            return (Bullet) Class.forName(this.item.getTag().getString("BULLET_TYPE")).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    protected Integer getMagazine() {
        if(!this.item.getTag().hasKey("AMMO")) return null;
        return this.item.getTag().getInt("AMMO");
    }
    protected Integer getMaxMagazine() {
        if(!this.item.getTag().hasKey("MAX_AMMO")) return null;
        return this.item.getTag().getInt("MAX_AMMO");
    }
    protected ItemStack getStack() {
        return item;
    }
    protected Integer getReloadingTime(){
        if(!this.item.getTag().hasKey("RELOADING_TIME")) return null;
        return this.item.getTag().getInt("RELOADING_TIME");
    }
}
