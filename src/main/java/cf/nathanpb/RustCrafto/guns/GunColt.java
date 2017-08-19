package cf.nathanpb.RustCrafto.guns;


import cf.nathanpb.RustCrafto.bullets.Bullet38;
import net.minecraft.server.v1_9_R1.EntityPlayer;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Player;

/**
 * Created by nathanpb on 8/16/17.
 */
public class GunColt extends Gun{
    public GunColt(Player p){
        super( ((EntityPlayer) ((((CraftEntity)p).getHandle()))).getItemInMainHand());
        setMaxMagazine(6);
        setBullet(Bullet38.class);
        setReloadingTime(20*4);
        recoilUp = 6;
        recoilRight = 2;
    }
}
