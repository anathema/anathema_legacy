package net.sf.anathema.hero.sheet.preferences;

import javafx.scene.Node;
import net.miginfocom.layout.CC;
import net.sf.anathema.framework.preferences.elements.PreferenceView;
import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.selection.ComboBoxSelectionView;
import org.tbee.javafx.scene.layout.MigPane;

@SuppressWarnings("UnusedDeclaration")
public class FxSheetPreferenceView implements PreferenceView, NodeHolder, SheetPreferenceView {
  private final MigPane pane = new MigPane(LayoutUtils.fillWithoutInsets());

  @Override
  public ObjectSelectionView<PageSize> addObjectSelectionView(String title, AgnosticUIConfiguration<PageSize> uiConfiguration) {
    ComboBoxSelectionView<PageSize> view = new ComboBoxSelectionView<>(title, uiConfiguration);
    pane.add(view.getNode(), new CC().alignY("top"));
    return view;
  }

  @Override
  public Node getNode() {
    return pane;
  }
}
