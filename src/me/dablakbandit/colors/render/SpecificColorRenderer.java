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

public class SpecificColorRenderer extends RenderMap{

	private int current;
	private int dif = 0;
	private SpecificColorRendererManager manager;

	public SpecificColorRenderer(Players player, SpecificColorRendererManager manager, RenderMap old) {
		super(player, old);
		this.manager = manager;
		current = manager.getFirst();
	}

	@Override
	protected void customRender(MapView view, MapCanvas canvas, Player p) {
		clear(canvas);
		Map<Integer, ColorByte> r = manager.getReferences();
		if(r.size()==0){
			return;
		}
		if(current < 0){
			current = ColorRendererManager.getInstance().getFirst();
		}
		List<ColorByte> used = new ArrayList<ColorByte>();
		ColorByte b = r.get(current);
		used.add(b);
		setAll(canvas, b.getByte());
		int done = 0;
		for(int i : r.keySet()){
			ColorByte cb = r.get(i);
			b = Color.getRandomColor(used).getColorByte();
			used.add(b);
			if(i<current){
				drawText(canvas, dif, (-10 * manager.getDifference(i, current)), MinecraftFont.Font, cb.getName(), b, TextAlign.MIDDLE_CENTER);
			}else if(i==current){
				drawText(canvas, dif, 0, MinecraftFont.Font, ">" + cb.getName(), b, TextAlign.MIDDLE_CENTER);
				done = 1;
			}else if(i>current){
				drawText(canvas, dif, (done * 10), MinecraftFont.Font, cb.getName(), b, TextAlign.MIDDLE_CENTER);
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
		current = manager.getBefore(current);
		clearUUIDs();
	}

	@Override
	public void onMoveBackward() {
		current = manager.getNext(current);
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
		getPlayers().setTextColor(manager.getReference(current));
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

	@Override
	public void onDrop() {
		// TODO Auto-generated method stub
		
	}

}
