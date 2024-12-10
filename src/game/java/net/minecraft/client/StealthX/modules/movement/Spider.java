package net.minecraft.client.StealthX.modules.movement;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventUpdate;
import net.minecraft.client.StealthX.modules.Module;
import net.minecraft.util.MovementInputFromOptions;

public class Spider extends Module {

    public Spider() {
        super("Spider", KeyboardConstants.KEY_NONE, Category.MOVEMENT);
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onEvent(Event e) {
    	if (e instanceof EventUpdate) {
            handleSpiderMovement();
        }
    }

    private void handleSpiderMovement() {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;

        if (player.isCollidedHorizontally && !player.isInWater() && !player.capabilities.isFlying) {
            player.motionY = 0.5; 

            if (player.motionY > 0.0) {
                player.motionX = 0.0;  
                player.motionZ = 0.0;  
            }
        }
    }
}
