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
import net.sf.anathema.character.library.trait.view.FrontToggleButtonTraitViewWrapper;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeView;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeViewProperties;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.GroupedGridDialogPanel;

public class SiderealCollegeView implements IView, ISiderealCollegeView {

  private final GroupedGridDialogPanel collegeGroupPanel = new GroupedGridDialogPanel(3);
  private JPanel content;
  private final JPanel overviewPanel = new JPanel();
  private final ISiderealCollegeViewProperties properties;

  public SiderealCollegeView(ISiderealCollegeViewProperties properties) {
    this.properties = properties;
  }

  public void startGroup(String groupLabel) {
    collegeGroupPanel.startNewGroup(groupLabel);
  }

  public JComponent getComponent() {
    if (content == null) {
      content = new JPanel(new GridDialogLayout(1, false));
      addCollegePanel();
      content.add(overviewPanel);
    }
    return content;
  }

  private void addCollegePanel() {
    JPanel collegePanel = new JPanel();
    collegePanel.setBorder(new TitledBorder(properties.getCollegeString()));
    collegeGroupPanel.addOverallView(collegePanel);
    content.add(collegePanel);
  }

  public IToggleButtonTraitView<SimpleTraitView> addIntValueView(
      String label,
      IIntValueDisplayFactory factory,
      IIconToggleButtonProperties viewProperties,
      int value,
      int maxValue,
      boolean selected) {
    SimpleTraitView view = new SimpleTraitView(factory, label, value, maxValue);
    FrontToggleButtonTraitViewWrapper<SimpleTraitView> traitView = new FrontToggleButtonTraitViewWrapper<SimpleTraitView>(
        view,
        viewProperties,
        selected);
    collegeGroupPanel.addEntry(traitView);
    return traitView;
  }

  public IOverviewCategory createOverview(String borderLabel) {
    return new OverviewCategory(borderLabel, false);
  }

  public void setOverview(IOverviewCategory overview) {
    overviewPanel.removeAll();
    overviewPanel.add(overview.getComponent());
  }
}