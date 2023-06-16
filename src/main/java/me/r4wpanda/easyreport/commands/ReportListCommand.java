package me.r4wpanda.easyreport.commands;

import me.r4wpanda.easyreport.EasyReportMain;
import me.r4wpanda.easyreport.reportsystem.menus.ReportMenuStaff;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (p.hasPermission("easyreport.staff")) {
                new ReportMenuStaff(EasyReportMain.getPlayerMenuUtility(p)).open();
                return true;
            }

            p.sendMessage(ChatColor.RED + "You dont not have the right permission!");
        }

        return true;
    }
}
