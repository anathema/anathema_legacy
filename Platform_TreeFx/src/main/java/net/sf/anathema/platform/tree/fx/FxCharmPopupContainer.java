package net.sf.anathema.platform.tree.fx;

import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ToggleButton;
import javafx.stage.WindowEvent;
import net.sf.anathema.platform.tree.display.SpecialCharmContainer;
import net.sf.anathema.platform.tree.view.interaction.SpecialContentMap;

public class FxCharmPopupContainer implements SpecialCharmContainer{

  private final ContextMenu menu;
  private final ToggleButton parent;
  private final SpecialContentMap specialContent;

  public FxCharmPopupContainer(final ToggleButton parent, SpecialContentMap specialContent) {
    this.parent = parent;
    this.specialContent = specialContent;
    this.menu = new ContextMenu();
    menu.setOnHidden(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent windowEvent) {
        parent.setSelected(false);
      }
    });
  }

  public void display() {
    menu.show(parent, Side.BOTTOM, 0, 2);
  }

  public void hide() {
    menu.hide();
  }

  @Override
  public <T> T add(Class<T> clazz, Object... parameters) {
    T createdContent = specialContent.create(clazz, parameters);
    ((FxSpecialContent) createdContent).addTo(menu);
    return createdContent;
  }
}
