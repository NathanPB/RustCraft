package cf.nathanpb.RustCrafto.Utils;

import cf.nathanpb.ProjectMetadata.ProjectMetadata;
import cf.nathanpb.RustCrafto.Radiation;
import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.NBTTagCompound;
import net.minecraft.server.v1_9_R1.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_9_R1.inventory.CraftItemStack;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.json.JSONObject;

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
        if(p.getItemInHand() != null && CraftItemStack.asNMSCopy(p.getItemInHand()).getTag() != null) {
            if (CraftItemStack.asNMSCopy(p.getItemInHand()).getTag().hasKey("UUID")) {
                try {
                    ProjectMetadata pm = new ProjectMetadata(".weapons");
                    JSONObject json = pm.get(CraftItemStack.asNMSCopy(p.getItemInHand()).getTag().getLong("UUID") + "", JSONObject.class);
                    if (json.has("RELOADING") && json.getBoolean("RELOADING")) {
                        s += " - Ammo: " + ChatColor.GOLD + "RELOADING";
                    } else {
                        s += " - Ammo: " + ChatColor.GOLD + json.getInt("AMMO") + "/" + json.getInt("MAX_AMMO");
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        sendActionbar(p, s);
    }
}
