package net.sf.anathema.hero.combos.display.view;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import net.sf.anathema.hero.combos.display.presenter.ComboContainer;
import net.sf.anathema.hero.combos.display.presenter.ComboView;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

public class FxComboContainer implements ComboContainer {
  private final MigPane comboPane = new MigPane(LayoutUtils.fillWithoutInsets());
  private final ScrollPane comboScrollPane = new ScrollPane();

  public FxComboContainer() {
    comboScrollPane.setContent(comboPane);
  }

  public Node getNode() {
    return comboScrollPane;
  }

  @Override
  public ComboView addView(String name, String description) {
    FxComboView comboView = new FxComboView(comboPane, name, description);
    comboPane.add(comboView.getNode());
    return comboView;
  }
}