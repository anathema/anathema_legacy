package net.sf.anathema.character.sidereal.colleges.view;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.character.library.trait.view.GroupedTraitView;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeView;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeViewProperties;
import net.sf.anathema.lib.gui.IView;

public class SiderealCollegeView implements IView, ISiderealCollegeView {

  private JPanel content;
  private final JPanel collegePanel = new JPanel();
  private final JPanel overviewPanel = new JPanel();
  private final GroupedTraitView groupedTraitView = new GroupedTraitView(collegePanel, 3);
  private final ISiderealCollegeViewProperties properties;

  public SiderealCollegeView(ISiderealCollegeViewProperties properties) {
    this.properties = properties;
  }

  public void startGroup(String groupLabel) {
    groupedTraitView.startNewGroup(groupLabel);
  }

  public JComponent getComponent() {
    if (content == null) {
      content = new JPanel(new GridDialogLayout(1, false));
      collegePanel.setBorder(new TitledBorder(properties.getCollegeString()));
      content.add(collegePanel);
      content.add(overviewPanel);
    }
    return content;
  }

  public IToggleButtonTraitView<SimpleTraitView> addIntValueView(
      String label,
      IIntValueDisplayFactory factory,
      IIconToggleButtonProperties viewProperties,
      int value,
      int maxValue,
      boolean selected) {
    return groupedTraitView.addTraitView(label, value, maxValue, null, selected, viewProperties, factory);
  }

  public IOverviewCategory createOverview(String borderLabel) {
    return new OverviewCategory(overviewPanel, borderLabel, false);
  }

  public void setOverview(IOverviewCategory overview) {
    overviewPanel.removeAll();
    overviewPanel.add(overview.getComponent());
  }
}