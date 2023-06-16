package me.r4wpanda.easyreport;

import me.r4wpanda.easyreport.commands.ReportCommand;
import me.r4wpanda.easyreport.commands.ReportListCommand;
import me.r4wpanda.easyreport.listeners.MenuListener;
import me.r4wpanda.easyreport.menusystem.PlayerMenuUtility;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class EasyReportMain extends JavaPlugin {

    private static EasyReportMain plugin;

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        getCommand("report").setExecutor(new ReportCommand());
        getCommand("reportlist").setExecutor(new ReportListCommand());

        //Menu listener system
        getServer().getPluginManager().registerEvents(new MenuListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    //Provide a player and return a menu system for that player
    //create one if they don't already have one
    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(p))) { //See if the player has a playermenuutility "saved" for them

            //This player doesn't. Make one for them and add it to the hashmap
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(p); //Return the object by using the provided player
        }
    }

    public static EasyReportMain getPlugin() {
        return plugin;
    }
}

