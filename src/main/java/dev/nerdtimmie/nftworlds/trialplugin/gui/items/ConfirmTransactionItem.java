package dev.nerdtimmie.nftworlds.trialplugin.gui.items;


import com.nftworlds.wallet.api.WalletAPI;
import com.nftworlds.wallet.objects.Network;
import dev.nerdtimmie.nftworlds.trialplugin.Trialplugin;
import dev.nerdtimmie.nftworlds.trialplugin.payloads.PlayerBuyItemTransaction;
import net.kyori.adventure.text.Component;
import ninja.amp.ampmenus.events.ItemClickEvent;
import ninja.amp.ampmenus.items.MenuItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ListIterator;

public class ConfirmTransactionItem extends MenuItem {

    private final Integer times, amount;
    private final Boolean isBuy, isEthereum;
    private final Double price;
    private final Material item;
    private final Trialplugin plugin;


    public ConfirmTransactionItem(String displayName, Integer times, Boolean isBuy, Double price, Integer amount, Material item, Boolean isEthereum, Trialplugin plugin) {

        super(displayName, new ItemStack(Material.GRAY_CONCRETE_POWDER));

        this.times = times;
        this.isBuy = isBuy;
        this.price = price;
        this.amount = amount;
        this.item = item;
        this.isEthereum = isEthereum;
        this.plugin = plugin;
    }

    @Override
    public ItemStack getFinalIcon(Player player) {
        ItemStack itemStack;
        if (isBuy) {
            itemStack = new ItemStack(Material.GREEN_CONCRETE_POWDER, times);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.displayName(Component.text("Buy " + times + "X"));
            itemStack.setItemMeta(itemMeta);
        } else {
            itemStack = new ItemStack(Material.RED_CONCRETE_POWDER, times);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.displayName(Component.text("Sell " + times + "X"));
            itemStack.setItemMeta(itemMeta);
        }

        ItemMeta itemMeta = itemStack.getItemMeta();

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public void onItemClick(ItemClickEvent event) {
        event.setWillClose(true);
        Player player = event.getPlayer();
        WalletAPI WalletAPI = plugin.getService(WalletAPI.class);

        if (isBuy) {
            WalletAPI.getNFTPlayer(player).requestWRLD(price * times,
                    isEthereum ? Network.ETHEREUM : Network.POLYGON,
                    "Bought items",
                    false,      // don't know what this means.
                    new PlayerBuyItemTransaction(new ItemStack(item, amount), times));
        } else {

            if (!containsEnoughItems(player)) {
                player.sendMessage("Not enough items");
                return;
            }
            removeItems(player);
            WalletAPI.sendWRLD(player, amount * times, isEthereum ? Network.ETHEREUM : Network.POLYGON, "Sold items");

        }
    }

    private Boolean containsEnoughItems(Player player) {
        return player.getInventory().contains(item, amount * times);
    }

    private void removeItems(Player player) {
        int totalAmount = amount * times;
        for (ItemStack next : player.getInventory()) {
            if(next == null) {
                continue;
            }
            if (next.getType() == item) {
                int amount1 = next.getAmount();
                if (totalAmount >= amount1) {
                    next.subtract(amount1);
                    totalAmount = totalAmount - amount1;
                }
                if (totalAmount < amount1) {
                    next.subtract(amount1);
                }
            }
        }
    }
}
