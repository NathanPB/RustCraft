package cf.nathanpb.RustCrafto.bullets;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

/**
 * Created by nathanpb on 8/17/17.
 */
public class BulletDot50 extends Bullet{
    public BulletDot50(){
        setGravity(0.071428571); //35b
        setDamage(20);
    }

    @Override
    protected void onHitBlock(Block b) {
        super.onHitBlock(b);
        b.getWorld().createExplosion(b.getLocation(), (float)0.3);
    }

    @Override
    protected void onHitEntity(Entity e) {
        super.onHitEntity(e);
        e.getWorld().createExplosion(e.getLocation(), (float)0.3);
    }
}
