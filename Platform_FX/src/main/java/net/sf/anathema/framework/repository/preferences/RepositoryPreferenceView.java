package net.sf.anathema.framework.repository.preferences;

import javafx.scene.Node;
import net.sf.anathema.framework.preferences.elements.PreferenceView;
import net.sf.anathema.framework.preferences.elements.RegisteredPreferenceView;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.FxTextView;
import net.sf.anathema.platform.fx.NodeHolder;
import org.tbee.javafx.scene.layout.MigPane;

@RegisteredPreferenceView
public class RepositoryPreferenceView implements PreferenceView, NodeHolder {

  private final MigPane pane = new MigPane(LayoutUtils.fillWithoutInsets());

  public ITextView addRepositoryDisplay(String label) {
    FxTextView view = FxTextView.SingleLine(label);
    pane.add(view.getNode());
    return view;
  }

  @Override
  public Node getNode() {
    return pane;
  }
  //button: Select
  //button: Open
  //button: Default
}
