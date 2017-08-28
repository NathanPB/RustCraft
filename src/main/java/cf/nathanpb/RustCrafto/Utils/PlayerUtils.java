package cf.nathanpb.RustCrafto.Utils;

import cf.nathanpb.ProjectMetadata.ProjectMetadata;
import cf.nathanpb.RustCrafto.Core;
import cf.nathanpb.RustCrafto.Radiation;
import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.NBTTagCompound;
import net.minecraft.server.v1_9_R1.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
        System.out.println(p.getItemInHand());
        if(!p.getItemInHand().equals(Material.AIR) && p.getItemInHand() != null &&
                CraftItemStack.asNMSCopy(p.getItemInHand()).getTag() != null) {

            if (CraftItemStack.asNMSCopy(p.getItemInHand()).getTag().hasKey("UUID")) {
                try {
                    ProjectMetadata pm = new ProjectMetadata(CraftItemStack.asNMSCopy(p.getItemInHand()).getTag().getLong("UUID")+"", Core.WeaponsDatabase);
                    if (pm.hasKey("RELOADING") && pm.get("RELOADING", Boolean.class)) {
                        s += " - Ammo: " + ChatColor.GOLD + "RELOADING";
                    } else if(pm.hasKey("AMMO") && pm.hasKey("MAX_AMMO")) {
                        s += " - Ammo: " + ChatColor.GOLD + pm.get("AMMO", Integer.class) + "/" + pm.get("MAX_AMMO", Integer.class);
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
