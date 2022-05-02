package dev.nerdtimmie.nftworlds.trialplugin.commands;

import dev.nerdtimmie.nftworlds.trialplugin.Trialplugin;
import dev.nerdtimmie.nftworlds.trialplugin.gui.GuiManager;
import ninja.amp.ampmenus.menus.ItemMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * The command to open a shop
 */
public class ShopCommand implements CommandExecutor {

    private final Trialplugin plugin;

    public ShopCommand(Trialplugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof ConsoleCommandSender) {
            return false;
        }
        GuiManager service = plugin.getService(GuiManager.class);
        assert service != null;
        Objects.requireNonNull(service.getGui(1)).open((Player) sender);

        return true;
    }


}
