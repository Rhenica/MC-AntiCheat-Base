package com.acbase.check;

import com.acbase.AntiCheat;
import com.acbase.check.impl.Example;
import com.acbase.event.PacketListener;
import com.acbase.util.PlayerLocation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CheckManager implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        AntiCheat.listeners.add(new Example(e.getPlayer(), "Example", 10, false));
        PacketListener.lastLocs.put(e.getPlayer(), new PlayerLocation(e.getPlayer().getLocation()));
        PacketListener.locs.put(e.getPlayer(), new PlayerLocation(e.getPlayer().getLocation()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        AntiCheat.listeners.remove(new Example(e.getPlayer(), "Example", 10, false));
        PacketListener.lastLocs.remove(e.getPlayer());
        PacketListener.locs.remove(e.getPlayer());
    }
}
