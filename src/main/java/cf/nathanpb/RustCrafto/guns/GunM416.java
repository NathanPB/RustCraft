package cf.nathanpb.RustCrafto.guns;

import cf.nathanpb.RustCrafto.bullets.Bullet556;
import net.minecraft.server.v1_9_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Player;

/**
 * Created by nathanpb on 8/16/17.
 */
public class GunM416 extends Gun{
    public GunM416(Player p){
        super( ((EntityPlayer) ((((CraftEntity)p).getHandle()))).getItemInMainHand());
        setBullet(Bullet556.class);
        setMaxMagazine(30);
        setReloadingTime(70);
        recoilUp = 4;
        recoilRight = 2;
    }
}
