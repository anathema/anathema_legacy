package net.sf.anathema.hero.sheet.preferences;

import javafx.scene.Node;
import net.sf.anathema.framework.preferences.elements.PreferenceView;
import net.sf.anathema.framework.preferences.elements.RegisteredPreferenceView;
import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.selection.ComboBoxSelectionView;
import org.tbee.javafx.scene.layout.MigPane;

@RegisteredPreferenceView
public class SheetPreferenceView implements PreferenceView, NodeHolder {
  private final MigPane pane = new MigPane(LayoutUtils.fillWithoutInsets());

  public ObjectSelectionView<PageSize> addObjectSelectionView(String title, AgnosticUIConfiguration<PageSize> uiConfiguration) {
    ComboBoxSelectionView<PageSize> view = new ComboBoxSelectionView<>(title, uiConfiguration);
    pane.add(view.getNode());
    return view;
  }

  @Override
  public Node getNode() {
    return pane;
  }
}
