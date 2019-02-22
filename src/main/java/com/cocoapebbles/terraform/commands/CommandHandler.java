package com.cocoapebbles.terraform.commands;
/*
import com.cocoapebbles.terraform.models.cube.CubeResourceData;
import com.cocoapebbles.terraform.models.cylinder.CylinderResourceData;
import com.cocoapebbles.terraform.models.cube.CubeDimensions;
import com.cocoapebbles.terraform.models.cylinder.CylinderDimensions;
import com.cocoapebbles.terraform.provider.ResourceCylinder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import com.cocoapebbles.terraform.provider.ResourceCube;

import java.util.Arrays;
import com.cocoapebbles.terraform.utility.Utility;

public class CommandHandler implements CommandExecutor {
    private net.md_5.bungee.api.ChatColor chatColor = ChatColor.RED;

    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
        String[] otherArgs = Arrays.copyOfRange(args, 1, args.length);
        switch(args[0]){
            case "cube": return cubeHandler(sender,otherArgs);
//            case "help": return helpHandler(sender,otherArgs);
            case "heart": return heartHandler(sender);
            case "cylinder": return cylinderHandler(sender, otherArgs);
            case "pig": return pigHandler(sender, otherArgs);
            default: return false;
        }
    }

    public boolean cubeHandler(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        World w = player.getWorld();
        CubeResourceData data = new CubeResourceData();
        data.setLocation(new Location(w, 150, 150 ,150));
        data.setMaterialId("GREEN_CONCRETE");

        data.setCubeDimensions(new CubeDimensions(5, 5, 5));


        ResourceCube rcube = new ResourceCube();
        rcube.create(data);
        return true;
    }

    public boolean cylinderHandler(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        World w = player.getWorld();
        CylinderResourceData data = new CylinderResourceData();
        data.setLocation(new Location(w, Integer.parseInt(args[0]), Integer.parseInt(args[1]) ,Integer.parseInt(args[2])));
        data.setMaterialId("BLUE_CONCRETE");

        data.setCylinderDimensions(new CylinderDimensions(50, 1));


        ResourceCylinder rcylinder = new ResourceCylinder();
        rcylinder.create(data);
        return true;
    }

    public boolean pigHandler(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        World w = player.getWorld();
        w.spawnEntity(new Location(w, Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2])), EntityType.PIG);
        return true;
    }

    public boolean heartHandler(CommandSender sender) {
        Player player = (Player) sender;
        Location playerLocation = player.getLocation();
        World w = player.getWorld();
        int x = playerLocation.getBlockX();
        int y = playerLocation.getBlockY();
        int z = playerLocation.getBlockZ();
        Utility.schedulePlacement(w, x+7, y-1, z-5, 1, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+6, y-1, z-5, 2, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+5, y-1, z-5, 3, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+4, y-1, z-4, 4, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+3, y-1, z-3, 5, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+2, y-1, z-2, 6, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+1, y-1, z-1, 7, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+0, y-1, z+0, 8, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+1, y-1, z+1, 9, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+2, y-1, z+2, 10, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+3, y-1, z+3, 11, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+4, y-1, z+4, 12, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+5, y-1, z+5, 13, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+6, y-1, z+5, 14, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+7, y-1, z+5, 15, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+8, y-1, z+4, 16, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+9, y-1, z+3, 17, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+9, y-1, z+2, 18, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+8, y-1, z+1, 19, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+7, y-1, z+0, 20, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+8, y-1, z-1, 21, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+9, y-1, z-2, 22, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+9, y-1, z-3, 23, "RED_CONCRETE");
        Utility.schedulePlacement(w, x+8, y-1, z-4, 24, "RED_CONCRETE");



        //success
        player.sendMessage(chatColor + "Heart is created!");
        return true;
    }
}


*/