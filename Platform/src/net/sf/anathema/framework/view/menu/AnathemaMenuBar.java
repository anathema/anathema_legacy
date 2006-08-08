package net.sf.anathema.framework.view.menu;

import javax.swing.JMenuBar;

import net.sf.anathema.framework.view.IMenuBar;

public class AnathemaMenuBar implements IMenuBar {

  private final JMenuBar menuBar = new JMenuBar();
  private final IMenuBarView mainMenu;
  private final IMenuBarView helpMenu;

  public AnathemaMenuBar(String mainMenuName, String helpMenuName) {
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
    menuBar.add(mainMenu.getComponent());
    menuBar.add(helpMenu.getComponent());
  }

  public IMenu addMenu(String title) {
    Menu menu = new Menu(title);
    menuBar.add(menu.getComponent(), menuBar.getComponentCount() - 1);
    return menu;
  }

  public IMenu getMainMenu() {
    return mainMenu;
  }

  public IMenu getHelpMenu() {
    return helpMenu;
  }

  public JMenuBar getMenuBar() {
    return menuBar;
  }
}