package net.minecraft.client.StealthX.modules.movement;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventUpdate;
import net.minecraft.client.StealthX.modules.Module;

public class AirWalk extends Module {

    public AirWalk() {
        super("AirWalk", KeyboardConstants.KEY_NONE, Category.MOVEMENT);
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            handleAirWalk();
        }
    }

    private void handleAirWalk() {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;

        if (player != null && !player.onGround && !player.isInWater() && !player.capabilities.isFlying) {
            if (player.isSneaking()) {
                player.motionY = -0.1;
            } else if (player.motionY > 0) {
                player.motionY = 0.1;
            } else {
                player.motionY = 0.0;
            }
        }
    }
}
