package cf.nathanpb.RustCrafto.guns;

import cf.nathanpb.RustCrafto.bullets.Bullet9mm;
import net.minecraft.server.v1_9_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Player;

/**
 * Created by nathanpb on 8/16/17.
 */
public class GunMP5 extends Gun{
    public GunMP5(Player p){
        super( ((EntityPlayer) ((((CraftEntity)p).getHandle()))).getItemInMainHand());
        setBullet(Bullet9mm.class);
        setMaxMagazine(24);
        setReloadingTime(20*5);
        recoilUp = 2;
        recoilRight = 1;
    }
}
