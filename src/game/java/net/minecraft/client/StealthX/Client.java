package net.minecraft.client.StealthX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.client.StealthX.events.Event;
import net.minecraft.client.StealthX.events.listeners.EventKey;
import net.minecraft.client.StealthX.modules.Module;
import net.minecraft.client.StealthX.modules.Module.Category;
import net.minecraft.client.StealthX.modules.movement.fly;
import net.minecraft.client.StealthX.modules.render.Fullbright;
import net.minecraft.client.StealthX.modules.render.TabGUI;
import net.minecraft.client.StealthX.modules.combat.AimBot;
import net.minecraft.client.StealthX.modules.combat.AutoClicker;
import net.minecraft.client.StealthX.modules.combat.Reach;
import net.minecraft.client.StealthX.modules.combat.Criticals;
import net.minecraft.client.StealthX.modules.combat.AntiKnockback;
import net.minecraft.client.StealthX.modules.combat.AutoPot;
import net.minecraft.client.StealthX.modules.combat.BowAimBot;
import net.minecraft.client.StealthX.modules.combat.NoSwing;
import net.minecraft.client.StealthX.modules.combat.FastBow;
import net.minecraft.client.StealthX.modules.combat.TriggerBot;
import net.minecraft.client.StealthX.modules.combat.AutoGapple;
import net.minecraft.client.StealthX.modules.combat.WTap;
import net.minecraft.client.StealthX.modules.combat.TargetHUD;
import net.minecraft.client.StealthX.modules.combat.AutoSoup;
import net.minecraft.client.StealthX.modules.combat.SafeAura;
import net.minecraft.client.StealthX.modules.combat.BowSpam;
import net.minecraft.client.StealthX.modules.combat.HitParticles;
import net.minecraft.client.StealthX.modules.combat.PvPStats;
import net.minecraft.client.StealthX.modules.combat.ArmorBreaker;
import net.minecraft.client.StealthX.modules.combat.ComboBot;
import net.minecraft.client.StealthX.modules.combat.BlockHit;
import net.minecraft.client.StealthX.modules.combat.QuickSwap;
import net.minecraft.client.StealthX.modules.combat.SmartAttack;

import net.minecraft.client.StealthX.modules.movement.Speed;
import net.minecraft.client.StealthX.modules.movement.Spider;
import net.minecraft.client.StealthX.modules.movement.Step;
import net.minecraft.client.StealthX.modules.movement.NoFall;
import net.minecraft.client.StealthX.modules.movement.HighJump;
import net.minecraft.client.StealthX.modules.movement.Jesus;
import net.minecraft.client.StealthX.modules.movement.Glide;
import net.minecraft.client.StealthX.modules.movement.Dolphin;
import net.minecraft.client.StealthX.modules.movement.Teleport;
import net.minecraft.client.StealthX.modules.movement.Phase;
import net.minecraft.client.StealthX.modules.movement.ClickTP;
import net.minecraft.client.StealthX.modules.movement.Timer;
import net.minecraft.client.StealthX.modules.movement.Velocity;
import net.minecraft.client.StealthX.modules.movement.FastLadder;
import net.minecraft.client.StealthX.modules.movement.BlockPhase;
import net.minecraft.client.StealthX.modules.movement.NoClip;
import net.minecraft.client.StealthX.modules.movement.SafeWalk;
import net.minecraft.client.StealthX.modules.movement.ParkourAssist;
import net.minecraft.client.StealthX.modules.movement.WallRun;
import net.minecraft.client.StealthX.modules.movement.ReverseGravity;
import net.minecraft.client.StealthX.modules.movement.AirWalk;
import net.minecraft.client.StealthX.modules.movement.ElytraBoost;
import net.minecraft.client.StealthX.modules.movement.LadderBoost;
import net.minecraft.client.StealthX.modules.movement.BunnyHop;
import net.minecraft.client.StealthX.modules.movement.Scaffold;

import net.minecraft.client.StealthX.modules.player.AutoArmor;
import net.minecraft.client.StealthX.modules.player.AutoTool;
import net.minecraft.client.StealthX.modules.player.InventoryMove;
import net.minecraft.client.StealthX.modules.player.AutoEat;
import net.minecraft.client.StealthX.modules.player.AntiAFK;
import net.minecraft.client.StealthX.modules.player.AutoFish;
import net.minecraft.client.StealthX.modules.player.AutoFarm;
import net.minecraft.client.StealthX.modules.player.AutoBridge;
import net.minecraft.client.StealthX.modules.player.FastPlace;
import net.minecraft.client.StealthX.modules.player.AutoMine;
import net.minecraft.client.StealthX.modules.player.ChestStealer;
import net.minecraft.client.StealthX.modules.player.FakePlayer;
import net.minecraft.client.StealthX.modules.player.SkinStealer;
import net.minecraft.client.StealthX.modules.player.MultiAccount;
import net.minecraft.client.StealthX.modules.player.ExploitFinder;
import net.minecraft.client.StealthX.modules.player.ServerCrasher;
import net.minecraft.client.StealthX.modules.player.ChatSpammer;
import net.minecraft.client.StealthX.modules.player.TrollAura;
import net.minecraft.client.StealthX.modules.player.AutoRespawn;
import net.minecraft.client.StealthX.modules.player.BedNuker;
import net.minecraft.client.StealthX.modules.player.Derp;
import net.minecraft.client.StealthX.modules.player.AntiBlindness;
import net.minecraft.client.StealthX.modules.player.AntiHunger;
import net.minecraft.client.StealthX.modules.player.AutoCraft;
import net.minecraft.client.StealthX.modules.player.AutoFriend;
import net.minecraft.client.StealthX.modules.render.NameTags;
import net.minecraft.client.StealthX.modules.render.CameraZoom;
import net.minecraft.client.StealthX.modules.render.Chams;
import net.minecraft.client.StealthX.modules.render.Waypoints;
import net.minecraft.client.StealthX.modules.render.Tracers;
import net.minecraft.client.StealthX.modules.render.ChestESP;
import net.minecraft.client.StealthX.modules.render.BlockESP;
import net.minecraft.client.StealthX.modules.render.ItemESP;
import net.minecraft.client.StealthX.modules.render.EntityESP;
import net.minecraft.client.StealthX.modules.render.Radar;
import net.minecraft.client.StealthX.modules.render.OutlineESP;
import net.minecraft.client.StealthX.modules.render.XRay;
import net.minecraft.client.StealthX.modules.render.HitBox;
import net.minecraft.client.StealthX.modules.render.CustomHUD;
import net.minecraft.client.StealthX.modules.render.TimeChanger;
import net.minecraft.client.StealthX.modules.render.WeatherChanger;
import net.minecraft.client.StealthX.modules.render.ParticleTrails;
import net.minecraft.client.StealthX.modules.render.FakeDamage;
import net.minecraft.client.StealthX.modules.render.CustomFog;
import net.minecraft.client.StealthX.modules.render.HealthESP;
import net.minecraft.client.StealthX.modules.render.ArmorESP;
import net.minecraft.client.StealthX.modules.render.Glow;
import net.minecraft.client.StealthX.modules.render.ShadowESP;
import net.minecraft.client.StealthX.modules.render.DamageOverlay;
import net.minecraft.client.StealthX.modules.movement.WEEEE;


import net.minecraft.client.StealthX.ui.HUD;

public class Client {
	public static String name = "Cocain", version = "1";
	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
	public static HUD hud = new HUD();

	// Method for adding a glowing effect to text
	public static void drawGlowingText(String text, int x, int y, int color, int glowColor) {
	}

	public static void startup() {
		System.out.println("Starting " + name + " " + version);

		modules.add(new fly());		
		modules.add(new Scaffold());		
		modules.add(new Fullbright());
		modules.add(new TabGUI());
		modules.add(new AimBot());
		modules.add(new AutoClicker());
		modules.add(new Reach());
		modules.add(new Criticals());
		modules.add(new AntiKnockback());
		modules.add(new WEEEE());
		modules.add(new Velocity());
		modules.add(new AutoPot());
		modules.add(new BowAimBot());
		modules.add(new NoSwing());
		modules.add(new FastBow());
		modules.add(new TriggerBot());
		modules.add(new AutoGapple());
		modules.add(new WTap());
		modules.add(new TargetHUD());
		modules.add(new AutoSoup());
		modules.add(new SafeAura());
		modules.add(new BowSpam());
		modules.add(new HitParticles());
		modules.add(new PvPStats());
		modules.add(new ArmorBreaker());
		modules.add(new ComboBot());
		modules.add(new BlockHit());
		modules.add(new QuickSwap());
		modules.add(new SmartAttack());

		modules.add(new Speed());
		modules.add(new Spider());
		modules.add(new Step());
		modules.add(new NoFall());
		modules.add(new HighJump());
		modules.add(new Jesus());
		modules.add(new Glide());
		modules.add(new Dolphin());
		modules.add(new Teleport());
		modules.add(new Phase());
		modules.add(new ClickTP());
		modules.add(new Timer());
		modules.add(new FastLadder());
		modules.add(new BlockPhase());
		modules.add(new NoClip());
		modules.add(new SafeWalk());
		modules.add(new ParkourAssist());
		modules.add(new WallRun());
		modules.add(new ReverseGravity());
		modules.add(new AirWalk());
		modules.add(new ElytraBoost());
		modules.add(new LadderBoost());
		modules.add(new BunnyHop());

		modules.add(new AutoArmor());
		modules.add(new AutoTool());
		modules.add(new InventoryMove());
		modules.add(new AutoEat());
		modules.add(new AntiAFK());
		modules.add(new AutoFish());
		modules.add(new AutoFarm());
		modules.add(new AutoBridge());
		modules.add(new FastPlace());
		modules.add(new AutoMine());
		modules.add(new ChestStealer());
		modules.add(new FakePlayer());
		modules.add(new SkinStealer());
		modules.add(new MultiAccount());
		modules.add(new ExploitFinder());
		modules.add(new ServerCrasher());
		modules.add(new ChatSpammer());
		modules.add(new TrollAura());
		modules.add(new AutoRespawn());
		modules.add(new BedNuker());
		modules.add(new Derp());
		modules.add(new AntiBlindness());
		modules.add(new AntiHunger());
		modules.add(new AutoCraft());
		modules.add(new AutoFriend());

		modules.add(new NameTags());
		modules.add(new CameraZoom());
		modules.add(new Chams());
		modules.add(new Waypoints());
		modules.add(new Tracers());
		modules.add(new ChestESP());
		modules.add(new BlockESP());
		modules.add(new ItemESP());
		modules.add(new EntityESP());
		modules.add(new Radar());
		modules.add(new OutlineESP());
		modules.add(new XRay());
		modules.add(new HitBox());
		modules.add(new CustomHUD());
		modules.add(new TimeChanger());
		modules.add(new WeatherChanger());
		modules.add(new ParticleTrails());
		modules.add(new FakeDamage());
		modules.add(new CustomFog());
		modules.add(new HealthESP());
		modules.add(new ArmorESP());
		modules.add(new Glow());
		modules.add(new ShadowESP());
		modules.add(new DamageOverlay());


	}

	public static void onEvent(Event<?> e) {
		for (Module m : modules) {
			if (!m.toggled)
				continue;
			m.onEvent(e);
		}
	}

	public static void keyPress(int key) {
		Client.onEvent(new EventKey(key));
		for (Module m : modules) {
			if (m.getKey() == key) {
				m.toggle();
			}
		}
	}

	public static List<Module> getModulesByCategory(Category c) {
		List<Module> modules = new ArrayList<Module>();

		for (Module m : Client.modules) {
			if (m.category == c)
				modules.add(m);
		}
		return modules;
	}

	// Example method for rendering glowing text, can be used in GUI rendering
	public static void renderClientName() {
		// Call the drawGlowingText method with the name "StealthX" and a glowing color
		// effect
		drawGlowingText(name, 50, 50, 0xFFFFFF, 0xFF00FF); // White text with a pink glow
	}
}
