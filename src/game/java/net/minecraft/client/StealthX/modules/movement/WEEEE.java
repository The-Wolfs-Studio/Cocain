package net.minecraft.client.StealthX.modules.movement;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventUpdate;
import net.minecraft.client.StealthX.modules.Module;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class WEEEE extends Module {

    private long lastDamageTime = 0;
    private static final long DAMAGE_INTERVAL = 10000;

    public WEEEE() {
        super("WEEEE", KeyboardConstants.KEY_NONE, Category.MOVEMENT);
    }

    public void onEnable() {
        lastDamageTime = System.currentTimeMillis();
    }

    public void onDisable() {
        if (mc.thePlayer != null) {
            mc.thePlayer.extinguish();
        }
    }

    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            if (mc.thePlayer != null && mc.thePlayer.hurtTime > 0) {
                mc.thePlayer.motionX = -3;
                mc.thePlayer.motionY = 1;
                mc.thePlayer.motionZ = -3;
            }

            mc.thePlayer.fallDistance = 0;

            if (mc.thePlayer.fallDistance > 0) {
                mc.thePlayer.motionY = 0;
            }

            if (mc.thePlayer != null) {
                // Apply fire damage every tick (though the player is invincible, this won't affect them)
                mc.thePlayer.setFire(1);

                // Fire damage timer logic (still works but won't hurt the player)
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastDamageTime >= DAMAGE_INTERVAL) {
                    lastDamageTime = currentTime; // Reset the timer
                    if (!mc.thePlayer.isImmuneToFire()) {
                        // Invincibility: Prevent any damage
                        mc.thePlayer.setHealth(mc.thePlayer.getHealth()); // Do nothing to health
                    }
                }

                // Health Boost Effect
                // Apply Health Boost (level 1) every tick
                if (mc.thePlayer != null) {
                    // Correct usage of Potion.HEALTH_BOOST
                    PotionEffect healthBoost = new PotionEffect(Potion.healthBoost.id, 20, 1); // 20 ticks = 1 second
                    if (!mc.thePlayer.isPotionActive(Potion.healthBoost)) {
                        mc.thePlayer.addPotionEffect(healthBoost); // Apply the health boost effect if not already active
                    }
                }
            }
        }
    }
}
