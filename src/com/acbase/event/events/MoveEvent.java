package com.acbase.event.events;

import com.acbase.event.Event;
import com.acbase.util.PlayerLocation;
import org.bukkit.entity.Player;

public class MoveEvent extends Event {
    protected final PlayerLocation to;
    protected final PlayerLocation from;
    protected final boolean onGround;

    public MoveEvent(final Player player, final PlayerLocation to, final PlayerLocation from, final boolean onGround) {
        super(player);
        this.to = to;
        this.from = from;
        this.onGround = onGround;
    }

    public PlayerLocation getTo() {
        return to;
    }

    public PlayerLocation getFrom() {
        return from;
    }

    public boolean isOnGround() {
        return onGround;
    }
}
