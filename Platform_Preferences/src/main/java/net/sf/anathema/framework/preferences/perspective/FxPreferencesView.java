package net.sf.anathema.framework.preferences.perspective;

import javafx.scene.Node;
import net.sf.anathema.framework.preferences.elements.PreferenceView;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import org.tbee.javafx.scene.layout.MigPane;

public class FxPreferencesView implements PreferencesView {
  private final Node node = new MigPane(LayoutUtils.fillWithoutInsets());

  public Node getNode() {
    return node;
  }

  public void show(PreferenceView view) {
  }
}
