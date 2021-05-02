package com.acbase.event;

import org.bukkit.entity.Player;

public class Event {
    final Player player;
    boolean cancelled = false;

    public Event(final Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
