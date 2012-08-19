package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.presenter.view.SpecialCharmContainer;

import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;

public class PopupSpecialCharmContainer implements SpecialCharmContainer {

  private final JPopupMenu menu;
  private final JToggleButton parent;
  private final SpecialContentMap specialContent;

  public PopupSpecialCharmContainer(JToggleButton parent, SpecialContentMap specialContent) {
    this.parent = parent;
    this.specialContent = specialContent;
    this.menu = new JPopupMenu();
    menu.addPopupMenuListener(new UntogglingListener(parent));
  }

  public void display() {
    menu.show(parent, 0, 0);
  }

  public void hide() {
    menu.setVisible(false);
  }

  @Override
  public <T> T add(Class<T> clazz, Object... parameters) {
    T createdContent = specialContent.create(clazz, parameters);
    ((SpecialContent) createdContent).addTo(menu);
    return createdContent;
  }
}