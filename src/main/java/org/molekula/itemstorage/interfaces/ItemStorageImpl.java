package org.molekula.itemstorage.interfaces;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.molekula.itemstorage.utils.Utils;

import java.io.File;
import java.io.IOException;

public class ItemStorageImpl implements ItemStorageAPI {

    private final File itemsFile;
    private final FileConfiguration itemsConfig;

    public ItemStorageImpl(File dataFolder) {
        itemsFile = new File(dataFolder, "items.yml");
        if (!itemsFile.exists()) {
            try {
                itemsFile.getParentFile().mkdirs();
                itemsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        itemsConfig = YamlConfiguration.loadConfiguration(itemsFile);
    }

    public void saveItem(ItemStack item, String name) {
        String serializedItem = Utils.itemStackToString(item);
        itemsConfig.set("saved-items." + name, serializedItem);
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
            e.printStackTrace();
        }
    }
}
