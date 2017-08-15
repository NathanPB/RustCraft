package cf.nathanpb.RustCrafto.item.bullets;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by nathanpb on 8/14/17.
 */
public class TestBullet extends Bullet implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        new TestBullet(Bukkit.getPlayerExact(commandSender.getName()).getLocation());
        return true;
    }
    public TestBullet(){};
    public TestBullet(Location loc){
        setGravity(0.5).setDamage(10);
        spawn(loc);
    }
}
