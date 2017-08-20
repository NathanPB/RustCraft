package cf.nathanpb.RustCrafto;

import cf.nathanpb.RustCrafto.guns.Gun;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by nathanpb on 8/20/17.
 */
public class PlayerInfo {
    public static HashMap<Player, Gun> gunInHand = new HashMap<>();
}
