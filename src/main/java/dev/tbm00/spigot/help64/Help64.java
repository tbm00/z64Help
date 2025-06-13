package dev.tbm00.spigot.help64;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import dev.tbm00.spigot.help64.command.HeadCmd;
import dev.tbm00.spigot.help64.command.HelpCmd;

public class Help64 extends JavaPlugin {
    private ConfigHandler configHandler;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        final PluginDescriptionFile pdf = this.getDescription();

		log(ChatColor.LIGHT_PURPLE,
            ChatColor.DARK_PURPLE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-",
            pdf.getName() + " v" + pdf.getVersion() + " created by tbm00",
            ChatColor.DARK_PURPLE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-"
		);

        if (getConfig().contains("enabled") && getConfig().getBoolean("enabled")) {
            configHandler = new ConfigHandler(this);

            if (configHandler.isFeatureEnabled()) {
                
                // Register Commands
                getCommand("zzz").setExecutor(new HelpCmd(this));
                getCommand("sellhead").setExecutor(new HeadCmd());
            }
                
        }
    }

    public ConfigHandler getConfigHandler() {
        return configHandler;
    }

    /**
     * Disables the plugin.
     */
    private void disablePlugin() {
        getServer().getPluginManager().disablePlugin(this);
    }

    /**
     * Called when the plugin is disabled.
     */
    @Override
    public void onDisable() {
        log(ChatColor.RED, "Help64 disabled..!");
    }

    /**
     * Logs one or more messages to the server console with the prefix & specified chat color.
     *
     * @param chatColor the chat color to use for the log messages
     * @param strings one or more message strings to log
     */
    public void log(ChatColor chatColor, String... strings) {
		for (String s : strings)
            getServer().getConsoleSender().sendMessage("[Help64] " + chatColor + s);
	}

}