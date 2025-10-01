package com.trs;

import com.google.inject.Inject;
import com.google.inject.Provides;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.Client;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.eventbus.EventBus;
import net.runelite.api.events.ClientTick;
import net.runelite.api.widgets.Widget;

@Slf4j
@PluginDescriptor(name = "Rune Pouch Redesign")
public class RunePouchPlugin extends Plugin {
	
	@Inject private Client client;
	@Inject private EventBus eventBus;
	@Inject private RunePouchUI runePouchUI;

	@Subscribe
	public void onClientTick(ClientTick event)
	{
		Widget runePouch = client.getWidget(InterfaceID.Bankside.RUNEPOUCH_CONTAINER);
		boolean isRunePouchOpen = runePouch != null && !runePouch.isHidden();
		
		if (!isRunePouchOpen) return;

		runePouchUI.redrawRunePouch();
	}

	@Override
	protected void startUp() throws Exception
	{
		eventBus.register(this);
	}

	@Override
	protected void shutDown() throws Exception
	{
		// TODO: set runepouch back to normal
		eventBus.unregister(this);
	}

	@Provides
	RunePouchConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RunePouchConfig.class);
	}
}