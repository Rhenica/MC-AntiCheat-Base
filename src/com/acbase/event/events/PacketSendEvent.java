package com.acbase.event.events;

import com.acbase.event.Event;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Player;

public class PacketSendEvent extends Event {
    PacketContainer packet;

    public PacketSendEvent(Player player, PacketContainer packet) {
        super(player);
        this.packet = packet;
    }

    public PacketContainer getPacket() {
        return packet;
    }
}