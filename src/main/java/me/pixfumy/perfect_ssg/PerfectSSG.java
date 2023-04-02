package me.pixfumy.perfect_ssg;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PerfectSSG implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    private static final File configFile = FabricLoader.getInstance().getConfigDir().resolve("perfect_ssg.properties").toFile();
    public static int dragonInitialWaitTicks;
    public static int dragonBetweenChargesWaitTicks;
    public static float dragonChargeChance;

    @Override
    public void onInitialize() {
        LOGGER.info("PerfectSSG Mod Loaded.");
        loadOrCreateConfigFile();
    }

    private static void loadOrCreateConfigFile() {
        try {
            Properties properties = new Properties();
            if (configFile.exists()) {
                FileInputStream fileInputStream = new FileInputStream(configFile);
                properties.load(fileInputStream);
                LOGGER.info("Found perfect_ssg.properties file in .minecraft/config. Reading it in.");
            } else {
                LOGGER.info("No perfect_ssg.properties file found in .minecraft/config. Creating one with default values..");
            }
            dragonInitialWaitTicks = Math.max(0, Integer.parseInt(properties.getProperty("dragon_initial_wait_ticks", "300")));
            dragonBetweenChargesWaitTicks = Math.max(0, Integer.parseInt(properties.getProperty("dragon_between_charges_wait_ticks", "120")));
            dragonChargeChance = Math.max(0.0F, Math.min(1.0F, Float.valueOf(properties.getProperty("dragon_charge_chance", "0.5"))));
            FileWriter fileWriter = new FileWriter(configFile);
            fileWriter.write("# Amount of ticks dragon will wait for when the player enters end before it starts charging. 20 Ticks = 1 second. \n");
            fileWriter.write("# Set to a higher value if doing Snape tower. \n");
            fileWriter.write("dragon_initial_wait_ticks = " + dragonInitialWaitTicks + "\n\n");
            fileWriter.write("# Amount of ticks dragon will wait after taking damage before charging again. \n");
            fileWriter.write("dragon_between_charges_wait_ticks = " + dragonBetweenChargesWaitTicks + "\n\n");
            fileWriter.write("# Chance for dragon to pick the player when it rerolls a target. Must be a decimal number between 0.0 and 1.0.\n");
            fileWriter.write("# The vanilla amount is 0.25.\n");
            fileWriter.write("dragon_charge_chance = " + dragonChargeChance + "\n\n");
            fileWriter.close();
        } catch (IOException e) {
            LOGGER.error("Failed to read perfect_ssg.properties", e);
        }
    }
}