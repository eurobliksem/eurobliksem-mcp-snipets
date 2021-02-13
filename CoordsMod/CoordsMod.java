package euroclient.mods.impl;

import euroclient.gui.huid.ScreenPosition;
import euroclient.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;



public class CoordsMod extends ModDraggable{
	/*coded bye eurobliksem*/
	private String biomeName; 
	private int subwidth;
	private int truewidth;
	@Override
	public int getWidth() {
		int xwidth = font.getStringWidth("X: " + Math.round((Minecraft.getMinecraft().thePlayer.posX)*1000.0) / 1000);
		int Ywidth = font.getStringWidth("Y: " + Math.round((Minecraft.getMinecraft().thePlayer.posY)*1000.0) / 1000);
		int Zwidth = font.getStringWidth("Z: " + Math.round((Minecraft.getMinecraft().thePlayer.posZ)*1000.0) / 1000);
		int BiomeWidth = font.getStringWidth("Biome: " + biomeName);
		
		if( (xwidth > Ywidth) && (xwidth > Zwidth)) {
			subwidth = xwidth;
		}else if((Ywidth > xwidth) && (Ywidth > Zwidth) ) {
			subwidth = Ywidth;
		}else {
			subwidth = Zwidth;
		}
		
		if(BiomeWidth > subwidth) {
			truewidth = BiomeWidth;
		}else {
			truewidth = subwidth;
			
		}
		
		if (truewidth < 90) {
			truewidth = 90;
		}
		
		return truewidth + 6;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 45;
	}

	@Override
	public void render(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() , pos.getAbsoluteX() + getWidth() + 1, pos.getAbsoluteY() + getHeight() , 0x90000000);
		font.drawStringWithShadow("X: " + Math.round((Minecraft.getMinecraft().thePlayer.posX)*1000.0) / 1000, pos.getAbsoluteX() +3, pos.getAbsoluteY()+4, -1);
		font.drawStringWithShadow("Y: " + Math.round((Minecraft.getMinecraft().thePlayer.posY)*1000.0) / 1000, pos.getAbsoluteX() +3, pos.getAbsoluteY() +10 +4, -1);
		font.drawStringWithShadow("Z: " + Math.round((Minecraft.getMinecraft().thePlayer.posZ)*1000.0) / 1000, pos.getAbsoluteX() +3, pos.getAbsoluteY() +20 +4, -1);
		BlockPos blockpos = new BlockPos(this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().getEntityBoundingBox().minY, this.mc.getRenderViewEntity().posZ);

		if (this.mc.theWorld != null && this.mc.theWorld.isBlockLoaded(blockpos))
		            {
		                Chunk chunk = this.mc.theWorld.getChunkFromBlockCoords(blockpos);
		 biomeName = chunk.getBiome(blockpos, this.mc.theWorld.getWorldChunkManager()).biomeName;
		 font.drawStringWithShadow("Biome: " + biomeName , pos.getAbsoluteX() +3, pos.getAbsoluteY() +30 +4, -1);
		}
	
		
	}

}
