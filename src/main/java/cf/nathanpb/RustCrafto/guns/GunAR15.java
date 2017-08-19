package cf.nathanpb.RustCrafto.guns;

import cf.nathanpb.RustCrafto.bullets.Bullet556;
import net.minecraft.server.v1_9_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Player;

/**
 * Created by nathanpb on 8/16/17.
 */
public class GunAR15 extends Gun{
    public GunAR15(Player p){
        super( ((EntityPlayer) ((((CraftEntity)p).getHandle()))).getItemInMainHand());
        setMaxMagazine(20);
        setBullet(Bullet556.class);
        setReloadingTime(20*4);
        recoilUp = 4;
        recoilRight = 4;
    }
}
