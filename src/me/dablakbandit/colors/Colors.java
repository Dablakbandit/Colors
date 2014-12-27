package me.dablakbandit.colors;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import me.dablakbandit.colors.render.ColorRendererManager;
import me.dablakbandit.playermap.PlayerMap;
import me.dablakbandit.playermap.api.CustomMapPallete.Color;
import me.dablakbandit.playermap.api.CustomMapPallete.ColorByte;
import me.dablakbandit.playermap.render.MainMenuManager;

import org.bukkit.plugin.java.JavaPlugin;

public class Colors extends JavaPlugin{

	public void onLoad(){
		try {
			MainMenuManager.getInstance().add(2, ColorRendererManager.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onEnable(){
		try {
			for(Color c : Color.getColors()){
				File f = new File(PlayerMap.getInstance().getDataFolder().getAbsoluteFile()+ "/Colors/" + c.getName());
				f.mkdirs();
				for(ColorByte cb : c.getListColorByte()){
					f = new File(PlayerMap.getInstance().getDataFolder().getAbsoluteFile()+ "/Colors/" + c.getName() + "/" + cb.getName() + ".png");
					if(!f.exists()){
						BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
						java.awt.Color c1 = Color.getJavaColor(cb);
						for(int x = 0; x < 10; x++) {
							for(int y = 0; y < 10; y++) {
								image.setRGB(x, y, c1.getRGB());
							}
						}
						ImageIO.write(image, "png", f);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
