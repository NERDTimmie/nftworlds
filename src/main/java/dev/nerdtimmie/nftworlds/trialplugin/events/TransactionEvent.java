package dev.nerdtimmie.nftworlds.trialplugin.events;

import com.nftworlds.wallet.event.PlayerTransactEvent;
import dev.nerdtimmie.nftworlds.trialplugin.payloads.PlayerBuyItemTransaction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class TransactionEvent implements Listener {

    @EventHandler
    public void onPlayerTransact(PlayerTransactEvent<?> e) {
        if(e.getPayload() instanceof PlayerBuyItemTransaction){
            PlayerBuyItemTransaction payload = (PlayerBuyItemTransaction) e.getPayload();

            ItemStack items = payload.items;
            Integer times = payload.times;
            PlayerInventory inventory = e.getPlayer().getInventory();
            for (int i = 0; i < times; i++) {
                inventory.addItem(items);
            }
        }
    }
}
