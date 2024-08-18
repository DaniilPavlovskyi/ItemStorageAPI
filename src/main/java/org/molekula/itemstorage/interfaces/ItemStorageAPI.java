package org.molekula.itemstorage.interfaces;

import org.bukkit.inventory.ItemStack;

public interface ItemStorageAPI {
    void saveItem(String key, ItemStack item);
    ItemStack loadItem(String key);
}
