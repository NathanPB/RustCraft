package cf.nathanpb.RustCrafto.item.bullets;

import cf.nathanpb.RustCrafto.Core;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

/**
 * Created by nathanpb on 8/14/17.
 */
public class Bullet {
    private double damage;
    private double gravity;
    private Firework projectile;
    private Location spawnLocation;
    private Vector velocity;

    private BukkitTask gravityRunnable;

    protected double getDamage() {
        return damage;
    }
    protected double getGravity() {
        return gravity;
    }

    protected Bullet setDamage(double damage) {
        this.damage = damage;
        return this;
    }
    protected Bullet setGravity(double gravity) {
        this.gravity = gravity;
        return this;
    }

    public void spawn(Location spawnLocation){
        this.spawnLocation = spawnLocation;
        Vector looking = spawnLocation.getDirection().multiply(2);
        projectile = (Firework) spawnLocation.getWorld().spawnEntity(spawnLocation.add(looking.getX(), looking.getY(), looking.getZ()), EntityType.FIREWORK);
        projectile.setGravity(false);
        projectile.setVelocity(looking);
        applyGravity();
    }
    public void despawn(){
        projectile.remove();
    }
    private boolean exists(){
        return projectile.isDead();
    }

    private void applyGravity(){
        gravityRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                for(Entity e : projectile.getNearbyEntities(1, 1, 1)){
                    if(e instanceof LivingEntity){
                        if(isTargetDamageable((LivingEntity)e)){
                            applyDamage((LivingEntity)e);
                            despawn();
                        }
                    }
                }
                if(!exists()){
                    this.cancel();
                    return;
                }
                double newY = projectile.getLocation().getY() - gravity;
                Location loc = projectile.getLocation();
                loc.setY(newY);
                projectile.teleport(loc);
            }
        }.runTaskTimer(Core.getInstance(), 1, 0);
    }

    protected boolean isTargetDamageable(LivingEntity e){
        return true;
    }
    protected void applyDamage(LivingEntity e){
        e.damage(getDamage());
    }
}
