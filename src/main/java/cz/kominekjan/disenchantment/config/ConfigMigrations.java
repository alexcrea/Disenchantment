package cz.kominekjan.disenchantment.config;

import cz.kominekjan.disenchantment.config.migrations.Migration1Base;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

public class ConfigMigrations {
    private static final Map<Integer, IConfigMigration> migrations = Map.of(
            1, new Migration1Base()
    );

    public static FileConfiguration apply(Plugin plugin, FileConfiguration oldConfig, FileConfiguration newConfig) {
        int oldVersion = oldConfig.getInt("migration", 0);
        int newVersion = newConfig.getInt("migration", 0);

        FileConfiguration updatedConfig = oldConfig;

        for (int i = oldVersion + 1; i <= newVersion; i++) {
            FileConfiguration configTemplate = YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(plugin.getResource("migrations/" + i + ".yml")), StandardCharsets.UTF_8));

            if (migrations.containsKey(i)) {
                updatedConfig = migrations.get(i).migrate(updatedConfig, configTemplate);
            }
        }

        return updatedConfig;
    }
}