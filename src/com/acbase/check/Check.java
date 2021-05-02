package com.acbase.check;

import com.acbase.AntiCheat;
import com.acbase.event.Event;
import com.acbase.event.EventListener;
import com.acbase.event.events.MoveEvent;
import com.acbase.event.events.PacketReceiveEvent;
import com.acbase.event.events.PacketSendEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Check extends EventListener {
    public final Player player;
    private final String name;
    public final int max;
    private final boolean experimental;
    private double vl = 0;
    private long lastPunish = System.currentTimeMillis();

    public Check(final Player player, final String name, final int max, final boolean experimental) {
        this.player = player;
        this.name = name;
        this.max = max;
        this.experimental = experimental;
    }

    public String getName() {
        return name;
    }

    public boolean isExperimental() {
        return experimental;
    }

    public void onMove(MoveEvent e) {

    }

    public void onPacketSend(PacketSendEvent e) {

    }

    public void onPacketReceive(PacketReceiveEvent e) {

    }

    @Override
    public void onEvent(Event e) {
        if (e.getPlayer() != player) return;
        if (e instanceof MoveEvent) onMove((MoveEvent) e);
        if (e instanceof PacketSendEvent) onPacketSend((PacketSendEvent) e);
        if (e instanceof PacketReceiveEvent) onPacketReceive((PacketReceiveEvent) e);
    }

    public String format(String format) {
        return ChatColor.translateAlternateColorCodes('&', format);
    }

    public void flag() {
        vl++;
        for (Player staff : Bukkit.getOnlinePlayers()) {
            if (staff.hasPermission(AntiCheat.alertPerm)) {
                staff.sendMessage(format(AntiCheat.prefix + AntiCheat.sc + player.getName() + AntiCheat.pc + " failed " + AntiCheat.sc + this.getName() + AntiCheat.pc + " &8[&7VL: " + AntiCheat.sc + Math.round(vl) + "&8]"));
            }
        }
        if (vl >= max) {
            vl = 0;
            kick();
        }
    }

    public void flag(double multiplier) {
        vl += multiplier;
        for (Player staff : Bukkit.getOnlinePlayers()) {
            if (staff.hasPermission(AntiCheat.alertPerm)) {
                staff.sendMessage(format(AntiCheat.prefix + AntiCheat.sc + player.getName() + AntiCheat.pc + " failed " + AntiCheat.sc + this.getName() + AntiCheat.pc + " &8[&7VL: " + AntiCheat.sc + Math.round(vl) + "&8]"));
            }
        }
        if (vl >= max) {
            vl = 0;
            kick();
        }
    }

    public void flag(String info) {
        vl++;
        for (Player staff : Bukkit.getOnlinePlayers()) {
            if (staff.hasPermission(AntiCheat.alertPerm)) {
                staff.sendMessage(format(AntiCheat.prefix + AntiCheat.sc + player.getName() + AntiCheat.pc + " failed " + AntiCheat.sc + this.getName() + AntiCheat.pc + ": " + info + " &8[&7VL: " + AntiCheat.sc + Math.round(vl) + "&8]"));
            }
        }
        if (vl >= max) {
            vl = 0;
            kick();
        }
    }

    public void flag(double multiplier, String info) {
        vl += multiplier;
        for (Player staff : Bukkit.getOnlinePlayers()) {
            if (staff.hasPermission(AntiCheat.alertPerm)) {
                staff.sendMessage(format(AntiCheat.prefix + AntiCheat.sc + player.getName() + AntiCheat.pc + " failed " + AntiCheat.sc + this.getName() + AntiCheat.pc + ": " + info + " &8[&7VL: " + AntiCheat.sc + Math.round(vl) + "&8]"));
            }
        }
        if (vl >= max) {
            vl = 0;
            kick();
        }
    }

    public void pass(double multiplier) {
        vl = Math.max(vl - multiplier, 0);
    }

    @SuppressWarnings("deprecation")
    public void kick() {
        if (System.currentTimeMillis() - lastPunish > 200) {
            Bukkit.getScheduler().runTask(AntiCheat.getInstance(), new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.broadcastMessage(format(AntiCheat.prefix + AntiCheat.sc + player.getName() + AntiCheat.pc + " was kicked for " + AntiCheat.sc + name));
                    player.kickPlayer(format(AntiCheat.prefix + AntiCheat.sc + name));
                }
            });
        }
        lastPunish = System.currentTimeMillis();
    }
}
