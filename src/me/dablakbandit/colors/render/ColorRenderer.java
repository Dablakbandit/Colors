package me.dablakbandit.colors.render;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapView;
import org.bukkit.map.MinecraftFont;

import me.dablakbandit.playermap.api.CustomMapPallete.Color;
import me.dablakbandit.playermap.api.CustomMapPallete.ColorByte;
import me.dablakbandit.playermap.api.RenderMap;
import me.dablakbandit.playermap.player.Players;

public class ColorRenderer extends RenderMap{

	private int current = ColorRendererManager.getInstance().getFirst();
	private int dif = 0;

	public ColorRenderer(Players player, RenderMap old) {
		super(player, old);
	}

	@Override
	protected void customRender(MapView view, MapCanvas canvas, Player p) {
		clear(canvas);
		List<ColorByte> used = new ArrayList<ColorByte>();
		ColorByte b = Color.getRandomColor().getColorByte();
		used.add(b);
		setAll(canvas, b.getByte());
		Map<Integer, Color> r = ColorRendererManager.getInstance().getReferences();
		if(r.size()==0){
			return;
		}
		if(current < 0){
			current = ColorRendererManager.getInstance().getFirst();
		}
		int done = 0;
		for(int i : r.keySet()){
			b = Color.getRandomColor(used).getColorByte();
			used.add(b);
			if(i<current){
				drawText(canvas, dif, (-(MinecraftFont.Font.getHeight() + 3) * ColorRendererManager.getInstance().getDifference(i, current)), MinecraftFont.Font, r.get(i).getName(), b, TextAlign.MIDDLE_CENTER);
			}else if(i==current){
				drawText(canvas, dif, 0, MinecraftFont.Font, ">" + r.get(i).getName(), b, TextAlign.MIDDLE_CENTER);
				done = 1;
			}else if(i>current){
				drawText(canvas, dif, (done * (MinecraftFont.Font.getHeight() + 3)), MinecraftFont.Font, r.get(i).getName(), b, TextAlign.MIDDLE_CENTER);
				done++;
			}
		}
	}

	@Override
	public void onLookUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLookDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLookRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLookLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMoveForward() {
		current = ColorRendererManager.getInstance().getBefore(current);
		clearUUIDs();
	}

	@Override
	public void onMoveBackward() {
		current = ColorRendererManager.getInstance().getNext(current);
		clearUUIDs();
	}

	@Override
	public void onMoveLeft() {
		dif-=5;
		clearUUIDs();
	}

	@Override
	public void onMoveRight() {
		dif+=5;
		clearUUIDs();
	}

	@Override
	public void onLeftClick() {
		Players players = getPlayers();
		players.setRenderer(ColorRendererManager.getInstance().getSpecificColorRendererManager(
				ColorRendererManager.getInstance().getReference(current))
				.getNewRenderMap(players, this));
	}

	@Override
	public void onRightClick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSneak() {
		back();
	}

	@Override
	public void onJump() {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockMove() {
		// TODO Auto-generated method stub
		
	}

}
