package org.molekula.itemstorage.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.molekula.itemstorage.ItemStoragePlugin;

public class SaveItemCommand implements CommandExecutor {

    private final ItemStoragePlugin plugin;

    public SaveItemCommand(ItemStoragePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack itemInHand = player.getInventory().getItemInMainHand();

            if (!itemInHand.getType().isAir()) {
                if (args.length > 0) {
                    String itemName = args[0];
                    ItemStoragePlugin.getAPI().saveItem(itemInHand, itemName);
                    player.sendMessage("Item saved as '" + itemName + "'.");
                } else {
                    player.sendMessage("Please specify a name for the item.");
                }
            } else {
                player.sendMessage("You must hold an item in your main hand to save it.");
            }
        } else {
            sender.sendMessage("This command can only be used by players.");
        }
        return true;
    }
}
