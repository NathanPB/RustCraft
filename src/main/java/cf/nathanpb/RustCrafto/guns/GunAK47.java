package cf.nathanpb.RustCrafto.guns;

import cf.nathanpb.RustCrafto.bullets.Bullet762;
import net.minecraft.server.v1_9_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Player;

/**
 * Created by nathanpb on 8/15/17.
 */
public class GunAK47 extends Gun {
    public GunAK47(Player p){
        super( ((EntityPlayer) ((((CraftEntity)p).getHandle()))).getItemInMainHand());
        setBullet(Bullet762.class);
        setMaxMagazine(30);
        setReloadingTime(20*5);
        recoilUp = 9;
        recoilRight = 4;
    }
}
