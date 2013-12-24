package mcp.mobius.waila.handlers.hud;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.tools.ModIdentification;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import codechicken.nei.forge.GuiContainerManager;

public class HUDHandlerWaila implements IWailaDataProvider {

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor,	IWailaConfigHandler config) {
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,	IWailaConfigHandler config) {

        String name = null;
        try
        {
            String s = GuiContainerManager.itemDisplayNameShort(itemStack);
            if(s != null && !s.endsWith("Unnamed"))
                name = s;

            if(name != null)
                currenttip.add(name);
        }
        catch(Exception e)
        {
        }

        if(itemStack.getItem() == Item.redstone)
        {
            int md = accessor.getMetadata();
            String s = ""+md;
            if(s.length() < 2)
                s=" "+s;
            currenttip.set(currenttip.size()-1, name+" "+s);
        }		
		
		if (currenttip.size() == 0)
			currenttip.add("< Unnamed >");
		else{
			name = currenttip.get(0);
			currenttip.set(0, name + String.format(" %s:%s", accessor.getBlockID(), accessor.getMetadata()));
		}		
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,	IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,	IWailaConfigHandler config) {
		String modName = ModIdentification.nameFromStack(itemStack);
		if (modName != null && !modName.equals(""))
			currenttip.add("\u00A79\u00A7o" + modName);
		
		return currenttip;
	}

	/*
	@Override
	public ItemStack identifyHighlight(World world, EntityPlayer player, MovingObjectPosition mop) {
		return null;
	}

	@Override
	public List<String> handleTextData(ItemStack itemStack, World world, EntityPlayer player, MovingObjectPosition mop,	List<String> currenttip, Layout layout) {
		
		if (layout == Layout.FOOTER){
			String modName = ModIdentification.nameFromStack(itemStack);
			if (modName != null && !modName.equals(""))
				currenttip.add("\u00A79\u00A7o" + modName);
		} else if (layout == Layout.HEADER && ConfigHandler.instance().getConfig(Constants.CFG_WAILA_METADATA, false)){
			if (currenttip.size() == 0)
				currenttip.add("< Unnamed >");
			else{
				String name = currenttip.get(0);
				currenttip.set(0, name + String.format(" %s:%s", world.getBlockId(mop.blockX, mop.blockY, mop.blockZ), world.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ)));
			}
		} 
		return currenttip;		
	}		
	*/
}
