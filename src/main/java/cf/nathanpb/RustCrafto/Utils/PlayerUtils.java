package cf.nathanpb.RustCrafto.Utils;

import cf.nathanpb.RustCrafto.PlayerInfo;
import cf.nathanpb.RustCrafto.Radiation;
import cf.nathanpb.RustCrafto.commands.RustCraft;
import cf.nathanpb.RustCrafto.item.RustCraftItem;
import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

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
    public static void sendInformation(Player p){
        String s = ChatColor.BLUE+"Radiation: "+ChatColor.GOLD+ Radiation.getRadiation(p)+"%"+ChatColor.BLUE;
        if(PlayerInfo.gunInHand.get(p) != null) {
            s += " - Ammo: " + ChatColor.GOLD +PlayerInfo.gunInHand.get(p).getMagazine()+"/"+PlayerInfo.gunInHand.get(p).getMaxMagazine();
        }
        sendActionbar(p, s);
    }
}
