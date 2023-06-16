package me.r4wpanda.easyreport.commands;

import me.r4wpanda.easyreport.EasyReportMain;
import me.r4wpanda.easyreport.reportsystem.menus.ReportMenuPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            //create the menu and then open it for the player
            new ReportMenuPlayer(EasyReportMain.getPlayerMenuUtility(p)).open();

        }

        return true;
    }

}
