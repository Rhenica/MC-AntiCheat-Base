package com.acbase.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PlayerLocation {
    final double x, y, z;
    final float yaw, pitch;

    public PlayerLocation(final double x, final double y, final double z, final float yaw, final float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public PlayerLocation(final Location loc) {
        this.x = loc.getX();
        this.y = loc.getY();
        this.z = loc.getZ();
        this.yaw = loc.getYaw();
        this.pitch = loc.getPitch();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public Location getBukkitLoc(Player player) {
        return new Location(player.getWorld(),x,y,z,yaw,pitch);
    }

    public Location getBukkitLoc(World world) {
        return new Location(world,x,y,z,yaw,pitch);
    }
}
