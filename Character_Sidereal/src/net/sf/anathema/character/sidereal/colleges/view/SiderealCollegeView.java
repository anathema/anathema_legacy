package net.sf.anathema.character.sidereal.colleges.view;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.character.library.trait.view.GroupedTraitView;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class SiderealCollegeView implements IView, ISiderealCollegeView {

  private JPanel content;
  private final JPanel collegePanel = new JPanel();
  private final JPanel overviewPanel = new JPanel();
  private final GroupedTraitView groupedTraitView = new GroupedTraitView(collegePanel, 2);

  @Override
  public void startGroup(String groupLabel) {
    groupedTraitView.startNewGroup(groupLabel);
  }

  @Override
  public JComponent getComponent() {
    if (content == null) {
      content = new JPanel(new GridDialogLayout(1, false));
      content.add(collegePanel);
      content.add(overviewPanel);
    }
    return content;
  }

  @Override
  public IToggleButtonTraitView<SimpleTraitView> addIntValueView(
      String label,
      IntegerViewFactory factory,
      IIconToggleButtonProperties viewProperties,
      int value,
      int maxValue,
      boolean selected) {
    return groupedTraitView.addTraitView(label, value, maxValue, null, selected, viewProperties, factory);
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