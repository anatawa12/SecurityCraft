package net.geforcemods.securitycraft.items;

import net.geforcemods.securitycraft.api.IOwnable;
import net.geforcemods.securitycraft.api.IPasswordProtected;
import net.geforcemods.securitycraft.gui.GuiHandler;
import net.geforcemods.securitycraft.main.mod_SecurityCraft;
import net.geforcemods.securitycraft.util.PlayerUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemUniversalKeyChanger extends Item {

	public ItemUniversalKeyChanger() {
		super();
	}
	
	@Override
	public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		if(!world.isRemote) {
        	if(world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof IPasswordProtected) {
        		if(((IOwnable) world.getTileEntity(pos)).getOwner().isOwner(player)) {
    			    player.openGui(mod_SecurityCraft.instance, GuiHandler.KEY_CHANGER_GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
    			}
    			else {
					PlayerUtils.sendMessageToPlayer(player, I18n.format("item.universalKeyChanger.name"), I18n.format("messages.notOwned").replace("#", ((IOwnable) world.getTileEntity(pos)).getOwner().getName()), TextFormatting.RED);
    			}
        		
    			return EnumActionResult.SUCCESS;
        	}
        }
        
        return EnumActionResult.FAIL;
    }

}
