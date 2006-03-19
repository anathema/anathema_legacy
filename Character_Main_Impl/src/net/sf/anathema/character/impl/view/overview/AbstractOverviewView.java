package net.sf.anathema.character.impl.view.overview;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.sf.anathema.character.view.overview.IOverviewView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledValueView;
import net.sf.anathema.lib.workflow.labelledvalue.view.AbstractLabelledValueView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledIntegerValueView;

public abstract class AbstractOverviewView implements IOverviewView {

  private final Box panel = new Box(BoxLayout.Y_AXIS);
  private final JComponent content = new JScrollPane(
      panel,
      ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

  protected final ILabelledAlotmentView addCategoryView(
      String categoryName,
      int currentValue,
      int maxValue,
      GridDialogPanel overviewPanel) {
    return addCategoryView(categoryName, currentValue, maxValue, overviewPanel, 2);
  }

  protected final ILabelledAlotmentView addCategoryView(
      String categoryName,
      int currentValue,
      int maxValue,
      GridDialogPanel overviewPanel,
      int maxValueLength) {
    LabelledAlotmentView categoryView = new LabelledAlotmentView(categoryName, currentValue, maxValue, maxValueLength);
    categoryView.addComponents(overviewPanel);
    return categoryView;
  }

  protected final ILabelledValueView<Integer> addDerivedView(
      String labelText,
      int currentPoints,
      GridDialogPanel containerPanel) {
    return this.addDerivedView(labelText, currentPoints, containerPanel, 2);
  }

  protected final ILabelledValueView<Integer> addDerivedView(
      String labelText,
      int currentPoints,
      GridDialogPanel containerPanel,
      int maxValueLength) {
    LabelledIntegerValueView derivedView = new LabelledIntegerValueView(labelText, currentPoints, true, maxValueLength);
    derivedView.addComponents(containerPanel);
    return derivedView;
  }

  public JComponent getComponent() {
    return content;
  }

  protected final Box getPanel() {
    return panel;
  }

  protected final void addOverviewPanel(String title, GridDialogPanel overviewPanel) {
    JPanel newPanel = overviewPanel.getContent();
    TitledBorder titledBorder = new TitledBorder(title);
    titledBorder.setTitleFont(AbstractLabelledValueView.deriveSmallerFont(titledBorder.getTitleFont()));
    newPanel.setBorder(titledBorder);
    getPanel().add(newPanel);
  }
}