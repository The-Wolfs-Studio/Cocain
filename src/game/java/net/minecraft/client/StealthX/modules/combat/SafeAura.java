package net.minecraft.client.StealthX.modules.combat;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventUpdate;
import net.minecraft.client.StealthX.modules.Module;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class SafeAura extends Module {

    private int delay = 5;
    private long lastClickTime = 0;

    public SafeAura() {
        super("SafeAura", KeyboardConstants.KEY_NONE, Category.COMBAT);
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastClickTime >= delay) {
                EntityLivingBase target = getClosestTarget();
                if (target != null && !target.isDead) {
                    mc.playerController.attackEntity(mc.thePlayer, target);
                }
                lastClickTime = currentTime;
            }
        }
    }

    private EntityLivingBase getClosestTarget() {
        double closestDistance = 12.0;
        EntityLivingBase closestEntity = null;
        
        for (Object obj : mc.theWorld.loadedEntityList) {
            if (obj instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase) obj;
                if (entity != mc.thePlayer && !entity.isDead) {
                    double distance = mc.thePlayer.getDistanceToEntity(entity);
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestEntity = entity;
                    }
                }
            }
        }
        return closestEntity;
    }
}
