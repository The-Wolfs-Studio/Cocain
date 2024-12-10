package net.minecraft.client.StealthX.modules.combat;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventUpdate;
import net.minecraft.client.StealthX.modules.Module;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

public class AutoGapple extends Module {

    private static final int HEALTH_THRESHOLD = 10;
    private long lastGappleTime = 0;
    private static final long GAPPLE_COOLDOWN = 1000 * 5;

    public AutoGapple() {
        super("AutoGapple", KeyboardConstants.KEY_NONE, Category.COMBAT);
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            if (mc.thePlayer.getHealth() <= HEALTH_THRESHOLD && canUseGapple()) {
                useGapple();
            }
        }
    }

    private boolean canUseGapple() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastGappleTime >= GAPPLE_COOLDOWN) {
            lastGappleTime = currentTime;
            return true;
        }
        return false;
    }

    private void useGapple() {
        if (mc.thePlayer.isUsingItem()) {
            return;
        }

        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.thePlayer.inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() == Items.golden_apple) {
                mc.thePlayer.inventory.currentItem = i;
                mc.rightClickMouse();
                return;
            }
        }

        for (int i = 9; i < 36; i++) {
            ItemStack stack = mc.thePlayer.inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() == Items.golden_apple) {
                mc.thePlayer.inventory.currentItem = i - 9;
                mc.rightClickMouse();
                return;
            }
        }
    }
}
