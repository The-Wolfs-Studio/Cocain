package net.minecraft.client.StealthX.modules.combat;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventUpdate;
import net.minecraft.client.StealthX.modules.Module;
//Dosent really work 
public class Reach extends Module {

    private final Minecraft mc = Minecraft.getMinecraft();
    private static final float REACH_DISTANCE = 12.0F; 

    public Reach() {
        super("Reach", KeyboardConstants.KEY_NONE, Category.COMBAT);
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            setReach(REACH_DISTANCE);
        }
    }

    private void setReach(float distance) {
        if (mc.thePlayer != null && mc.theWorld != null) {
            mc.playerController.getBlockReachDistance();
        }
    }
}
