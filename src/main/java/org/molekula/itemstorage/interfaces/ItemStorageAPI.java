package org.molekula.itemstorage.interfaces;

import org.bukkit.inventory.ItemStack;

public interface ItemStorageAPI {
    void saveItem(ItemStack item, String key);
    ItemStack loadItem(String key);
}
