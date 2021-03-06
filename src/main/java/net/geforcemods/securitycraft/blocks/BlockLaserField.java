package net.geforcemods.securitycraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.geforcemods.securitycraft.SCContent;
import net.geforcemods.securitycraft.SecurityCraft;
import net.geforcemods.securitycraft.api.CustomizableSCTE;
import net.geforcemods.securitycraft.misc.CustomDamageSources;
import net.geforcemods.securitycraft.misc.EnumCustomModules;
import net.geforcemods.securitycraft.util.EntityUtils;
import net.geforcemods.securitycraft.util.ModuleUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLaserField extends Block{

	@SideOnly(Side.CLIENT)
	private IIcon transparentIcon;

	public BlockLaserField(Material material) {
		super(material);
		setBlockBounds(0.250F, 0.300F, 0.300F, 0.750F, 0.700F, 0.700F);

	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube(){
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}


	/**
	 * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
	 */
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if(!world.isRemote && entity instanceof EntityLivingBase && !EntityUtils.doesMobHavePotionEffect((EntityLivingBase) entity, Potion.invisibility)){
			for(int i = 0; i < SecurityCraft.config.laserBlockRange; i++){
				Block id = world.getBlock(x + i, y, z);
				if(id == SCContent.laserBlock){
					if(world.getTileEntity(x + i, y, z) instanceof CustomizableSCTE && ((CustomizableSCTE) world.getTileEntity(x + i, y, z)).hasModule(EnumCustomModules.WHITELIST) && ModuleUtils.getPlayersFromModule(world, x + i, y, z, EnumCustomModules.WHITELIST).contains(((EntityLivingBase) entity).getCommandSenderName().toLowerCase()))
						return;
					world.setBlockMetadataWithNotify(x + i, y, z, 2, 3);
					world.notifyBlocksOfNeighborChange(x + i, y, z, SCContent.laserBlock);
					world.scheduleBlockUpdate(x + i, y, z, SCContent.laserBlock, 50);
					world.notifyBlocksOfNeighborChange(x + i, y, z, SCContent.laserBlock);

					if(world.getTileEntity(x + i, y, z) instanceof CustomizableSCTE && ((CustomizableSCTE) world.getTileEntity(x + i, y, z)).hasModule(EnumCustomModules.HARMING))
						((EntityLivingBase) entity).attackEntityFrom(CustomDamageSources.laser, 10F);
				}
			}

			for(int i = 0; i < SecurityCraft.config.laserBlockRange; i++){
				Block id = world.getBlock(x - i, y, z);
				if(id == SCContent.laserBlock){
					if(world.getTileEntity(x - i, y, z) instanceof CustomizableSCTE && ((CustomizableSCTE) world.getTileEntity(x - i, y, z)).hasModule(EnumCustomModules.WHITELIST) && ModuleUtils.getPlayersFromModule(world, x - i, y, z, EnumCustomModules.WHITELIST).contains(((EntityLivingBase) entity).getCommandSenderName().toLowerCase()))
						return;
					world.setBlockMetadataWithNotify(x - i, y, z, 2, 3);
					world.notifyBlocksOfNeighborChange(x - i, y, z, SCContent.laserBlock);
					world.scheduleBlockUpdate(x - i, y, z, SCContent.laserBlock, 50);
					world.notifyBlocksOfNeighborChange(x - i, y, z, SCContent.laserBlock);

					if(world.getTileEntity(x - i, y, z) instanceof CustomizableSCTE && ((CustomizableSCTE) world.getTileEntity(x - i, y, z)).hasModule(EnumCustomModules.HARMING))
						((EntityLivingBase) entity).attackEntityFrom(CustomDamageSources.laser, 10F);
				}
			}

			for(int i = 0; i < SecurityCraft.config.laserBlockRange; i++){
				Block id = world.getBlock(x, y, z + i);
				if(id == SCContent.laserBlock){
					if(world.getTileEntity(x, y, z + i) instanceof CustomizableSCTE && ((CustomizableSCTE) world.getTileEntity(x, y, z + i)).hasModule(EnumCustomModules.WHITELIST) && ModuleUtils.getPlayersFromModule(world, x, y, z + i, EnumCustomModules.WHITELIST).contains(((EntityLivingBase) entity).getCommandSenderName().toLowerCase()))
						return;
					world.setBlockMetadataWithNotify(x, y, z + i, 2, 3);
					world.notifyBlocksOfNeighborChange(x, y, z + i, SCContent.laserBlock);
					world.scheduleBlockUpdate(x, y, z + i, SCContent.laserBlock, 50);
					world.notifyBlocksOfNeighborChange(x, y, z + i, SCContent.laserBlock);

					if(world.getTileEntity(x, y, z + i) instanceof CustomizableSCTE && ((CustomizableSCTE) world.getTileEntity(x, y, z + i)).hasModule(EnumCustomModules.HARMING))
						((EntityLivingBase) entity).attackEntityFrom(CustomDamageSources.laser, 10F);
				}
			}

			for(int i = 0; i < SecurityCraft.config.laserBlockRange; i++){
				Block id = world.getBlock(x, y, z - i);
				if(id == SCContent.laserBlock){
					if(world.getTileEntity(x, y, z - i) instanceof CustomizableSCTE && ((CustomizableSCTE) world.getTileEntity(x, y, z - i)).hasModule(EnumCustomModules.WHITELIST) && ModuleUtils.getPlayersFromModule(world, x, y, z - i, EnumCustomModules.WHITELIST).contains(((EntityLivingBase) entity).getCommandSenderName().toLowerCase()))
						return;
					world.setBlockMetadataWithNotify(x, y, z - i, 2, 3);
					world.notifyBlocksOfNeighborChange(x, y, z - i, SCContent.laserBlock);
					world.scheduleBlockUpdate(x, y, z - i, SCContent.laserBlock, 50);
					world.notifyBlocksOfNeighborChange(x, y, z - i, SCContent.laserBlock);

					if(world.getTileEntity(x, y, z - i) instanceof CustomizableSCTE && ((CustomizableSCTE) world.getTileEntity(x, y, z - i)).hasModule(EnumCustomModules.HARMING))
						((EntityLivingBase) entity).attackEntityFrom(CustomDamageSources.laser, 10F);
				}
			}

			for(int i = 0; i < SecurityCraft.config.laserBlockRange; i++){
				Block id = world.getBlock(x, y + i, z);
				if(id == SCContent.laserBlock){
					if(world.getTileEntity(x, y + i, z ) instanceof CustomizableSCTE && ((CustomizableSCTE) world.getTileEntity(x, y + i, z)).hasModule(EnumCustomModules.WHITELIST) && ModuleUtils.getPlayersFromModule(world, x, y + i, z, EnumCustomModules.WHITELIST).contains(((EntityLivingBase) entity).getCommandSenderName().toLowerCase()))
						return;
					world.setBlockMetadataWithNotify(x, y + i, z , 2, 3);
					world.notifyBlocksOfNeighborChange(x, y + i, z, SCContent.laserBlock);
					world.scheduleBlockUpdate(x, y + i, z, SCContent.laserBlock, 50);
					world.notifyBlocksOfNeighborChange(x, y + i, z, SCContent.laserBlock);

					if(world.getTileEntity(x, y + i, z) instanceof CustomizableSCTE && ((CustomizableSCTE) world.getTileEntity(x, y + i, z)).hasModule(EnumCustomModules.HARMING))
						((EntityLivingBase) entity).attackEntityFrom(CustomDamageSources.laser, 10F);
				}
			}

			for(int i = 0; i < SecurityCraft.config.laserBlockRange; i++){
				Block id = world.getBlock(x, y - i, z);
				if(id == SCContent.laserBlock){
					if(world.getTileEntity(x, y - i, z ) instanceof CustomizableSCTE && ((CustomizableSCTE) world.getTileEntity(x, y - i, z)).hasModule(EnumCustomModules.WHITELIST) && ModuleUtils.getPlayersFromModule(world, x, y - i, z, EnumCustomModules.WHITELIST).contains(((EntityLivingBase) entity).getCommandSenderName().toLowerCase()))
						return;
					world.setBlockMetadataWithNotify(x, y - i, z , 2, 3);
					world.notifyBlocksOfNeighborChange(x, y - i, z, SCContent.laserBlock);
					world.scheduleBlockUpdate(x, y - i, z, SCContent.laserBlock, 50);
					world.notifyBlocksOfNeighborChange(x, y - i, z, SCContent.laserBlock);

					if(world.getTileEntity(x, y - i, z) instanceof CustomizableSCTE && ((CustomizableSCTE) world.getTileEntity(x, y - i, z)).hasModule(EnumCustomModules.HARMING))
						((EntityLivingBase) entity).attackEntityFrom(CustomDamageSources.laser, 10F);
				}
			}
		}
	}


	/**
	 * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
	 */
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
	{
		if(!world.isRemote){
			for(int i = 0; i < SecurityCraft.config.laserBlockRange; i++){
				Block id = world.getBlock(x + i, y, z);
				if(id == SCContent.laserBlock)
					for(int j = 1; j < i; j++)
						world.breakBlock(x + j, y, z, false);
			}

			for(int i = 0; i < SecurityCraft.config.laserBlockRange; i++){
				Block id = world.getBlock(x - i, y, z);
				if(id == SCContent.laserBlock)
					for(int j = 1; j < i; j++)
						world.breakBlock(x - j, y, z, false);
			}

			for(int i = 0; i < SecurityCraft.config.laserBlockRange; i++){
				Block id = world.getBlock(x, y, z + i);
				if(id == SCContent.laserBlock)
					for(int j = 1; j < i; j++)
						world.breakBlock(x, y, z + j, false);
			}

			for(int i = 0; i < SecurityCraft.config.laserBlockRange; i++){
				Block id = world.getBlock(x , y, z - i);
				if(id == SCContent.laserBlock)
					for(int j = 1; j < i; j++)
						world.breakBlock(x, y, z - j, false);
			}

			for(int i = 0; i < SecurityCraft.config.laserBlockRange; i++){
				Block id = world.getBlock(x, y + i, z);
				if(id == SCContent.laserBlock)
					for(int j = 1; j < i; j++)
						world.breakBlock(x, y + j, z, false);
			}

			for(int i = 0; i < SecurityCraft.config.laserBlockRange; i++){
				Block id = world.getBlock(x, y - i, z);
				if(id == SCContent.laserBlock)
					for(int j = 1; j < i; j++)
						world.breakBlock(x, y - j, z, false);
			}
		}
	}

	/**
	 * Called when the block is placed in the world.
	 */
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
	{
		int entityRotation = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if(entityRotation == 0)
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		else if(entityRotation == 1)
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);

		else if(entityRotation == 2)
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		else if(entityRotation == 3)
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z)
	{
		if(access.getBlockMetadata(x, y, z) == 1)
			setBlockBounds(0.250F, 0.000F, 0.300F, 0.750F, 1.000F, 0.700F);
		else if(access.getBlockMetadata(x, y, z) == 2)
			setBlockBounds(0.250F, 0.300F, 0.000F, 0.750F, 0.700F, 1.000F);
		else if(access.getBlockMetadata(x, y, z) == 3)
			setBlockBounds(0.000F, 0.300F, 0.300F, 1.000F, 0.700F, 0.700F);
		else
			setBlockBounds(0.250F, 0.300F, 0.300F, 0.750F, 0.700F, 0.700F);
	}


	@Override
	@SideOnly(Side.CLIENT)

	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	public IIcon getIcon(int side, int meta)
	{
		if(meta == 1 && (side == 1 || side == 0))
			return transparentIcon;
		else
			return blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IIconRegister register)
	{
		blockIcon = register.registerIcon("securitycraft:aniLaser");
		transparentIcon = register.registerIcon("securitycraft:transparent");
	}

	@Override
	@SideOnly(Side.CLIENT)

	/**
	 * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
	 */
	public Item getItem(World world, int x, int y, int z)
	{
		return null;
	}

}
