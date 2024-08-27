package cz.kominekjan.disenchantment.commands.impl;

import cz.kominekjan.disenchantment.commands.Command;
import cz.kominekjan.disenchantment.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import static cz.kominekjan.disenchantment.utils.TextUtils.*;

public class ShatterRepair {
    public static final Command command = new Command(
            "shatter_repair",
            new String[]{"disenchantment.all", "disenchantment.command.shatter_repair"},
            "You don't have permission to use this command.",
            new String[]{"reset", "cost", "base", "multiply"},
            false,
            ShatterRepair::execute
    );

    public static void execute(CommandSender s, String[] args) {
        if (args.length == 1) {
            s.sendMessage(textWithPrefix("Repair cost configuration"));
            s.sendMessage("");

            String builder = "";

            builder += ChatColor.GRAY + "Repair reset is ";
            builder += Config.Shatterment.Anvil.Repair.isResetEnabled() ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled";

            builder += ChatColor.GRAY + "Repair cost is ";
            builder += Config.Shatterment.Anvil.Repair.isCostEnabled() ? ChatColor.GREEN + "Enabled" : ChatColor.RED + "Disabled";

            s.sendMessage(builder);
            s.sendMessage(ChatColor.GRAY + "Base value: " + Config.Shatterment.Anvil.Repair.getBaseCost());
            s.sendMessage(ChatColor.GRAY + "Multiply value: " + Config.Shatterment.Anvil.Repair.getCostMultiplier());
            return;
        }

        switch (args[1].toLowerCase()) {
            case "reset": {
                if (args.length == 2) {
                    s.sendMessage(textWithPrefixError("You must specify a value"));
                    break;
                }

                if (args[2].equalsIgnoreCase("enable")) {
                    Config.Shatterment.Anvil.Repair.setResetEnabled(true);
                    s.sendMessage(textWithPrefixSuccess("Repair cost reset enabled"));
                } else if (args[2].equalsIgnoreCase("disable")) {
                    Config.Shatterment.Anvil.Repair.setResetEnabled(false);
                    s.sendMessage(textWithPrefixSuccess("Repair cost reset disabled"));
                } else {
                    s.sendMessage(textWithPrefixError("You must specify 'enable' or 'disable'"));
                }

                break;
            }
            case "cost": {
                if (args.length == 2) {
                    s.sendMessage(textWithPrefixError("You must specify a value"));
                    break;
                }

                if (args[2].equalsIgnoreCase("enable")) {
                    Config.Shatterment.Anvil.Repair.setCostEnabled(true);
                    s.sendMessage(textWithPrefixSuccess("Repair cost reset enabled"));
                } else if (args[2].equalsIgnoreCase("disable")) {
                    Config.Shatterment.Anvil.Repair.setCostEnabled(false);
                    s.sendMessage(textWithPrefixSuccess("Repair cost reset disabled"));
                } else {
                    s.sendMessage(textWithPrefixError("You must specify 'enable' or 'disable'"));
                }

                break;
            }
            case "base": {
                if (args.length == 2) {
                    s.sendMessage(textWithPrefixError("You must specify a value"));
                    break;
                }

                try {
                    Config.Shatterment.Anvil.Repair.setBaseCost(Double.parseDouble(args[2]));
                    s.sendMessage(textWithPrefixSuccess("Base value set to " + args[2]));
                } catch (NumberFormatException e) {
                    s.sendMessage(textWithPrefixError("You must specify a valid number"));
                }
            }
            case "multiply": {
                if (args.length == 2) {
                    s.sendMessage(textWithPrefixError("You must specify a value"));
                    break;
                }

                try {
                    Config.Shatterment.Anvil.Repair.setCostMultiplier(Double.parseDouble(args[2]));
                    s.sendMessage(textWithPrefixSuccess("Multiply value set to " + args[2]));
                } catch (NumberFormatException e) {
                    s.sendMessage(textWithPrefixError("You must specify a valid number"));
                }
            }
            default: {
                s.sendMessage(textWithPrefixError("Unknown argument"));
            }
        }
    }
}