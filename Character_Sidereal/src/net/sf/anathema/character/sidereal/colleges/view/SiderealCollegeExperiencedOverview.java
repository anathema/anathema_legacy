package net.sf.anathema.character.sidereal.colleges.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeExperiencedOverview;
import net.sf.anathema.character.sidereal.colleges.presenter.ISiderealCollegeViewProperties;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledIntegerValueView;

public class SiderealCollegeExperiencedOverview implements ISiderealCollegeExperiencedOverview {

  private JPanel content;
  private LabelledIntegerValueView view;

  public SiderealCollegeExperiencedOverview(ISiderealCollegeViewProperties properties) {
    this.view = new LabelledIntegerValueView(properties.getExperiencePointsString(), 0, true, 3);
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
    view.addComponents(panel);
    overview.add(panel.getContent());
    return overview;
  }

  public void setExperiencePointsSpent(int spent) {
    view.setValue(spent);
  }
}