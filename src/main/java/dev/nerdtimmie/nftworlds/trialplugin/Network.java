package dev.nerdtimmie.nftworlds.trialplugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum Network {
    POLYGON(false),
    ETHEREUM(true);

    public boolean isEthereum;

    Network(boolean isEthereum) {
        this.isEthereum = isEthereum;
    }

    public ItemStack getIcon() {
        ItemStack itemStack;
        if (isEthereum) {
            itemStack = new ItemStack(Material.NETHER_STAR);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.displayName(Component.text("Ethereum")
                    .decoration(TextDecoration.ITALIC, false)
                    .color(TextColor.color(255, 255, 255)));
            itemStack.setItemMeta(itemMeta);

        } else {
            itemStack = new ItemStack(Material.DIAMOND);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.displayName(Component.text("Polygon")
                    .decoration(TextDecoration.ITALIC, false)
                    .color(TextColor.color(255, 255, 255)));
            itemStack.setItemMeta(itemMeta);

        }
        return itemStack;
    }
}
