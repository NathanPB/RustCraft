package cf.nathanpb.RustCrafto.guns;

import cf.nathanpb.RustCrafto.bullets.Bullet338;
import net.minecraft.server.v1_9_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Player;

/**
 * Created by nathanpb on 8/16/17.
 */
public class GunBarrettM98B extends Gun{
    public GunBarrettM98B(Player p){
        super( ((EntityPlayer) ((((CraftEntity)p).getHandle()))).getItemInMainHand());
        setMaxMagazine(1);
        setBullet(Bullet338.class);
        setReloadingTime(20*3);
        recoilUp = 34;
        recoilRight = 3;
    }
}
