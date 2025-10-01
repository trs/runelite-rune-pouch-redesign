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

	@ConfigSection(
		position = 1,
		name = "Loadout Names",
		description = ""
	)
	String sectionLoadoutNames = "sectionLoadoutNames";
	
	@ConfigItem(
		position = 0,
		keyName = "enableCustomNames",
		name = "Enable Custom Loadout Names",
		description = "",
		section = sectionLoadoutNames
	)
	default boolean enableCustomNames() {
			return true;
	}
	
	@ConfigItem(
		position = 1,
		keyName = "loadoutName1",
		name = "Loadout Name 1",
		description = "",
		section = sectionLoadoutNames
	)
	default String loadoutName1() {
			return "Loadout 1";
	}
	
	@ConfigItem(
		position = 2,
		keyName = "loadoutName2",
		name = "Loadout Name 2",
		description = "",
		section = sectionLoadoutNames
	)
	default String loadoutName2() {
			return "Loadout 2";
	}

	@ConfigItem(
		position = 3,
		keyName = "loadoutName3",
		name = "Loadout Name 3",
		description = "",
		section = sectionLoadoutNames
	)
	default String loadoutName3() {
			return "Loadout 3";
	}

	@ConfigItem(
		position = 4,
		keyName = "loadoutName4",
		name = "Loadout Name 4",
		description = "",
		section = sectionLoadoutNames
	)
	default String loadoutName4() {
			return "Loadout 4";
	}

	@ConfigItem(
		position = 5,
		keyName = "loadoutName5",
		name = "Loadout Name 5",
		description = "",
		section = sectionLoadoutNames
	)
	default String loadoutName5() {
			return "Loadout 5";
	}

	@ConfigItem(
		position = 6,
		keyName = "loadoutName6",
		name = "Loadout Name 6",
		description = "",
		section = sectionLoadoutNames
	)
	default String loadoutName6() {
			return "Loadout 6";
	}

	@ConfigItem(
		position = 7,
		keyName = "loadoutName7",
		name = "Loadout Name 7",
		description = "",
		section = sectionLoadoutNames
	)
	default String loadoutName7() {
			return "Loadout 7";
	}

	@ConfigItem(
		position = 8,
		keyName = "loadoutName8",
		name = "Loadout Name 8",
		description = "",
		section = sectionLoadoutNames
	)
	default String loadoutName8() {
			return "Loadout 8";
	}

	@ConfigItem(
		position = 9,
		keyName = "loadoutName9",
		name = "Loadout Name 9",
		description = "",
		section = sectionLoadoutNames
	)
	default String loadoutName9() {
			return "Loadout 9";
	}

	@ConfigItem(
		position = 10,
		keyName = "loadoutName10",
		name = "Loadout Name 10",
		description = "",
		section = sectionLoadoutNames
	)
	default String loadoutName10() {
			return "Loadout 10";
	}
}