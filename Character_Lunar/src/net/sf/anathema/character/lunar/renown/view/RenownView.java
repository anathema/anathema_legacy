package net.sf.anathema.character.lunar.renown.view;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.widgets.HorizontalLine;
import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledValueView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledIntegerValueView;

public class RenownView implements ISimpleTabView {

  private final JPanel content = new JPanel(new GridDialogLayout(2, false));
  private final JPanel mainPanel = new JPanel(new GridDialogLayout(1, false));
  private final JPanel overviewPanel = new JPanel(new GridDialogLayout(1, false));
  private final IGridDialogPanel renownPanel = new DefaultGridDialogPanel();
  private final IGridDialogPanel totalPanel = new DefaultGridDialogPanel();
  private final IGridDialogPanel facePanel = new DefaultGridDialogPanel();

  public JComponent getComponent() {
    mainPanel.add(renownPanel.getContent());
    mainPanel.add(new HorizontalLine(), GridDialogLayoutData.FILL_HORIZONTAL);
    mainPanel.add(totalPanel.getContent());
    mainPanel.add(facePanel.getContent());
    content.add(mainPanel);
    GridDialogLayoutData data = new GridDialogLayoutData();
    data.setVerticalAlignment(GridAlignment.BEGINNING);
    content.add(overviewPanel, data);
    return content;
  }

  public boolean needsScrollbar() {
    return false;
  }

  public IIntValueView addIntValueView(String label) {
    SpinnerTraitView view = new SpinnerTraitView(label);
    view.addTo(renownPanel);
    return view;
  }

  public ILabelledValueView<Integer> addTotalView(String string) {
    LabelledIntegerValueView view = new LabelledIntegerValueView(string, 0, false, 4);
    view.addComponents(totalPanel);
    return view;
  }

  public IIntValueView addFaceSelectionView(String label, ListCellRenderer renderer, int maximum) {
    ObjectSelectionIntValueView view = new ObjectSelectionIntValueView(label, renderer, maximum);
    view.addTo(facePanel);
    return view;
  }

  public ILabelledAlotmentView addOverview(String borderLabel, String categoryLabel) {
    overviewPanel.setBorder(new TitledBorder(borderLabel));
    IGridDialogPanel panel = new DefaultGridDialogPanel();
    LabelledAlotmentView renownPointsView = new LabelledAlotmentView(categoryLabel, 0, 0);
    renownPointsView.addComponents(panel);
    overviewPanel.add(panel.getContent());
    return renownPointsView;
  }

  public void showOverview(boolean show) {
    if (!show) {
      content.remove(overviewPanel);
    }
    else {
      content.add(overviewPanel);
    }
  }
}