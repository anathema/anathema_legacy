package net.sf.anathema.hero.combos.display.view;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.sf.anathema.hero.combos.display.presenter.ComboContainer;
import net.sf.anathema.hero.combos.display.presenter.ComboView;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

public class FxComboContainer implements ComboContainer {
  private final MigPane comboPane = new MigPane(LayoutUtils.fillWithoutInsets().wrapAfter(1), new AC().grow().fill());
  private final ScrollPane comboScrollPane = new ScrollPane();

  public FxComboContainer() {
    comboScrollPane.setContent(comboPane);
    comboScrollPane.setFitToWidth(true);
  }

  public Node getNode() {
    return comboScrollPane;
  }

  @Override
  public ComboView addView(String name) {
    FxComboView comboView = new FxComboView(comboPane, name);
    comboPane.add(comboView.getNode(), new CC().push().grow());
    return comboView;
  }
}