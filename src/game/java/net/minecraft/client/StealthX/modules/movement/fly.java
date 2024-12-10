package net.minecraft.client.StealthX.modules.movement;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;

import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventUpdate;
import net.minecraft.client.StealthX.modules.Module;

public class fly extends Module{

	public fly() {
	    super("Fly", KeyboardConstants.KEY_NONE, Category.MOVEMENT);
	}
	public void onDisable() {
		mc.thePlayer.capabilities.isFlying = false;
	}

    public void onEvent(Event e) {
    	if(e instanceof EventUpdate) {
    		if(e.isPre()) {
    			mc.thePlayer.capabilities.isFlying = true;
    		}
    	}
    }

}
