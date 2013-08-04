package net.sf.anathema.hero.charms.display;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import net.sf.anathema.framework.value.IntValueView;
import net.sf.anathema.fx.hero.traitview.FxTraitView;
import net.sf.anathema.lib.control.IntValueChangedListener;
import net.sf.anathema.platform.tree.fx.FxSpecialContent;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.fillWithoutInsets;

public class FxIntValueView implements FxSpecialContent, IntValueView {
  private FxTraitView view;

  public FxIntValueView(FxTraitView view) {
    this.view = view;
  }

  @Override
  public void setValue(int newValue) {
    view.setValue(newValue);
  }

  @Override
  public void addIntValueChangedListener(IntValueChangedListener listener) {
    view.addIntValueChangedListener(listener);
  }

  @Override
  public void removeIntValueChangedListener(IntValueChangedListener listener) {
    view.removeIntValueChangedListener(listener);
  }

  @Override
  public void addTo(ContextMenu menu) {
    MigPane container = new MigPane(fillWithoutInsets().wrapAfter(2));
    view.addTo(container);
    menu.getItems().add(new MenuItem("", container));
  }
}