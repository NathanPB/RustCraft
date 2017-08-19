package cf.nathanpb.RustCrafto.bullets;

import cf.nathanpb.RustCrafto.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

/**
 * Created by nathanpb on 8/14/17.
 */
public class Bullet implements Listener{
    private double damage;
    private double gravity;
    private Arrow projectile;
    private Location spawnLocation;

    protected BukkitTask gravityRunnable;
    public Bullet(){
        Bukkit.getServer().getPluginManager().registerEvents(this, Core.getInstance());
    }

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
        projectile = (Arrow) spawnLocation.getWorld().spawnEntity(spawnLocation.add(looking.getX(), looking.getY(), looking.getZ()), EntityType.ARROW);
        projectile.setVelocity(looking);
        applyGravity();
        playTriggerEffect();
    }
    public void despawn(){
        projectile.remove();
    }
    private boolean exists(){
        return !projectile.isDead();
    }

    private void applyGravity(){
        gravityRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                projectile.setVelocity(projectile.getVelocity().setY(projectile.getVelocity().getY()+.045));
                playPathEffect();
                if(!exists()){
                    this.cancel();
                    return;
                }
                Location loc = projectile.getLocation();
                loc.setY(loc.getY() - gravity);
                projectile.teleport(loc);
            }
        }.runTaskTimer(Core.getInstance(), 1, 1);
    }

    protected void onHitEntity(Entity e){
        if(e instanceof LivingEntity){
            if(isTargetDamageable((LivingEntity) e)){
                applyDamage((LivingEntity)e);
            }
            playHitEffect();
            gravityRunnable.cancel();
            despawn();
        }
    }

    protected void onHitBlock(Block b){
        gravityRunnable.cancel();
        despawn();
    };

    protected boolean isTargetDamageable(LivingEntity e){
        return true;
    }
    protected void applyDamage(LivingEntity e){
        e.damage(getDamage());
    }

    @EventHandler
    public void onEvent(ProjectileHitEvent e){
        if(e.getEntity().equals(projectile)) {
            BlockIterator iterator = new BlockIterator(e.getEntity().getWorld(), e.getEntity().getLocation().toVector(), e.getEntity().getVelocity().normalize(), 0, 4);
            Block hitBlock = null;
            while(iterator.hasNext()) {
                hitBlock = iterator.next();
                if(hitBlock.getTypeId()!=0) onHitBlock(hitBlock);
            }
        }
    }
    @EventHandler
    public void onEvent2(EntityDamageByEntityEvent e){
        if(e.getDamager().equals(this.projectile) && e.getEntity() instanceof LivingEntity) {
            e.setCancelled(true);
            onHitEntity(e.getEntity());
        }
    }

    protected void playTriggerEffect(){
        projectile.getWorld().spawnParticle(Particle.SMOKE_LARGE, projectile.getLocation(), 5);
        projectile.getWorld().playSound(spawnLocation, Sound.ENTITY_FIREWORK_BLAST, 10, 10);
    }
    protected void playPathEffect(){
        projectile.getWorld().spawnParticle(Particle.SMOKE_NORMAL, projectile.getLocation(), 5);
    }
    protected void playHitEffect(){

    }

}
