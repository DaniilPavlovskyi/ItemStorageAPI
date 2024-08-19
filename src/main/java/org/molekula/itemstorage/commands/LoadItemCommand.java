package org.molekula.itemstorage.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.molekula.criminalactivity.CriminalActivity;
import org.molekula.itemstorage.ItemStoragePlugin;

public class LoadItemCommand implements CommandExecutor {

    private final ItemStoragePlugin plugin;

    public LoadItemCommand(ItemStoragePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                String itemName = args[0];
                ItemStack item = ItemStoragePlugin.getAPI().loadItem(itemName);

                if (item != null) {
                    player.getInventory().addItem(item);
                    player.sendMessage("You have received the item: '" + itemName + "'.");
                } else {
                    player.sendMessage("No item found with the name '" + itemName + "'.");
                }
            } else {
                player.sendMessage("Please specify the name of the item you want to retrieve.");
            }
        } else {
            sender.sendMessage("This command can only be used by players.");
        }
        return true;
    }
}
