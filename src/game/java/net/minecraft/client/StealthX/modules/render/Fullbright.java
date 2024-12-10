package net.minecraft.client.StealthX.modules.render;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;

import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventUpdate;
import net.minecraft.client.StealthX.modules.Module;

public class Fullbright extends Module{

	public Fullbright() {
	    super("Fullbright", KeyboardConstants.KEY_NONE, Category.RENDER);
	}
	
	public void onEnable() {
		mc.gameSettings.gammaSetting = 100;
		
		
	}
	public void onDisable() {
		mc.gameSettings.gammaSetting = 1;
    }

}
