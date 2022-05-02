package dev.nerdtimmie.nftworlds.trialplugin.payloads;

import org.bukkit.inventory.ItemStack;

public class PlayerBuyItemTransaction {
    public ItemStack items;
    public Integer times;

    public PlayerBuyItemTransaction(ItemStack items, Integer times) {
        this.items = items;
        this.times = times;
    }
}
