package dev.nerdtimmie.nftworlds.trialplugin.gui.menus;

import dev.nerdtimmie.nftworlds.trialplugin.Trialplugin;
import dev.nerdtimmie.nftworlds.trialplugin.gui.items.ConfirmTransactionItem;
import dev.nerdtimmie.nftworlds.trialplugin.gui.items.NetworkChooseItem;
import dev.nerdtimmie.nftworlds.trialplugin.utils.ShopItemBuilder;
import ninja.amp.ampmenus.items.MenuItem;
import ninja.amp.ampmenus.menus.ItemMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ConfirmPurchaseItemMenu extends ItemMenu {

    private final Double price;
    private final Integer amount;
    private final Material item;

    public ConfirmPurchaseItemMenu(Trialplugin plugin, Double price, Integer amount, Material item, Player player) {
        super(ChatColor.BLUE + "Shop", Size.ONE_LINE, plugin);
        this.price = price;
        this.amount = amount;
        this.item = item;

        NetworkChooseItem networkChooseItem = new NetworkChooseItem();


        setItem(0, new ConfirmTransactionItem("Buy x8", 8, true, price, amount, item, networkChooseItem.getValue(player), plugin));
        setItem(1, new ConfirmTransactionItem("Buy x4", 4, true, price, amount, item, networkChooseItem.getValue(player), plugin));
        setItem(2, new ConfirmTransactionItem("Buy x2", 2, true, price, amount, item, networkChooseItem.getValue(player), plugin));
        setItem(3, new ConfirmTransactionItem("Buy x1", 1, true, price, amount, item, networkChooseItem.getValue(player), plugin));
        setItem(4, new ConfirmTransactionItem("Sell x1", 1, false, price, amount, item, networkChooseItem.getValue(player), plugin));
        setItem(5, new ConfirmTransactionItem("Sell x2", 2, false, price, amount, item, networkChooseItem.getValue(player), plugin));
        setItem(6, new ConfirmTransactionItem("Sell x4", 4, false, price, amount, item, networkChooseItem.getValue(player), plugin));
        setItem(7, new ConfirmTransactionItem("Sell x8", 8, false, price, amount, item, networkChooseItem.getValue(player), plugin));


        setItem(8, networkChooseItem);
    }

}
