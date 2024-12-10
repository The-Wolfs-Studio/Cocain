package net.minecraft.client.StealthX.ui;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.StealthX.Client;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.StealthX.modules.Module;
import net.minecraft.client.StealthX.events.listeners.EventRenderGUI;

public class HUD {

    public Minecraft mc = Minecraft.getMinecraft();
    private float glowTimer = 0.0f;

    public void draw() {
        ScaledResolution sr = new ScaledResolution(mc);
        FontRenderer fr = mc.fontRendererObj;

        Client.modules
                .sort((m1, m2) -> Integer.compare(mc.fontRendererObj.getStringWidth(((Module) m1).name), mc.fontRendererObj.getStringWidth(((Module) m2).name)));

        GlStateManager.pushMatrix();
        GlStateManager.translate(8, 4, 0);
        GlStateManager.scale(1.0, 1.0, 1.0);
        fr.drawString("Cocain " + "1" + Client.version, 5, 10, -1);
        GlStateManager.popMatrix();

        int count = 0;

        for (Module m : Client.modules) {

            if (!m.toggled || m.name.equals("TabGUI"))
                continue;

            double offset = count * (fr.FONT_HEIGHT + 6) * 0.75;

            Gui.drawRect(sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(m.name) - 8, +offset,
                    sr.getScaledWidth(), 6 + mc.fontRendererObj.FONT_HEIGHT + offset, 0x90000000);
            mc.fontRendererObj.drawString(m.name, sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(m.name) - 4,
                    4 + offset, -1);

            count++;
        }

        Client.onEvent(new EventRenderGUI());
        
        glowTimer += 0.02f;
        if (glowTimer > 1.0f) glowTimer = 0.0f;

        float alpha = (float) Math.abs(Math.sin(glowTimer * Math.PI));

        GlStateManager.pushMatrix();
        GlStateManager.translate(sr.getScaledWidth() - 325, sr.getScaledHeight() - 40, 0); // Moved 10 pixels down
        GlStateManager.scale(0.75, 0.75, 0.75);

        int glowColor = (int) (alpha * 255);
        fr.drawString("§5§D Developed by Namenotfound128", 0, 0, (glowColor << 16) | (glowColor << 8) | glowColor);

        GlStateManager.popMatrix();
    }
}
