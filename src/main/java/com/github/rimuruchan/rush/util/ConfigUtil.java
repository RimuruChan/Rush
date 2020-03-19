package com.github.rimuruchan.rush.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.github.rimuruchan.rush.Rush;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUtil {

    public static Configuration loadConfig(File file) {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                saveDefaultConfig(file);
            }
            return YamlConfiguration.loadConfiguration(new InputStreamReader(new FileInputStream(file), "UTF8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void saveDefaultConfig(File file) {

        try {
            InputStreamReader localConfig = new InputStreamReader(
                    Rush.class.getResourceAsStream("/" + file.getName()));
            FileWriter fw;
            fw = new FileWriter(file);
            while (localConfig.ready()) {
                fw.write(localConfig.read());
            }
            localConfig.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}