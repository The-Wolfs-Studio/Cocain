package net.minecraft.client.StealthX.modules.render;

import java.util.List;

import net.lax1dude.eaglercraft.v1_8.Keyboard;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventKey;
import net.minecraft.client.StealthX.events.listeners.EventRenderGUI;
import net.minecraft.client.StealthX.modules.Module;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.StealthX.Client;
import net.lax1dude.eaglercraft.v1_8.Keyboard;

public class TabGUI extends Module {

    public int currentTab;
    public boolean expanded;

    public TabGUI() {
        super("TabGUI", KeyboardConstants.KEY_NONE, Category.RENDER);
        toggled = true;
    }

    public void onEvent(Event e) {
        if (e instanceof EventRenderGUI) {
            FontRenderer fr = mc.fontRendererObj;

            // Reducing the space between each entry even more (even smaller)
            int textHeight = 8; // Even smaller text height for tighter spacing

            Gui.drawRect(5, 30.5, 70 + 70, 30 + Module.Category.values().length * textHeight + 1.5, 0x9000000);
            Gui.drawRect(7, 33 + currentTab * textHeight, 7 + 61, 33 + currentTab * textHeight + 8, 0xff0090ff);

            int Count = 0;
            for (Category c : Module.Category.values()) {
                // Scaling the text down even more by using a very tight Y-spacing
                renderScaledText(fr, c.name, 11, 35 + Count * textHeight, -1, 0.5F);
                Count++;
            }

            if (expanded) {
                Category category = Module.Category.values()[currentTab];
                List<Module> modules = Client.getModulesByCategory(category);
                if (modules.size() == 0)
                    return;

                Count = 0;
                for (Module m : modules) {
                    // Scaling the module text even more
                    renderScaledText(fr, m.name, 73, 35 + Count * textHeight, -1, 0.5F);
                    Count++;
                }

                Gui.drawRect(72, 33 + category.moduleIndex * textHeight, 7 + 61, 33 + category.moduleIndex * textHeight + 8, 0xff0090ff);
            }
        }

        if (e instanceof EventKey) {
            int code = ((EventKey) e).code;
            Category category = Module.Category.values()[currentTab];
            List<Module> modules = Client.getModulesByCategory(category);

            if (code == KeyboardConstants.KEY_UP) {
                if (expanded) {
                    if (category.moduleIndex <= 0) {
                        category.moduleIndex = modules.size() - 1;
                    } else
                        category.moduleIndex--;
                } else {
                    if (currentTab <= 0) {
                        currentTab = Module.Category.values().length - 1;
                    } else
                        currentTab--;
                }
            }

            if (code == KeyboardConstants.KEY_DOWN) {
                if (expanded) {
                    if (category.moduleIndex >= modules.size() - 1) {
                    } else
                        category.moduleIndex++;
                } else {
                    if (currentTab >= Module.Category.values().length - 1) {
                    } else
                        currentTab++;
                }
            }

            if (code == KeyboardConstants.KEY_RIGHT) {
                if (expanded && modules.size() != 0) {
                    Module module = modules.get(category.moduleIndex);
                    if (!module.name.equals("TabGUI"))
                        module.toggle();
                } else {
                    expanded = true;
                }
            }

            if (code == KeyboardConstants.KEY_LEFT) {
                expanded = false;
            }
        }
    }

    // Custom method to scale down the text
    private void renderScaledText(FontRenderer fontRenderer, String text, int x, int y, int color, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, scale); // Apply scaling
        fontRenderer.drawStringWithShadow(text, x / scale, y / scale, color); // Adjust coordinates to fit scaled text
        GlStateManager.popMatrix();
    }
}
