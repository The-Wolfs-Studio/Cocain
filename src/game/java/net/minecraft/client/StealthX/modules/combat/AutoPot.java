package net.minecraft.client.StealthX.modules.combat;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventUpdate;
import net.minecraft.client.StealthX.modules.Module;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPotion;
import net.minecraft.potion.Potion;
import net.minecraft.nbt.NBTTagCompound;

public class AutoPot extends Module {

    public AutoPot() {
        super("AutoPot", KeyboardConstants.KEY_NONE, Category.COMBAT);
    }

    public void onEnable() {
        splashAllPotions();
    }

    public void onDisable() {
    }

    public void onEvent(Event e) {
    }

    private void splashAllPotions() {
        for (int i = 0; i < 36; i++) {
            ItemStack stack = mc.thePlayer.inventory.getStackInSlot(i);
            if (stack != null && isSplashPotion(stack)) {
                mc.thePlayer.inventory.currentItem = i % 9;
                mc.rightClickMouse(); 
            }
        }
    }

    private boolean isSplashPotion(ItemStack stack) {
        if (stack.getItem() instanceof ItemPotion) {
            ItemPotion potion = (ItemPotion) stack.getItem();
            NBTTagCompound tag = stack.getTagCompound();
            if (tag != null && tag.hasKey("Potion")) {
                String potionType = tag.getString("Potion");
                return potionType.contains("splash"); 
            }
        }
        return false;
    }
}
