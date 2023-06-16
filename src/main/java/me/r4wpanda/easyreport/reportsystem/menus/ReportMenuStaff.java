package me.r4wpanda.easyreport.reportsystem.menus;

import me.r4wpanda.easyreport.menusystem.PaginatedMenu;
import me.r4wpanda.easyreport.menusystem.PlayerMenuUtility;
import me.r4wpanda.easyreport.reportsystem.Report;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ReportMenuStaff extends PaginatedMenu {

    public ReportMenuStaff(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "List of player-reports!";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        ArrayList<Report> reportList = new ArrayList<Report>();
        for (Report r : Report.getReportList()) {
            reportList.add(r);
        }

        if (e.getCurrentItem().getType().equals(Material.PAPER)) {

            if (e.getClick().isLeftClick()) {
                String[] splitName = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).split("#");
                int i = Integer.parseInt(splitName[1].trim());
                Report.removeReport(i-1);
                p.closeInventory();
            }


        }else if (e.getCurrentItem().getType().equals(Material.BARRIER)) {

            //close inventory
            p.closeInventory();
            p.sendMessage(ChatColor.RED.toString() + ChatColor.ITALIC.toString() + "Closing ReportList Menu..." );

        }else if(e.getCurrentItem().getType().equals(Material.ARROW)){
            if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Previous page!")){
                if (page == 0){
                    p.sendMessage(ChatColor.GRAY + "You are already on the first page.");
                }else{
                    page = page - 1;
                    super.open();
                }
            }else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Next page!")){
                if (!((index + 1) >= reportList.size())){
                    page = page + 1;
                    super.open();
                }else{
                    p.sendMessage(ChatColor.GRAY + "You are on the last page.");
                }
            }
        }
    }

    @Override
    public void setMenuItems() {

        addMenuBorder();

        ArrayList<Report> reportList = new ArrayList<Report>();
        for (Report r : Report.getReportList()) {
            reportList.add(r);
        }

        if(reportList != null && !reportList.isEmpty()) {
            for(int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if(index >= reportList.size()) break;
                if (reportList.get(index) != null){

                    ItemStack reportItem = new ItemStack(Material.PAPER);
                    ItemMeta reportMeta = reportItem.getItemMeta();
                    reportMeta.setDisplayName(ChatColor.RED + "Report " + ChatColor.GRAY + "#" + (reportList.size() - i));

                    ArrayList<String> reportLore = new ArrayList<>();
                    reportLore.add(ChatColor.BLUE + "Reported by: " + ChatColor.GRAY + reportList.get(index).getReportWriter().getName());
                    reportLore.add(ChatColor.BLUE + "Reported player: " + ChatColor.GRAY + reportList.get(index).getReportedPlayer().getName());
                    reportLore.add(ChatColor.BLUE + "Reason: " + ChatColor.GRAY + reportList.get(index).getReason().getText());
                    reportLore.add(ChatColor.BLUE + "Date: " + ChatColor.GRAY + reportList.get(index).getTimeOfReport());
                    reportLore.add("");
                    reportLore.add(ChatColor.BLUE + "[" + ChatColor.RED + "-" + ChatColor.BLUE + "] " + ChatColor.GRAY + "Left-click to remove report");
                    reportMeta.setLore(reportLore);

                    reportItem.setItemMeta(reportMeta);

                    inventory.addItem(reportItem);

                }
            }
        }
    }
}

