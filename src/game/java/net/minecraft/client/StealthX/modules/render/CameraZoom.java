package net.minecraft.client.StealthX.modules.render;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;

import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventUpdate;
import net.minecraft.client.StealthX.modules.Module;

public class CameraZoom extends Module{

    public CameraZoom() {
        super("CameraZoom", KeyboardConstants.KEY_NONE, Category.RENDER);
    }
    
    public void onEnable() {
        
    }
    public void onDisable() {

    }

    public void onEvent(Event e) {
                
    }
}