package com.trs;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.runelite.api.Client;
import net.runelite.api.FontID;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.WidgetSizeMode;
import net.runelite.api.widgets.WidgetType;
import net.runelite.api.widgets.WidgetPositionMode;

@Singleton
public class RunePouchUI {
  private final Client client;
  private final RunePouchConfig runePouchConfig;

  private final int LOADOUT_PADDING = 4;
  private final int LOADOUT_COUNT = 10;

  private final int getLoadoutHeight() {
    if ( runePouchConfig.hideRunePouchNames()) return 32;
    else if (runePouchConfig.enableCustomNames()) return 48;
    else return 56;
  }

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
      runepouchLoadoutContainer.setOriginalHeight(48 + headerHeight);

      runepouchLoadoutContainer.setScrollHeight(getLoadoutHeight() * LOADOUT_COUNT + (LOADOUT_PADDING * LOADOUT_COUNT));
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
    var nameHeight = 0;
    
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

        if (runePouchConfig.enableCustomNames()) {
          runepouchName.setHidden(true);

          nameHeight = 8 + LOADOUT_PADDING;
        } else {
          nameHeight = runepouchName.getHeight() + LOADOUT_PADDING;
        }
        
        runepouchName.revalidate();
      }
    }

    var runepouchLoad = client.getWidget(loadWidgetID);
    if (runepouchLoad != null) {
      runepouchLoad.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
      runepouchLoad.setOriginalY(nameHeight + LOADOUT_PADDING);
      runepouchLoad.revalidate();
    }

    var loadoutStartingHeight = runePouchConfig.hideRunePouchLoadoutHeader() ? 148 : 132;

    var runepouchLoadout = client.getWidget(loadoutWidgetID);
    if (runepouchLoadout != null) {
      runepouchLoadout.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
      runepouchLoadout.setOriginalY(getLoadoutHeight() * index + (LOADOUT_PADDING * index));
      runepouchLoadout.setHeightMode(WidgetSizeMode.MINUS);
      runepouchLoadout.setOriginalHeight(loadoutStartingHeight - nameHeight - LOADOUT_PADDING);
      runepouchLoadout.revalidate();

      for (int i = 0; i < runepouchLoadout.getChildren().length; i++) {
        var child = runepouchLoadout.getChild(i);
        if (child != null) {
          child.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
          child.setOriginalY(nameHeight + LOADOUT_PADDING);
          child.revalidate();
        }
      }

      var nameCustomTextBgWidget = runepouchLoadout.getChild(5);
      if (runePouchConfig.enableCustomNames() && !runePouchConfig.hideRunePouchNames()) {
        if (nameCustomTextBgWidget == null) {
          nameCustomTextBgWidget = runepouchLoadout.createChild(-1, WidgetType.RECTANGLE);
        }
        nameCustomTextBgWidget.setType(WidgetType.RECTANGLE);

        nameCustomTextBgWidget.setHidden(false);
        nameCustomTextBgWidget.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
        nameCustomTextBgWidget.setXPositionMode(WidgetPositionMode.ABSOLUTE_LEFT);

        nameCustomTextBgWidget.setOriginalX(0);
        nameCustomTextBgWidget.setOriginalY(0);

        nameCustomTextBgWidget.setHeightMode(WidgetSizeMode.MINUS);
        nameCustomTextBgWidget.setOriginalHeight(runePouchConfig.hideRunePouchLoadoutHeader() ? 34 : 33);
        nameCustomTextBgWidget.setWidthMode(WidgetSizeMode.MINUS);
        nameCustomTextBgWidget.setOriginalWidth(0);
        nameCustomTextBgWidget.setTextColor(0x473F35);
        nameCustomTextBgWidget.setFilled(true);

        nameCustomTextBgWidget.revalidate();
      } else {
        if (nameCustomTextBgWidget != null) {
          nameCustomTextBgWidget.setHidden(true);
          nameCustomTextBgWidget.revalidate();
        }
      }

      var nameCustomTextWidget = runepouchLoadout.getChild(6);
      if (runePouchConfig.enableCustomNames() && !runePouchConfig.hideRunePouchNames()) {
        if (nameCustomTextWidget == null) {
          nameCustomTextWidget = runepouchLoadout.createChild(-1, WidgetType.TEXT);
        }
        nameCustomTextWidget.setType(WidgetType.TEXT);

        nameCustomTextWidget.setHidden(false);
        nameCustomTextWidget.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
        nameCustomTextWidget.setXPositionMode(WidgetPositionMode.ABSOLUTE_LEFT);

        nameCustomTextWidget.setOriginalX(2);
        nameCustomTextWidget.setOriginalY(2);

        nameCustomTextWidget.setHeightMode(WidgetSizeMode.MINUS);
        nameCustomTextWidget.setOriginalHeight(40);
        nameCustomTextWidget.setWidthMode(WidgetSizeMode.MINUS);
        nameCustomTextWidget.setOriginalWidth(0);

        nameCustomTextWidget.setFontId(FontID.BOLD_12);
        nameCustomTextWidget.setFontId(FontID.PLAIN_11);
        nameCustomTextWidget.setText(getLoadoutNameByIndex(index));
        nameCustomTextWidget.setTextColor(0xFF981F);
        nameCustomTextWidget.setTextShadowed(true);
        nameCustomTextWidget.revalidate();
      } else {
        if (nameCustomTextWidget != null) {
          nameCustomTextWidget.setHidden(true);
          nameCustomTextWidget.revalidate();
        }
      }
    }
  }

	private String getLoadoutNameByIndex(int index) {
		switch (index) {
			case 0:
				return runePouchConfig.loadoutName1();
			case 1:
				return runePouchConfig.loadoutName2();
			case 2:
				return runePouchConfig.loadoutName3();
			case 3:
				return runePouchConfig.loadoutName4();
			case 4:
				return runePouchConfig.loadoutName5();
			case 5:
				return runePouchConfig.loadoutName6();
			case 6:
				return runePouchConfig.loadoutName7();
			case 7:
				return runePouchConfig.loadoutName8();
			case 8:
				return runePouchConfig.loadoutName9();
			case 9:
				return runePouchConfig.loadoutName10();
			default:
				return "-";
		}
	}
}
