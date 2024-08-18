package org.molekula.itemstorage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.ItemStack;
import org.molekula.itemstorage.interfaces.ItemStorageAPI;
import org.molekula.itemstorage.utils.Utils;

import java.io.File;
import java.io.IOException;

public class ItemStorage extends JavaPlugin implements ItemStorageAPI {

    private File itemsFile;
    private FileConfiguration itemsConfig;

    @Override
    public void onEnable() {
        getLogger().info("ItemStorageAPI started!");

        itemsFile = new File(getDataFolder(), "items.yml");
        if (!itemsFile.exists()) {
            itemsFile.getParentFile().mkdirs();
            saveResource("items.yml", false);
        }
        itemsConfig = YamlConfiguration.loadConfiguration(itemsFile);

        getServer().getServicesManager().register(ItemStorageAPI.class, this, this, ServicePriority.Normal);
    }

    @Override
    public void saveItem(String key, ItemStack item) {
        String serializedItem = Utils.itemStackToString(item);
        itemsConfig.set("items." + key, serializedItem);
        saveItemsConfig();
    }

    @Override
    public ItemStack loadItem(String key) {
        String serializedItem = itemsConfig.getString("items." + key);
        return Utils.stringToItemStack(serializedItem);
    }

    private void saveItemsConfig() {
        try {
            itemsConfig.save(itemsFile);
        } catch (IOException e) {
            getLogger().info(e.getMessage());
        }
    }
}
