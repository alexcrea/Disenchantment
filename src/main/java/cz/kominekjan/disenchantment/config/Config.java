package cz.kominekjan.disenchantment.config;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static cz.kominekjan.disenchantment.Disenchantment.config;
import static cz.kominekjan.disenchantment.Disenchantment.plugin;

public class Config {
    public static Boolean getPluginEnabled() {
        return config.getBoolean(ConfigKeys.ENABLED.getKey());
    }

    public static Boolean getEnableLogging() {
        return config.getBoolean(ConfigKeys.ENABLE_LOGGING.getKey());
    }

    public static LoggingLevels getLoggingLevel() {
        String level = config.getString(ConfigKeys.LOGGING_LEVEL.getKey());
        return LoggingLevels.valueOf(level);
    }

    public static List<World> getDisabledWorlds() {
        return new ArrayList<>(config.getStringList(ConfigKeys.DISABLED_WORLDS.getKey()).stream().map(Bukkit::getWorld).toList());
    }

    public static List<Material> getDisabledMaterials() {
        return new ArrayList<>(config.getStringList(ConfigKeys.DISABLED_ITEMS.getKey()).stream().map(Material::getMaterial).toList());
    }

    public static Map<Enchantment, Boolean> getDisabledEnchantments() {
        List<String> list = config.getStringList(ConfigKeys.DISABLED_ENCHANTMENTS.getKey());
        return list.stream().map(s -> {
            String[] split = s.split(":");
            return Map.entry(
                    Objects.requireNonNull(Registry.ENCHANTMENT.stream().filter(e -> e.getKey().getKey().equals(split[0])).findFirst().orElse(null)),
                    Boolean.parseBoolean(split[1])
            );
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Boolean getDisableBookSplitting() {
        return config.getBoolean(ConfigKeys.DISABLE_BOOK_SPLITTING.getKey());
    }

    public static List<World> getDisabledBookSplittingWorlds() {
        return new ArrayList<>(config.getStringList(ConfigKeys.DISABLED_WORLDS.getKey()).stream().map(Bukkit::getWorld).toList());
    }

    public static Map<Enchantment, Boolean> getDisabledBookSplittingEnchantments() {
        List<String> list = config.getStringList(ConfigKeys.DISABLED_BOOK_SPLITTING_ENCHANTMENTS.getKey());
        return list.stream().map(s -> {
            String[] split = s.split(":");
            return Map.entry(
                    Objects.requireNonNull(Registry.ENCHANTMENT.stream().filter(e -> e.getKey().getKey().equals(split[0])).findFirst().orElse(null)),
                    Boolean.parseBoolean(split[1])
            );
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Boolean getEnableAnvilSound() {
        return config.getBoolean(ConfigKeys.ENABLE_ANVIL_SOUND.getKey());
    }

    public static Double getAnvilSoundVolume() {
        return config.getDouble(ConfigKeys.ANVIL_VOLUME.getKey());
    }

    public static Double getAnvilSoundPitch() {
        return config.getDouble(ConfigKeys.ANVIL_PITCH.getKey());
    }

    public static Boolean getEnableRepairReset() {
        return config.getBoolean(ConfigKeys.ENABLE_REPAIR_RESET.getKey());
    }

    public static Boolean getEnableRepairCost() {
        return config.getBoolean(ConfigKeys.ENABLE_REPAIR_COST.getKey());
    }

    public static Double getBaseRepairCost() {
        return config.getDouble(ConfigKeys.BASE_REPAIR_COST.getKey());
    }

    public static Double getRepairCostMultiplier() {
        return config.getDouble(ConfigKeys.COST_MULTIPLIER.getKey());
    }

    public static Boolean setPluginEnabled(Boolean enabled) {
        config.set(ConfigKeys.ENABLED.getKey(), enabled);
        plugin.saveConfig();

        return getPluginEnabled() == enabled;
    }

    public static Boolean setDisabledWorlds(List<World> worlds) {
        config.set(ConfigKeys.DISABLED_WORLDS.getKey(), worlds.stream().map(World::getName).toList());
        plugin.saveConfig();

        return getDisabledWorlds().equals(worlds);
    }

    public static Boolean setDisabledMaterials(List<Material> materials) {
        config.set(ConfigKeys.DISABLED_ITEMS.getKey(), materials.stream().map(Material::name).toList());
        plugin.saveConfig();

        return getDisabledMaterials().equals(materials);
    }

    public static Boolean setDisabledEnchantments(Map<Enchantment, Boolean> enchantments) {
        config.set(ConfigKeys.DISABLED_ENCHANTMENTS.getKey(), enchantments.entrySet().stream().map(e -> e.getKey().getKey().getKey() + ":" + e.getValue()).toList());
        plugin.saveConfig();

        return getDisabledEnchantments().equals(enchantments);
    }

    public static Boolean setEnableAnvilSound(Boolean enabled) {
        config.set(ConfigKeys.ENABLE_ANVIL_SOUND.getKey(), enabled);
        plugin.saveConfig();

        return getEnableAnvilSound() == enabled;
    }

    public static Boolean setAnvilSoundVolume(Double volume) {
        config.set(ConfigKeys.ANVIL_VOLUME.getKey(), volume);
        plugin.saveConfig();

        return config.getDouble(ConfigKeys.ANVIL_VOLUME.getKey()) == volume;
    }

    public static Boolean setAnvilSoundPitch(Double pitch) {
        config.set(ConfigKeys.ANVIL_PITCH.getKey(), pitch);
        plugin.saveConfig();

        return config.getDouble(ConfigKeys.ANVIL_PITCH.getKey()) == pitch;
    }

    public static Boolean setEnableRepairReset(Boolean enabled) {
        config.set(ConfigKeys.ENABLE_REPAIR_RESET.getKey(), enabled);
        plugin.saveConfig();

        return getEnableRepairReset() == enabled;
    }

    public static Boolean setEnableRepairCost(Boolean enabled) {
        config.set(ConfigKeys.ENABLE_REPAIR_COST.getKey(), enabled);
        plugin.saveConfig();

        return getEnableRepairCost() == enabled;
    }

    public static Boolean setBaseRepairCost(Double cost) {
        config.set(ConfigKeys.BASE_REPAIR_COST.getKey(), cost);
        plugin.saveConfig();

        return config.getInt(ConfigKeys.BASE_REPAIR_COST.getKey()) == cost;
    }

    public static Boolean setRepairCostMultiplier(Double multiplier) {
        config.set(ConfigKeys.COST_MULTIPLIER.getKey(), multiplier);
        plugin.saveConfig();

        return config.getDouble(ConfigKeys.COST_MULTIPLIER.getKey()) == multiplier;
    }

    private enum ConfigKeys {
        ENABLED("enabled"),
        ENABLE_LOGGING("enable-logging"),
        LOGGING_LEVEL("logging-level"),
        DISABLED_WORLDS("disabled-worlds"),
        DISABLED_ITEMS("disabled-materials"),
        DISABLED_ENCHANTMENTS("disabled-enchantments"),
        DISABLE_BOOK_SPLITTING("disable-book-splitting"),
        DISABLED_BOOK_SPLITTING_WORLDS("disabled-book-splitting-worlds"),
        DISABLED_BOOK_SPLITTING_ENCHANTMENTS("disabled-book-splitting-enchantments"),
        ENABLE_ANVIL_SOUND("enable-anvil-sound"),
        ANVIL_VOLUME("anvil-volume"),
        ANVIL_PITCH("anvil-pitch"),
        ENABLE_REPAIR_RESET("enable-repair-reset"),
        ENABLE_REPAIR_COST("enable-repair-cost"),
        BASE_REPAIR_COST("base"),
        COST_MULTIPLIER("multiply");

        private final String key;

        ConfigKeys(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    public enum LoggingLevels {
        INFO("INFO"),
        DEBUG("DEBUG");

        private final String level;

        LoggingLevels(String level) {
            this.level = level;
        }

        public String getLevel() {
            return level;
        }
    }
}
