package net.sf.anathema.character.lunar.renown.view;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.disy.commons.swing.widgets.HorizontalLine;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.framework.presenter.view.ObjectSelectionIntValueView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledIntegerValueView;

public class RenownView implements IView {

  private final JPanel content = new JPanel(new GridDialogLayout(2, false));
  private final JPanel mainPanel = new JPanel(new GridDialogLayout(1, false));
  private final JPanel overviewPanel = new JPanel(new GridDialogLayout(1, false));
  private final JPanel facePanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel renownPanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel totalPanel = new JPanel(new GridDialogLayout(2, false));

  public JComponent getComponent() {
    mainPanel.add(renownPanel);
    mainPanel.add(new HorizontalLine(), GridDialogLayoutData.FILL_HORIZONTAL);
    mainPanel.add(totalPanel);
    mainPanel.add(facePanel);
    content.add(mainPanel);
    content.add(overviewPanel, GridDialogLayoutDataFactory.createTopData());
    return content;
  }

  public IIntValueView addIntValueView(String label) {
    SpinnerTraitView view = new SpinnerTraitView(label);
    view.addTo(renownPanel);
    return view;
  }

  public IValueView<Integer> addTotalView(String string) {
    LabelledIntegerValueView view = new LabelledIntegerValueView(string, 0, false, 4);
    view.addComponents(totalPanel);
    return view;
  }

  public IIntValueView addFaceSelectionView(String label, ListCellRenderer renderer, int maximum) {
    ObjectSelectionIntValueView view = new ObjectSelectionIntValueView(label, renderer, maximum);
    facePanel.add(view.getComponent());
    return view;
  }

  public ILabelledAlotmentView addOverview(String borderLabel, String categoryLabel) {
    OverviewCategory category = new OverviewCategory(overviewPanel, borderLabel, false);
    ILabelledAlotmentView view = category.addAlotmentView(categoryLabel, 2);
    overviewPanel.add(category.getComponent());
    return view;
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