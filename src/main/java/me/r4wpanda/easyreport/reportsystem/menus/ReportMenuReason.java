package me.r4wpanda.easyreport.reportsystem.menus;

import me.r4wpanda.easyreport.EasyReportMain;
import me.r4wpanda.easyreport.menusystem.PaginatedMenu;
import me.r4wpanda.easyreport.menusystem.PlayerMenuUtility;
import me.r4wpanda.easyreport.reportsystem.Reason;
import me.r4wpanda.easyreport.reportsystem.Report;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ReportMenuReason extends PaginatedMenu {

    public ReportMenuReason(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Choose a valid reason!";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        ArrayList<Reason> reasonArrayList = new ArrayList<Reason>();
        for (Reason r : Reason.values()) {
            reasonArrayList.add(r);
        }

        if (e.getCurrentItem().getType().equals(Material.BOOK)) {

            PlayerMenuUtility playerMenuUtility = EasyReportMain.getPlayerMenuUtility(p);
            Reason reason = Reason.valueOf(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));

            p.closeInventory();

            Report.updateReports(new Report(playerMenuUtility.getOwner(), playerMenuUtility.getPlayerToReport(), reason));


        }else if (e.getCurrentItem().getType().equals(Material.BARRIER)) {

            //close inventory
            p.closeInventory();
            p.sendMessage(ChatColor.RED.toString() + ChatColor.ITALIC.toString() + "Closing Report Menu..." );

        }else if(e.getCurrentItem().getType().equals(Material.ARROW)){
            if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Previous page!")){
                if (page == 0){
                    p.sendMessage(ChatColor.GRAY + "You are already on the first page.");
                }else{
                    page = page - 1;
                    super.open();
                }
            }else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Next page!")){
                if (!((index + 1) >= reasonArrayList.size())){
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

        ArrayList<Reason> reasonArrayList = new ArrayList<Reason>();
        for (Reason r : Reason.values()) {
            reasonArrayList.add(r);
        }

        if(reasonArrayList != null && !reasonArrayList.isEmpty()) {
            for(int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if(index >= reasonArrayList.size()) break;
                if (reasonArrayList.get(index) != null){

                    ItemStack reasonItem = new ItemStack(Material.BOOK, 1);
                    ItemMeta reasonMeta = reasonItem.getItemMeta();

                    reasonMeta.setDisplayName(ChatColor.BLUE.toString() + ChatColor.BOLD.toString() + reasonArrayList.get(index));
                    List<String> loreList = new ArrayList<>();
                    loreList.add(reasonArrayList.get(index).getText());
                    reasonMeta.setLore(loreList);
                    reasonItem.setItemMeta(reasonMeta);

                    inventory.addItem(reasonItem);

                }
            }
        }
    }
}
