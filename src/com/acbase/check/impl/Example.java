package com.acbase.check.impl;

import com.acbase.check.Check;
import org.bukkit.entity.Player;

public class Example extends Check {
    public Example(Player player, String name, int max, boolean experimental) {
        super(player, name, max, experimental);
    }
}
