package cf.nathanpb.RustCrafto.guns;

import cf.nathanpb.RustCrafto.bullets.Bullet762;
import net.minecraft.server.v1_9_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Player;

/**
 * Created by nathanpb on 8/16/17.
 */
public class GunScarH extends Gun{
    public GunScarH(Player p){
        super( ((EntityPlayer) ((((CraftEntity)p).getHandle()))).getItemInMainHand());
        setBullet(Bullet762.class);
        setMaxMagazine(20);
        setReloadingTime(20*2);
        recoilUp = 12;
        recoilRight = 2;
    }
}
