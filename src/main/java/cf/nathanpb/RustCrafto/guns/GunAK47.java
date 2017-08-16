package cf.nathanpb.RustCrafto.guns;

import cf.nathanpb.RustCrafto.bullets.Bullet762;
import cf.nathanpb.RustCrafto.bullets.Bullet9mm;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by nathanpb on 8/15/17.
 */
public class GunAK47 extends Gun {
    public GunAK47(ItemStack item, Player p){
        super(CraftItemStack.asNMSCopy(item));
        setMagazine(10);
        setBullet(new Bullet762());
        setMaxMagazine(30);
        setReloadingTime(20*5);
        recoilUp = 9;
        recoilRight = 4;
    }
    public void get(Inventory i){
        i.addItem(CraftItemStack.asBukkitCopy(getStack()));
    }
}
