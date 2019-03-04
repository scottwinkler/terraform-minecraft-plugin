package com.swinkler.terraform;

//import com.cocoapebbles.terraform.commands.CommandHandler;
import com.swinkler.terraform.services.BukkitService;
import com.swinkler.terraform.web.WebServer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public class Main extends JavaPlugin implements Listener{
        private Logger logger;
        private WebServer webServer;

        public Main(){
            logger = this.getLogger();
        }
        @Override
        public void onEnable(){
            registerCommands();
            registerEvents();
            initializeWebApp();
            initializeBukkitClient();
        }

        public void initializeBukkitClient(){
            BukkitService.initialize(this);
        }

        @Override
        public void onDisable(){
            webServer.shutDownServer();
        }

        public void registerCommands(){

            //getCommand("tf").setExecutor(new CommandHandler());
        }

        public void registerEvents()
        {
            PluginManager pm = getServer().getPluginManager();
        }

        public void initializeWebApp(){
            loadConfig();
            webServer = new WebServer(this);
        }
        private void loadConfig(){
            FileConfiguration config = this.getConfig();
            config.addDefault("server.port",8080);
            config.addDefault("server.enable",true);
            config.options().copyDefaults(true);
            this.saveConfig();
        }
}
