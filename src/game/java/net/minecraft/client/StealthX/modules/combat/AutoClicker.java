package net.minecraft.client.StealthX.modules.combat;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventUpdate;
import net.minecraft.client.StealthX.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class AutoClicker extends Module {

    private int delay = 5;
    private long lastClickTime = 0;

    public AutoClicker() {
        super("AutoClicker", KeyboardConstants.KEY_NONE, Category.COMBAT);
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void onEvent(Event e) {
        if (e instanceof EventUpdate) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastClickTime >= delay) {
                simulateClick();
                lastClickTime = currentTime;
            }
        }
    }

    private void simulateClick() {
        Minecraft mc = Minecraft.getMinecraft();
        EntityLivingBase target = getPointedEntity();

        if (target != null && !target.isDead) {
            mc.playerController.attackEntity(mc.thePlayer, target);
        }
    }

    private EntityLivingBase getPointedEntity() {
        Minecraft mc = Minecraft.getMinecraft();
        EntityLivingBase pointedEntity = null;

        Vec3 eyePos = mc.thePlayer.getPositionEyes(1.0F);
        Vec3 lookVec = mc.thePlayer.getLook(1.0F);
        Vec3 targetPos = eyePos.addVector(lookVec.xCoord * 4, lookVec.yCoord * 4, lookVec.zCoord * 4);

        AxisAlignedBB boundingBox = mc.thePlayer.getEntityBoundingBox().addCoord(lookVec.xCoord * 4, lookVec.yCoord * 4, lookVec.zCoord * 4);
        for (Object obj : mc.theWorld.loadedEntityList) {
            if (obj instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase) obj;
                if (entity != mc.thePlayer && !entity.isDead && entity.getEntityBoundingBox().intersectsWith(boundingBox)) {
                    pointedEntity = entity;
                    break;
                }
            }
        }
        return pointedEntity;
    }
}
