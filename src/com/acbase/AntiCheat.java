package com.acbase;

import com.acbase.check.CheckManager;
import com.acbase.event.EventListener;
import com.acbase.event.PacketListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class AntiCheat extends JavaPlugin {
    private static AntiCheat instance;
    private static CheckManager checkManager;
    public static String alertPerm = "anticheat.alerts";
    public static String prefix = "&8[&2AntiCheat&8] &7";
    public static String pc = "&7";
    public static String sc = "&2";
    public static ArrayList<EventListener> listeners = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        checkManager = new CheckManager();
        Bukkit.getPluginManager().registerEvents(checkManager, instance);
        Bukkit.getPluginManager().registerEvents(new PacketListener(), instance);
    }

    @Override
    public void onDisable() {

    }

    public static AntiCheat getInstance() {
        return instance;
    }

    public static CheckManager getCheckManager() {
        return checkManager;
    }
}
