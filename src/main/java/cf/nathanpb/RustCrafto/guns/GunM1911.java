package cf.nathanpb.RustCrafto.guns;

import cf.nathanpb.RustCrafto.bullets.BulletDot45;
import net.minecraft.server.v1_9_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Player;

/**
 * Created by nathanpb on 8/16/17.
 */
public class GunM1911 extends Gun{
    public GunM1911(Player p){
        super( ((EntityPlayer) ((((CraftEntity)p).getHandle()))).getItemInMainHand());
        setBullet(BulletDot45.class);
        setMaxMagazine(7);
        setReloadingTime(70);
        recoilUp = 4;
        recoilRight = 2;
    }
}
