package dev.nerdtimmie.nftworlds.trialplugin.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.KeybindComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
import java.util.ArrayList;

public class ShopItemBuilder {

    private Material material;
    private Integer amount;
    private Double price;

    public ShopItemBuilder() {

    }

    public ShopItemBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public ShopItemBuilder setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public ShopItemBuilder setPrice(Double price) {
        this.price = price;
        return this;
    }

    public ItemStack create() {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        ArrayList<Component> lore = new ArrayList<>();
        lore.add(0, Component.text("Price: " + price)
                .color(TextColor.color(255, 255, 255))
                .decoration(TextDecoration.ITALIC, false)
                .append(Component.text(" WRLD")
                        .color(TextColor.color(0, 255, 0))
                        .decoration(TextDecoration.ITALIC, false)));

        lore.add(1, Component.text("Amount: " + amount)
                .color(TextColor.color(255,255,255))
                .decoration(TextDecoration.ITALIC, false));

        lore.add(2, Component.text("Right click to sell"));
        lore.add(3, Component.text("Left click to buy"));

        itemMeta.lore(lore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
