package me.dablakbandit.colors.render;

import java.util.HashMap;
import java.util.Map;

import me.dablakbandit.playermap.api.CustomMapPallete.Blue;
import me.dablakbandit.playermap.api.CustomMapPallete.Color;
import me.dablakbandit.playermap.api.Menu;
import me.dablakbandit.playermap.api.RenderMap;
import me.dablakbandit.playermap.player.Players;

public final class ColorRendererManager extends Menu<Color>{

	private static final ColorRendererManager manager = new ColorRendererManager(Color.getTextValue(Blue.AZURE) + "Colors");
	private Map<Color, SpecificColorRendererManager> colorsmanager = new HashMap<Color, SpecificColorRendererManager>();

	private ColorRendererManager(String name){
		super(name);
		int i = 1;
		try{
			for(Color c : Color.getColors()){
				add(i, c);
				colorsmanager.put(c, new SpecificColorRendererManager(c.getName(), c));
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static final ColorRendererManager getInstance(){
		return manager;
	}

	public final SpecificColorRendererManager getSpecificColorRendererManager(Color c){
		return colorsmanager.get(c);
	}
	
	@Override
	public final RenderMap getNewRenderMap(Players players, RenderMap old) {
		return new ColorRenderer(players, old);
	}

}
