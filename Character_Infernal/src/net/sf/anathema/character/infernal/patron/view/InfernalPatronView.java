package net.sf.anathema.character.infernal.patron.view;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.infernal.patron.presenter.IInfernalPatronView;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.character.library.trait.view.GroupedTraitView;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class InfernalPatronView implements IView, IInfernalPatronView {

  private JPanel content;
  private final JPanel patronPanel = new JPanel();
  private final JPanel overviewPanel = new JPanel();
  private final GroupedTraitView groupedTraitView = new GroupedTraitView(patronPanel, 3);

  @Override
  public void startGroup(String groupLabel) {
    groupedTraitView.startNewGroup(groupLabel);
  }

  @Override
  public JComponent getComponent() {
    if (content == null) {
      content = new JPanel(new MigLayout(new LC().wrapAfter(1)));
      content.add(patronPanel);
      content.add(overviewPanel);
    }
    return content;
  }

  @Override
  public IToggleButtonTraitView<SimpleTraitView> addYoziSelectionView(String label, IntegerViewFactory factory,
                                                                      IIconToggleButtonProperties viewProperties,
                                                                      boolean selected) {
    return groupedTraitView.addTraitView(label, 0, 0, null, selected, viewProperties, factory);
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