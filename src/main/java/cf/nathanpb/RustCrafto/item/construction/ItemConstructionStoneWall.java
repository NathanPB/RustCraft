package cf.nathanpb.RustCrafto.item.construction;

import cf.nathanpb.RustCrafto.item.RustCraftItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by nathanpb on 8/27/17.
 */
public class ItemConstructionStoneWall extends RustCraftItem{
    public ItemConstructionStoneWall(){
        super();
        material = Material.COBBLESTONE;
        name = ChatColor.BLUE+"Bloco de Pedra";
    }

    @Override
    protected void onBlockBreak(BlockBreakEvent e) {
        super.onBlockBreak(e);
        if(!(e.getPlayer() != null && e.getPlayer().getGameMode().ordinal() == 1)) e.setCancelled(true);
    }
}
