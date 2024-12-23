package net.minecraft.client.StealthX.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.StealthX.events.Event;

public class Module {

	public String name;
	public boolean toggled;
	public int keyCode;
	public Category category;
	public Minecraft mc = Minecraft.getMinecraft();

	public Module(String name, int key, Category c) {
		this.name = name;
		this.keyCode = key;
		this.category = c;
	}

	public boolean isEnabled() {
		return toggled;

	}

	public int getKey() {
		return keyCode;
	}

	public void onEvent(Event e) {

	}

	public void toggle() {
		toggled = !toggled;
		if (toggled) {
			onEnable();
		} else {
			onDisable();
		}
	}

	public void onEnable() {

	}

	public void onDisable() {
	}

	public enum Category {
		COMBAT("Combat"), RENDER("Render"), MOVEMENT("Movement"), PLAYER("Player");

		public String name;
		public int moduleIndex;

		Category(String name) {
			this.name = name;
		}
	}

	public void onUpdate() {
		
	}

}
