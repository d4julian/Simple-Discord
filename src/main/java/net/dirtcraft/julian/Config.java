package net.dirtcraft.julian;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.File;
import java.io.IOException;

public class Config {

    public static void setConfNode(CommentedConfigurationNode confNode) {
        Config.confNode = confNode;
    }

    public static File config;
    public static ConfigurationLoader<CommentedConfigurationNode> loader;
    public static CommentedConfigurationNode confNode;


    public static void setup(File myFile, ConfigurationLoader<CommentedConfigurationNode> load) {
        config = myFile;
        loader = load;
    }
    public static void load() {

        try {
            if (!config.exists()) {
                config.createNewFile();
                confNode = loader.load();
                addValues();
                loader.save(confNode);
            }
            confNode = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void addValues() {

        confNode.getNode("SimpleDiscord", "message")
                .setValue("&6&lJoin us on Discord")
                .setComment("Customize your message here");
        confNode.getNode("SimpleDiscord", "discordlink")
                .setValue("https://discord.gg/XXXXXX")
                .setComment("Insert your Discord link here");
    }

    public static CommentedConfigurationNode getConfNode(Object... node) {
        return confNode.getNode(node);
    }

}