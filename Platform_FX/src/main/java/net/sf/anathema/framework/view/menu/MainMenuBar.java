package net.sf.anathema.framework.view.menu;

import net.sf.anathema.framework.view.MenuBar;

public class MainMenuBar implements MenuBar {

  private final javafx.scene.control.MenuBar menuBar = new javafx.scene.control.MenuBar();
  private final Menu mainMenu;
  private final Menu helpMenu;

  public MainMenuBar(String mainMenuName, String helpMenuName) {
    menuBar.setUseSystemMenuBar(true);
    char mainMenuMnemonic = mainMenuName.charAt(0);
    mainMenu = new Menu(mainMenuName, mainMenuMnemonic);
    char helpMenuMnemonic = 'H';
    for (int index = 0; index < helpMenuName.length(); index++) {
      char possibleMnemonic = helpMenuName.charAt(index);
      if (possibleMnemonic != mainMenuMnemonic) {
        helpMenuMnemonic = possibleMnemonic;
        break;
      }
    }
    helpMenu = new Menu(helpMenuName, helpMenuMnemonic);
    menuBar.getMenus().add(mainMenu.getNode());
    menuBar.getMenus().add(helpMenu.getNode());
  }

  @Override
  public IMenu getMainMenu() {
    return mainMenu;
  }

  @Override
  public IMenu getHelpMenu() {
    return helpMenu;
  }

  public javafx.scene.control.MenuBar getMenuBar() {
    return menuBar;
  }
}