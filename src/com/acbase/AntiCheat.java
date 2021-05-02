package com.acbase;

import com.acbase.event.EventListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class AntiCheat extends JavaPlugin {
    private static AntiCheat instance;
    public static String alertPerm = "anticheat.alerts";
    public static String prefix = "&8[&2AntiCheat&8] &7";
    public static String pc = "&7";
    public static String sc = "&2";
    public static ArrayList<EventListener> listeners = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {

    }

    public static AntiCheat getInstance() {
        return instance;
    }
}
