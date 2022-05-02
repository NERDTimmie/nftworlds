package dev.nerdtimmie.nftworlds.trialplugin;

import com.nftworlds.wallet.api.WalletAPI;
import dev.nerdtimmie.nftworlds.trialplugin.commands.ShopCommand;
import dev.nerdtimmie.nftworlds.trialplugin.events.TransactionEvent;
import dev.nerdtimmie.nftworlds.trialplugin.gui.GuiManager;
import dev.nerdtimmie.nftworlds.trialplugin.gui.menus.ShopMenu;
import ninja.amp.ampmenus.menus.ItemMenu;
import org.bukkit.block.data.type.Wall;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;


public final class Trialplugin extends JavaPlugin {



    @Override
    public void onEnable() {
        initialiseManagers();
        registerCommands();
        registerGuis();
        registerEvent();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Initialise all the mangers as a spigot service. This is accessible by other plugins.
     */
    private void initialiseManagers() {
        ServicesManager servicesManager = getServer().getServicesManager();
        servicesManager.register(GuiManager.class, new GuiManager(), this, ServicePriority.Normal);
        servicesManager.register(WalletAPI.class, new WalletAPI(), this, ServicePriority.High);
    }

    private void registerCommands() {
        Objects.requireNonNull(getServer().getPluginCommand("shop")).setExecutor(new ShopCommand(this));
    }

    private void registerGuis() {
        GuiManager guiManager = getService(GuiManager.class);
        guiManager.addGui(1
                , new ShopMenu(this));
    }

    private void registerEvent(){
        getServer().getPluginManager().registerEvents(new TransactionEvent(), this);
    }

    /**
     * Get a registered service
     *
     * @param tClass The class of the service
     * @return The requested service. Returns null if no service is registered with that class.
     */
    public @Nullable <T> T getService(Class<T> tClass) {
        return getServer().getServicesManager().load(tClass);
    }
}
