package cf.nathanpb.RustCrafto.Utils;

import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;

/**
 * Created by nathanpb on 8/18/17.
 */
public class PlayerUtils {
    public static void sendActionbar(LivingEntity p, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" +
                ChatColor.translateAlternateColorCodes('&', message) + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
    }
}
