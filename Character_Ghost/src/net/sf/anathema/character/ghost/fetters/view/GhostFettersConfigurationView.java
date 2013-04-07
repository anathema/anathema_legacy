package net.sf.anathema.character.ghost.fetters.view;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.framework.swing.IView;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class GhostFettersConfigurationView implements IGhostFettersConfigurationView, IView {
  private final IntegerViewFactory factory;
  private final JPanel mainPanel = new JPanel(new MigLayout(withoutInsets().fillY()));
  private final JPanel fetterListPanel = new JPanel(new MigLayout(new LC().insets("2")));
  private final JPanel fetterPanel = new JPanel(new MigLayout(new LC().wrapAfter(1).fill()));
  private final JPanel overviewPanel = new JPanel();

  public GhostFettersConfigurationView(IntegerViewFactory factory) {
    this.factory = factory;
  }

  @Override
  public ButtonControlledEditView addFetterSelectionView(String labelText, Icon addIcon) {
    ButtonControlledEditView objectSelectionView = new ButtonControlledEditView(addIcon);
    fetterPanel.add(objectSelectionView.getComponent());
    return objectSelectionView;
  }

  @Override
  public IFetterView addFetterView(String fetterName, Icon deleteIcon, int value, int maxValue) {
    FetterView fetterView = new FetterView(factory, deleteIcon, fetterName, value, maxValue);
    fetterView.addComponents(fetterListPanel);
    return fetterView;
  }

  @Override
  public JComponent getComponent() {
    fetterPanel.add(new JScrollPane(fetterListPanel), new CC().grow().push());
    mainPanel.add(fetterPanel, new CC().grow());
    mainPanel.add(overviewPanel, new CC().grow());
    return mainPanel;
  }

  @Override
  public IOverviewCategory createOverview(String borderLabel) {
    return new OverviewCategory(overviewPanel, borderLabel, false);
  }

  @Override
  public void setOverview(IOverviewCategory overview) {
    overviewPanel.removeAll();
    overviewPanel.add(overview.getComponent());
  }
}