package dev.nerdtimmie.nftworlds.trialplugin.gui.items;

import dev.nerdtimmie.nftworlds.trialplugin.Network;
import ninja.amp.ampmenus.items.ToggleableMenuItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static dev.nerdtimmie.nftworlds.trialplugin.Network.*;

/**
 * An item so players can choose the specific network.
 */
public class NetworkChooseItem extends ToggleableMenuItem {


    private final HashMap<Player, Network> playerNetworkHashMap;

    public NetworkChooseItem() {
        super(ChatColor.GOLD + "Network");

        playerNetworkHashMap = new HashMap<>();
    }

    @Override
    public void toggleValue(Player player) {
        Network network = playerNetworkHashMap.getOrDefault(player, ETHEREUM);
        if (network == ETHEREUM) {
            playerNetworkHashMap.put(player, POLYGON);
        } else {
            playerNetworkHashMap.put(player, ETHEREUM);
        }
    }

    @Override
    public boolean getValue(Player player) {
        return playerNetworkHashMap.getOrDefault(player, ETHEREUM).isEthereum;
    }

    @Override
    public ItemStack getFinalIcon(Player player) {
        return playerNetworkHashMap.getOrDefault(player, ETHEREUM).getIcon();
    }
}
