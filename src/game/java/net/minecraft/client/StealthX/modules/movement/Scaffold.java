package net.minecraft.client.StealthX.modules.movement;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventUpdate;
import net.minecraft.client.StealthX.modules.Module;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class Scaffold extends Module {

    private final Minecraft mc = Minecraft.getMinecraft();
    private boolean isActive = false;

    public Scaffold() {
        super("Scaffold", KeyboardConstants.KEY_NONE, Category.MOVEMENT);
    }

    public void onEnable() {
        isActive = true;
    }

    public void onDisable() {
        isActive = false;
    }

    public void onEvent(Event e) {
        if (e instanceof EventUpdate && isActive) {
            simulateGroundPlacement();
        }
    }

    private void simulateGroundPlacement() {
        EntityPlayerSP player = mc.thePlayer;

        if (player != null && !player.capabilities.isFlying) {
            float originalYaw = player.rotationYaw;
            float originalPitch = player.rotationPitch;

            if (mc.gameSettings.thirdPersonView == 0) {
                player.rotationPitch = 90.0F;
            }

            BlockPos blockBelow = new BlockPos(player.posX, player.posY - 1.0, player.posZ);
            if (mc.theWorld.isAirBlock(blockBelow)) {
                for (int i = 0; i < 9; i++) {
                    if (mc.thePlayer.inventory.getStackInSlot(i) != null &&
                        mc.thePlayer.inventory.getStackInSlot(i).getItem() instanceof net.minecraft.item.ItemBlock) {
                        mc.thePlayer.inventory.currentItem = i;

                        mc.playerController.onPlayerRightClick(
                            player,
                            mc.theWorld,
                            player.getHeldItem(),
                            blockBelow,
                            EnumFacing.UP,
                            new Vec3(blockBelow.getX() + 0.5, blockBelow.getY(), blockBelow.getZ() + 0.5)
                        );
                        break;
                    }
                }
            }

            player.rotationYaw = originalYaw;
            player.rotationPitch = originalPitch;

            double speed = 0.2;
            player.motionX = 0;
            player.motionZ = 0;

            if (mc.gameSettings.keyBindForward.isKeyDown()) {
                player.motionX = -Math.sin(Math.toRadians(player.rotationYaw)) * speed;
                player.motionZ = Math.cos(Math.toRadians(player.rotationYaw)) * speed;
            }
            if (mc.gameSettings.keyBindBack.isKeyDown()) {
                player.motionX = Math.sin(Math.toRadians(player.rotationYaw)) * speed;
                player.motionZ = -Math.cos(Math.toRadians(player.rotationYaw)) * speed;
            }
            if (mc.gameSettings.keyBindLeft.isKeyDown()) {
                player.motionX = -Math.cos(Math.toRadians(player.rotationYaw)) * speed;
                player.motionZ = -Math.sin(Math.toRadians(player.rotationYaw)) * speed;
            }
            if (mc.gameSettings.keyBindRight.isKeyDown()) {
                player.motionX = Math.cos(Math.toRadians(player.rotationYaw)) * speed;
                player.motionZ = Math.sin(Math.toRadians(player.rotationYaw)) * speed;
            }

            if (mc.gameSettings.keyBindJump.isKeyDown()) {
                player.motionY = 0.42;
            } else if (player.onGround) {
                player.motionY = 0;
            }
        }
    }
}
