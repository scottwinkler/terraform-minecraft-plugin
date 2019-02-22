package com.cocoapebbles.terraform.controllers;

import com.cocoapebbles.terraform.Main;
import org.bukkit.World;
import org.bukkit.event.Event;

import java.util.logging.Logger;

public class BukkitController {
    private static Main _main = null;
    public static Main getInstance(){
        return _main;
    }

    public static void callEvent(Event event){
        _main.getServer().getPluginManager().callEvent(event);
    }

    public static String getDataDir(){
        return _main.getDataFolder().getAbsolutePath();
    }

    public static Logger getLogger(){
        return _main.getLogger();
    }

    public static World getWorld() {return _main.getServer().getWorld("world");}
    public static void initialize(Main main){
        _main = main;
    }
}
