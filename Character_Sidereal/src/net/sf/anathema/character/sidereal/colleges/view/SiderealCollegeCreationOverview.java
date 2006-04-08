package net.sf.anathema.character.sidereal.colleges.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeCreationOverview;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeViewProperties;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledIntegerValueView;

public class SiderealCollegeCreationOverview implements ISiderealCollegeCreationOverview {

  private final ISiderealCollegeViewProperties properties;
  private JPanel content;
  private LabelledAlotmentView favoredDotsView;
  private LabelledAlotmentView generalDotsView;
  private LabelledIntegerValueView bonusPointsView;

  public SiderealCollegeCreationOverview(ISiderealCollegeViewProperties properties) {
    this.properties = properties;
  }

  public JComponent getContent() {
    if (content == null) {
      content = createContent();
    }
    return content;
  }

  private JPanel createContent() {
    JPanel overview = new JPanel(new GridDialogLayout(2, false));
    IGridDialogPanel panel = new DefaultGridDialogPanel();
    favoredDotsView = new LabelledAlotmentView(properties.getFavoredCollegeDotsString(), 0, 0);
    generalDotsView = new LabelledAlotmentView(properties.getGeneralCollegeDotsString(), 0, 0);
    bonusPointsView = new LabelledIntegerValueView(properties.getCollegeBonusPointsString(), 0, true, 2);
    favoredDotsView.addComponents(panel);
    generalDotsView.addComponents(panel);
    bonusPointsView.addComponents(panel);
    overview.add(panel.getContent());
    return overview;
  }

  public void setFavoredDotsOverview(int favoredPicksSpent, int favoredPicksTotal) {
    favoredDotsView.setValue(favoredPicksSpent);
    favoredDotsView.setAlotment(favoredPicksTotal);
  }

  public void setGeneralDotsOverview(int generalPicksSpent, int generalPicksTotal) {
    generalDotsView.setValue(generalPicksSpent);
    generalDotsView.setAlotment(generalPicksTotal);
  }

  public void setBonusPointsOverview(int bonusPointsSpent) {
    bonusPointsView.setValue(bonusPointsSpent);
  }
}