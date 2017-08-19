package cf.nathanpb.RustCrafto.guns;

import cf.nathanpb.RustCrafto.bullets.Bullet9mm;
import net.minecraft.server.v1_9_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Player;

/**
 * Created by nathanpb on 8/16/17.
 */
public class GunBerettaM9 extends Gun{
    public GunBerettaM9(Player p){
        super( ((EntityPlayer) ((((CraftEntity)p).getHandle()))).getItemInMainHand());
        setMaxMagazine(15);
        setBullet(Bullet9mm.class);
        setReloadingTime(20*3);
        recoilUp = 3;
    }
}
