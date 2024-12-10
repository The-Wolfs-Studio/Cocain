package net.minecraft.client.StealthX.modules.utils;

import net.minecraft.client.StealthX.modules.Module;

public class Setting {

    private String name;
    private Module module;
    private float value;
    private float min;
    private float max;
    private float increment;
    private boolean slider;

    public Setting(String name, Module module, float value, float min, float max, float increment, boolean slider) {
        this.name = name;
        this.module = module;
        this.value = value;
        this.min = min;
        this.max = max;
        this.increment = increment;
        this.slider = slider;
    }

    public String getName() {
        return name;
    }

    public Module getModule() {
        return module;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        if (value >= min && value <= max) {
            this.value = value;
        }
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public float getIncrement() {
        return increment;
    }

    public boolean isSlider() {
        return slider;
    }
}
