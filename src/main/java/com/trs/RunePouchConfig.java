package com.trs;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup(RunePouchConfig.PLUGIN_GROUP_NAME)
public interface RunePouchConfig extends Config
{
	public static final String PLUGIN_GROUP_NAME = "rune-pouch-redesign";

	@ConfigSection(
		position = 0,
		name = "Settings",
		description = ""
	)
	String sectionSettings = "sectionSettings";
	
	@ConfigItem(
		position = 0,
		keyName = "hideRunePouchNames",
		name = "Hide Loadout Names",
		description = "",
		section = sectionSettings
	)
	default boolean hideRunePouchNames() {
			return true;
	}
	
	@ConfigItem(
		position = 1,
		keyName = "hideRunePouchLoadoutHeader",
		name = "Hide Load-outs Header Text",
		description = "",
		section = sectionSettings
	)
	default boolean hideRunePouchLoadoutHeader() {
			return true;
	}
}