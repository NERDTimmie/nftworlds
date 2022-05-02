package dev.nerdtimmie.nftworlds.trialplugin.gui.menus;

import dev.nerdtimmie.nftworlds.trialplugin.Trialplugin;

import dev.nerdtimmie.nftworlds.trialplugin.storage.Config;
import dev.nerdtimmie.nftworlds.trialplugin.utils.ShopItemBuilder;
import ninja.amp.ampmenus.items.CloseItem;
import ninja.amp.ampmenus.items.MenuItem;
import ninja.amp.ampmenus.items.SubMenuItem;
import ninja.amp.ampmenus.menus.PaginatedItemMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class ShopMenu extends PaginatedItemMenu {

    private final Config shopItems;
    private final Trialplugin plugin;

    public ShopMenu(Trialplugin plugin) {
        super(ChatColor.BLUE + "Shop", plugin);
        this.shopItems = new Config("shop.yml", plugin);
        shopItems.saveDefaultConfig(false);
        this.plugin = plugin;

        setItem(6, 8, new CloseItem());
    }

    @Override
    public List<MenuItem> getItems(Player player) {
        FileConfiguration shopItemsConfig = shopItems.getConfig();
        Set<String> items = shopItemsConfig.getConfigurationSection("items").getKeys(false);
        List<MenuItem> menuItems = new ArrayList<>();

        for (String item : items) {
            Material material = Material.valueOf(shopItemsConfig.getString("items." + item + ".item"));
            Integer amount = shopItemsConfig.getInt("items." + item + ".amount");
            double price = shopItemsConfig.getDouble("items." + item + ".price");

            ItemStack shopItem = new ShopItemBuilder().setMaterial(material).setAmount(amount).setPrice(price).create();

            menuItems.add(new SubMenuItem(plugin,
                    "Click me to buy!",
                    shopItem,
                    new ConfirmPurchaseItemMenu(plugin, price, amount, material, player)));
        }
        return menuItems;
    }
}
