package dev.nerdtimmie.nftworlds.trialplugin.storage;

import dev.nerdtimmie.nftworlds.trialplugin.Trialplugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Config {

    private final String fileName;
    private final File configFile;
    private FileConfiguration fileConfiguration;

    private final Trialplugin plugin;

    public Config(String fileName, Trialplugin plugin) {
        this.plugin = plugin;
        this.fileName = fileName;
        configFile = new File(plugin.getDataFolder(), fileName + (fileName.endsWith(".yml") ? "" : ".yml"));
    }

    /**
     * Reload the config
     */
    public void reloadConfig() {
        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);

        InputStream defConfigStream = plugin.getResource(fileName);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream));
            fileConfiguration.setDefaults(defConfig);
        }
    }

    /**
     * Get the configuration in the config
     *
     * @return the FileConfiguration of the config
     */
    public FileConfiguration getConfig() {
        if (fileConfiguration == null)
            reloadConfig();
        return fileConfiguration;
    }

    /**
     * Save the config
     */
    public void saveConfig() {
        if (fileConfiguration != null && configFile != null) {
            try {
                getConfig().save(configFile);
            } catch (IOException ignored) {
            }
        }
    }

    /**
     * Save the default config
     */
    public void saveDefaultConfig() {
        saveDefaultConfig(false);
    }

    /**
     * Save the default config
     *
     * @param override if the existing config should be overriden
     */
    public void saveDefaultConfig(boolean override) {
        if (!configFile.exists())
            plugin.saveResource(fileName, override);
    }
}
