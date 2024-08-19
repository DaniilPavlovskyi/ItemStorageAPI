package org.molekula.itemstorage;

import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.molekula.itemstorage.commands.LoadItemCommand;
import org.molekula.itemstorage.commands.SaveItemCommand;
import org.molekula.itemstorage.interfaces.ItemStorageAPI;
import org.molekula.itemstorage.interfaces.ItemStorageImpl;

public class ItemStoragePlugin extends JavaPlugin {

    private static ItemStorageAPI itemStorageAPI;

    @Override
    public void onEnable() {
        itemStorageAPI = new ItemStorageImpl(getDataFolder());

        getServer().getServicesManager().register(ItemStorageAPI.class, itemStorageAPI, this, ServicePriority.Normal);

        getLogger().info("ItemStoragePlugin enabled!");

        getCommand("saveitem").setExecutor(new SaveItemCommand(this));
        getCommand("loaditem").setExecutor(new LoadItemCommand(this));
    }

    public static ItemStorageAPI getAPI() {
        if (itemStorageAPI == null) {
            throw new IllegalStateException("ItemStorageAPI is not available.");
        }
        return itemStorageAPI;
    }
}