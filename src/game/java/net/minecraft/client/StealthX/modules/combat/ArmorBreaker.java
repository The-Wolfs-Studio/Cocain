package net.minecraft.client.StealthX.modules.combat;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventUpdate;
import net.minecraft.client.StealthX.modules.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ArmorBreaker extends Module {

    public ArmorBreaker() {
        super("ArmorBreaker", KeyboardConstants.KEY_NONE, Category.COMBAT);
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            if (mc.thePlayer != null && mc.theWorld != null) {
                EntityLivingBase target = getArmoredTarget();
                if (target != null) {
                    mc.playerController.attackEntity(mc.thePlayer, target);
                }
            }
        }
    }

    private EntityLivingBase getArmoredTarget() {
        EntityLivingBase bestTarget = null;
        double closestDistance = 6.0;

        for (Entity entity : mc.theWorld.loadedEntityList) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingEntity = (EntityLivingBase) entity;

                if (livingEntity != mc.thePlayer && !livingEntity.isDead && hasArmor(livingEntity)) {
                    double distance = mc.thePlayer.getDistanceToEntity(livingEntity);
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        bestTarget = livingEntity;
                    }
                }
            }
        }
        return bestTarget;
    }

    private boolean hasArmor(EntityLivingBase entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            for (ItemStack armorPiece : player.inventory.armorInventory) {
                if (armorPiece != null) {
                    return true;
                }
            }
        }
        return false;
    }
}
