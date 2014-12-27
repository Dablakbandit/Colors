package me.dablakbandit.colors.render;

import me.dablakbandit.playermap.api.CustomMapPallete.Color;
import me.dablakbandit.playermap.api.CustomMapPallete.ColorByte;
import me.dablakbandit.playermap.api.Menu;
import me.dablakbandit.playermap.api.RenderMap;
import me.dablakbandit.playermap.player.Players;

public final class SpecificColorRendererManager extends Menu<ColorByte>{

	private Color color;

	public SpecificColorRendererManager(String name, Color c){
		super(name);
		color = c;
		int i = 1;
		try{
			for(ColorByte cb : c.getListColorByte()){
				add(i, cb);
				i++;
			}
		}catch(Exception e){

		}
	}

	public final Color getColor(){
		return color;
	}

	public final RenderMap getNewRenderMap(Players players, RenderMap old) {
		return new SpecificColorRenderer(players, this, old);
	}

}
