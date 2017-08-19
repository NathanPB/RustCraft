package cf.nathanpb.RustCrafto.guns;

import cf.nathanpb.RustCrafto.bullets.BulletDot50;
import net.minecraft.server.v1_9_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Player;

/**
 * Created by nathanpb on 8/16/17.
 */
public class GunDeagle extends Gun{
    public GunDeagle(Player p){
        super( ((EntityPlayer) ((((CraftEntity)p).getHandle()))).getItemInMainHand());
        setMaxMagazine(7);
        setBullet(BulletDot50.class);
        setReloadingTime(20*2);
        recoilUp = 6;
        recoilRight = 2;
    }
}
