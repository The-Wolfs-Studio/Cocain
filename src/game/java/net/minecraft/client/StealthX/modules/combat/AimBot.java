package net.minecraft.client.StealthX.modules.combat;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventUpdate;
import net.minecraft.client.StealthX.modules.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class AimBot extends Module {

    public AimBot() {
        super("AimBot", KeyboardConstants.KEY_NONE, Category.COMBAT);
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            if (mc.thePlayer != null && mc.theWorld != null) {
                EntityLivingBase target = getClosestTarget();
                if (target != null) {
                    faceEntity(target);
                }
            }
        }
    }

    private EntityLivingBase getClosestTarget() {
        EntityLivingBase closestEntity = null;
        double closestDistance = 6.0; // max range 

        for (Entity entity : mc.theWorld.loadedEntityList) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingEntity = (EntityLivingBase) entity;

                if (livingEntity != mc.thePlayer && !livingEntity.isDead) {
                    double distance = mc.thePlayer.getDistanceToEntity(livingEntity);
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestEntity = livingEntity;
                    }
                }
            }
        }
        return closestEntity;
    }

    private void faceEntity(Entity entity) {
        double deltaX = entity.posX - mc.thePlayer.posX;
        double deltaY = (entity.posY + entity.getEyeHeight()) - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
        double deltaZ = entity.posZ - mc.thePlayer.posZ;

        double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        float yaw = (float) Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90F;
        float pitch = (float) -Math.toDegrees(Math.atan2(deltaY, distance));

        mc.thePlayer.rotationYaw = yaw;
        mc.thePlayer.rotationPitch = pitch;
    }
}
