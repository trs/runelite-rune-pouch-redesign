package com.trs;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.runelite.api.Client;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.WidgetSizeMode;
import net.runelite.api.widgets.WidgetPositionMode;

@Singleton
public class RunePouchUI {
  private final Client client;
  private final RunePouchConfig runePouchConfig;

  @Inject
  public RunePouchUI(Client client, RunePouchConfig runePouchConfig) {
    this.client = client;
    this.runePouchConfig = runePouchConfig;
  }

  public void redrawRunePouch() {
    var headerHeight = 0;
    var runepouchText = client.getWidget(InterfaceID.Bankside.RUNEPOUCH_CONTENTS_TEXT1);
    if (runepouchText != null) {
      if (runePouchConfig.hideRunePouchLoadoutHeader()) {
        runepouchText.setHidden(true);
        runepouchText.revalidate();
      } else {
        runepouchText.setHidden(false);
        runepouchText.revalidate();
        headerHeight = runepouchText.getHeight();
      }
    }

    var runepouchTopHeight = 46;
    var runepouchTop = client.getWidget(InterfaceID.Bankside.RUNEPOUCH_TOP);
    if (runepouchTop != null) {
      runepouchTopHeight = runepouchTop.getHeight();
    }

    var runepouchLoadoutContainer = client.getWidget(InterfaceID.Bankside.RUNEPOUCH_LOADOUT_CONTAINER);
    if (runepouchLoadoutContainer != null) {
      runepouchLoadoutContainer.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
      runepouchLoadoutContainer.setOriginalY(48 + headerHeight);

      runepouchLoadoutContainer.setHeightMode(WidgetSizeMode.MINUS);
      runepouchLoadoutContainer.setOriginalHeight(48 - headerHeight);

      runepouchLoadoutContainer.setScrollHeight(340);
      runepouchLoadoutContainer.revalidate();
    }
    
    var runepouchScrollbar = client.getWidget(InterfaceID.Bankside.RUNEPOUCH_LOADOUT_SCROLLBAR);
    if (runepouchScrollbar != null) {
      runepouchScrollbar.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
      runepouchScrollbar.setOriginalY(runepouchTopHeight + headerHeight);

      runepouchScrollbar.setHeightMode(WidgetSizeMode.MINUS);
      runepouchScrollbar.setOriginalHeight(runepouchTopHeight + headerHeight);
      runepouchScrollbar.revalidate();
    }

    var runepouchScrollbarBG = runepouchScrollbar.getChild(0);
    if (runepouchScrollbarBG != null) {
      runepouchScrollbarBG.setHeightMode(WidgetSizeMode.MINUS);
      runepouchScrollbarBG.setOriginalHeight(0);
      runepouchScrollbarBG.revalidate();
    }
    
    var runepouchScrollbarDown = runepouchScrollbar.getChild(5);
    if (runepouchScrollbarDown != null) {
      runepouchScrollbarDown.setYPositionMode(WidgetPositionMode.ABSOLUTE_BOTTOM);
      runepouchScrollbarDown.setOriginalY(0);
      runepouchScrollbarDown.revalidate();
    }

    redrawLoadout(InterfaceID.Bankside.RUNEPOUCH_NAME_A, InterfaceID.Bankside.RUNEPOUCH_LOAD_A, InterfaceID.Bankside.RUNEPOUCH_LOADOUT_A, 0);
    redrawLoadout(InterfaceID.Bankside.RUNEPOUCH_NAME_B, InterfaceID.Bankside.RUNEPOUCH_LOAD_B, InterfaceID.Bankside.RUNEPOUCH_LOADOUT_B, 1);
    redrawLoadout(InterfaceID.Bankside.RUNEPOUCH_NAME_C, InterfaceID.Bankside.RUNEPOUCH_LOAD_C, InterfaceID.Bankside.RUNEPOUCH_LOADOUT_C, 2);
    redrawLoadout(InterfaceID.Bankside.RUNEPOUCH_NAME_D, InterfaceID.Bankside.RUNEPOUCH_LOAD_D, InterfaceID.Bankside.RUNEPOUCH_LOADOUT_D, 3);
    redrawLoadout(InterfaceID.Bankside.RUNEPOUCH_NAME_E, InterfaceID.Bankside.RUNEPOUCH_LOAD_E, InterfaceID.Bankside.RUNEPOUCH_LOADOUT_E, 4);
    redrawLoadout(InterfaceID.Bankside.RUNEPOUCH_NAME_F, InterfaceID.Bankside.RUNEPOUCH_LOAD_F, InterfaceID.Bankside.RUNEPOUCH_LOADOUT_F, 5);
    redrawLoadout(InterfaceID.Bankside.RUNEPOUCH_NAME_G, InterfaceID.Bankside.RUNEPOUCH_LOAD_G, InterfaceID.Bankside.RUNEPOUCH_LOADOUT_G, 6);
    redrawLoadout(InterfaceID.Bankside.RUNEPOUCH_NAME_H, InterfaceID.Bankside.RUNEPOUCH_LOAD_H, InterfaceID.Bankside.RUNEPOUCH_LOADOUT_H, 7);
    redrawLoadout(InterfaceID.Bankside.RUNEPOUCH_NAME_I, InterfaceID.Bankside.RUNEPOUCH_LOAD_I, InterfaceID.Bankside.RUNEPOUCH_LOADOUT_I, 8);
    redrawLoadout(InterfaceID.Bankside.RUNEPOUCH_NAME_J, InterfaceID.Bankside.RUNEPOUCH_LOAD_J, InterfaceID.Bankside.RUNEPOUCH_LOADOUT_J, 9);
  }

  private void redrawLoadout(int nameWidgetID, int loadWidgetID, int loadoutWidgetID, int index) {
    var padding = 2;
    var nameHeight = 0;
    var loadoutHeight = 32;
    
    var runepouchName = client.getWidget(nameWidgetID);
    if (runepouchName != null) {

      runepouchName.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
      runepouchName.setOriginalY(0);

        nameHeight = runepouchName.getHeight();
		  if (runePouchConfig.hideRunePouchNames()) {
        runepouchName.setHidden(true);
        runepouchName.revalidate();

        nameHeight = 0;
      } else {
        runepouchName.setHidden(false);
        runepouchName.revalidate();

        nameHeight = runepouchName.getHeight() + padding;
        loadoutHeight = 56;
      }
    }

    var runepouchLoad = client.getWidget(loadWidgetID);
    if (runepouchLoad != null) {
      runepouchLoad.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
      runepouchLoad.setOriginalY(nameHeight + padding);
      runepouchLoad.revalidate();
    }

    var runepouchLoadout = client.getWidget(loadoutWidgetID);
    if (runepouchLoadout != null) {
      runepouchLoadout.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
      runepouchLoadout.setOriginalY(loadoutHeight * index + (padding * index));
      runepouchLoadout.setHeightMode(WidgetSizeMode.MINUS);
      runepouchLoadout.setOriginalHeight(148 - nameHeight - padding);
      runepouchLoadout.revalidate();

      for (int i = 0; i < runepouchLoadout.getChildren().length; i++) {
        var child = runepouchLoadout.getChild(i);
        if (child != null) {
          child.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
          child.setOriginalY(nameHeight + padding);
          child.revalidate();
        }
      }
    }
  }
}
