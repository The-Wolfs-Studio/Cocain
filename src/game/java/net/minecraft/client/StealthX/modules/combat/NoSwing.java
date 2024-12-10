package net.minecraft.client.StealthX.modules.combat;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;

import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventUpdate;
import net.minecraft.client.StealthX.modules.Module;

public class NoSwing extends Module{

    public NoSwing() {
        super("NoSwing", KeyboardConstants.KEY_NONE, Category.COMBAT);
    }
    
    public void onEnable() {
        
    }
    public void onDisable() {

    }

    public void onEvent(Event e) {
                
    }
}