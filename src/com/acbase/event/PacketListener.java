package com.acbase.event;

import com.acbase.AntiCheat;
import com.acbase.event.events.MoveEvent;
import com.acbase.event.events.PacketReceiveEvent;
import com.acbase.event.events.PacketSendEvent;
import com.acbase.util.PlayerLocation;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class PacketListener implements Listener {
    public static HashMap<Player, PlayerLocation> lastLocs = new HashMap<>();
    public static HashMap<Player, PlayerLocation> locs = new HashMap<>();

    public PacketListener() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(AntiCheat.getInstance(), ListenerPriority.NORMAL, PacketType.Play.Client.FLYING) {
            @Override
            public void onPacketReceiving(PacketEvent e) {
                onFlying(e.getPlayer(), e);
            }
        });
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(AntiCheat.getInstance(), ListenerPriority.NORMAL, PacketType.Play.Client.POSITION) {
            @Override
            public void onPacketReceiving(PacketEvent e) {
                onPosition(e.getPlayer(), e);
            }
        });
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(AntiCheat.getInstance(), ListenerPriority.NORMAL, PacketType.Play.Client.LOOK) {
            @Override
            public void onPacketReceiving(PacketEvent e) {
                onLook(e.getPlayer(), e);
            }
        });
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(AntiCheat.getInstance(), ListenerPriority.NORMAL, PacketType.Play.Client.POSITION_LOOK) {
            @Override
            public void onPacketReceiving(PacketEvent e) {
                onPosLook(e.getPlayer(), e);
            }
        });
        for (PacketType packetType : PacketType.values()) {
            if (packetType.isSupported()) {
                if (packetType.isClient()) {
                    ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(AntiCheat.getInstance(), ListenerPriority.NORMAL, packetType) {
                        @Override
                        public void onPacketReceiving(PacketEvent e) {
                            onPacketReceive(e.getPlayer(), e.getPacket());
                        }
                    });
                }
                if (packetType.isServer()) {
                    ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(AntiCheat.getInstance(), ListenerPriority.NORMAL, packetType) {
                        @Override
                        public void onPacketSending(PacketEvent e) {
                            onPacketSend(e.getPlayer(), e.getPacket());
                        }
                    });
                }
            }
        }
    }

    public void onPacketReceive(Player player, PacketContainer packet) {
        PacketReceiveEvent e = new PacketReceiveEvent(player, packet);
        for (EventListener listener : AntiCheat.listeners) {
            listener.onEvent(e);
        }
    }

    public void onPacketSend(Player player, PacketContainer packet) {
        PacketSendEvent e = new PacketSendEvent(player, packet);
        for (EventListener listener : AntiCheat.listeners) {
            listener.onEvent(e);
        }
    }

    public void onFlying(Player player, PacketEvent event) {
        PlayerLocation loc = locs.get(player);
        lastLocs.put(player, loc);
        locs.put(player, loc);
        MoveEvent e = new MoveEvent(player, loc, loc, event.getPacket().getBooleans().read(0));
        for (EventListener listener : AntiCheat.listeners) {
            listener.onEvent(e);
        }
    }

    public void onPosition(Player player, PacketEvent event) {
        PlayerLocation from = locs.get(player);
        PlayerLocation to = new PlayerLocation(event.getPacket().getDoubles().read(0), event.getPacket().getDoubles().read(1), event.getPacket().getDoubles().read(2), from.getYaw(), from.getPitch());
        lastLocs.put(player, from);
        locs.put(player, to);
        MoveEvent e = new MoveEvent(player, to, from, event.getPacket().getBooleans().read(0));
        for (EventListener listener : AntiCheat.listeners) {
            listener.onEvent(e);
        }
    }

    public void onLook(Player player, PacketEvent event) {
        PlayerLocation from = locs.get(player);
        PlayerLocation to = new PlayerLocation(from.getX(), from.getY(), from.getZ(), event.getPacket().getFloat().read(0), event.getPacket().getFloat().read(1));
        lastLocs.put(player, from);
        locs.put(player, to);
        MoveEvent e = new MoveEvent(player, to, from, event.getPacket().getBooleans().read(0));
        for (EventListener listener : AntiCheat.listeners) {
            listener.onEvent(e);
        }
    }

    public void onPosLook(Player player, PacketEvent event) {
        PlayerLocation from = locs.get(player);
        PlayerLocation to = new PlayerLocation(event.getPacket().getDoubles().read(0), event.getPacket().getDoubles().read(1), event.getPacket().getDoubles().read(2), event.getPacket().getFloat().read(0), event.getPacket().getFloat().read(1));
        lastLocs.put(player, from);
        locs.put(player, to);
        MoveEvent e = new MoveEvent(player, to, from, event.getPacket().getBooleans().read(0));
        for (EventListener listener : AntiCheat.listeners) {
            listener.onEvent(e);
        }
    }
}
