package cf.nathanpb.RustCrafto.item.guns;

import cf.nathanpb.RustCrafto.bullets.Bullet9mm;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by nathanpb on 8/15/17.
 */
public class MyGun extends Gun implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        new MyGun(new ItemStack(Material.GOLD_AXE), Bukkit.getPlayerExact(commandSender.getName()));
        return true;
    }
    public MyGun(){}
    public MyGun(ItemStack item, Player p){
        super(CraftItemStack.asNMSCopy(item));
        setMagazine(10);
        setBullet(new Bullet9mm());
        setMaxMagazine(10);
        setReloadingTime(20*5);
        p.getInventory().addItem(CraftItemStack.asBukkitCopy(getStack()));
    }
}
