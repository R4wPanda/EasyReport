package me.r4wpanda.easyreport.reportsystem.menus;

import me.r4wpanda.easyreport.EasyReportMain;
import me.r4wpanda.easyreport.menusystem.PaginatedMenu;
import me.r4wpanda.easyreport.menusystem.PlayerMenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.UUID;

import static org.bukkit.Bukkit.getPlayer;
import static org.bukkit.Bukkit.getServer;

public class ReportMenuPlayer extends PaginatedMenu {

    public ReportMenuPlayer(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.RED + "Report a player!";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        ArrayList<Player> players = new ArrayList<Player>(getServer().getOnlinePlayers());

        if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {

            PlayerMenuUtility playerMenuUtility = EasyReportMain.getPlayerMenuUtility(p);
            playerMenuUtility.setPlayerToReport(Bukkit.getPlayer(UUID.fromString(e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(EasyReportMain.getPlugin(), "uuid"), PersistentDataType.STRING))));

            new ReportMenuReason(playerMenuUtility).open();

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
                if (!((index + 1) >= players.size())){
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

        ArrayList<Player> playerList = new ArrayList<Player>(getServer().getOnlinePlayers());

        if(playerList != null && !playerList.isEmpty()) {
            for(int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if(index >= playerList.size()) break;
                if (playerList.get(index) != null){

                    ItemStack playerItem = new ItemStack(Material.PLAYER_HEAD, 1);
                    SkullMeta playerSkullMeta = (SkullMeta) playerItem.getItemMeta();
                    playerSkullMeta.setOwningPlayer(playerList.get(index));
                    playerSkullMeta.setDisplayName(ChatColor.GRAY +
                            "Report: " +
                            ChatColor.RED +
                            playerList.get(index).getDisplayName());

                    playerSkullMeta.getPersistentDataContainer().set(new NamespacedKey(EasyReportMain.getPlugin(), "uuid"),
                            PersistentDataType.STRING,
                            playerList.get(index).getUniqueId().toString());

                    playerItem.setItemMeta(playerSkullMeta);

                    inventory.addItem(playerItem);


                }
            }
        }
    }
}
