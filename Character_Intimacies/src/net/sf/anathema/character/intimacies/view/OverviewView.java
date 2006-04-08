package net.sf.anathema.character.intimacies.view;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledValueView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.view.LabelledIntegerValueView;

public class OverviewView implements IOverviewView {

  private final GridDialogPanel panel = new GridDialogPanel();
  private JPanel content;
  private final String borderTitle;

  public OverviewView(String borderTitle) {
    this.borderTitle = borderTitle;
  }

  public JComponent getComponent() {
    if (content == null) {
      content = panel.getContent();
      content.setBorder(new TitledBorder(borderTitle));
    }
    return content;
  }

  public ILabelledAlotmentView addAlotmentView(String labelText, int maxValueLength) {
    LabelledAlotmentView view = new LabelledAlotmentView(labelText, 0, 0, maxValueLength);
    view.addComponents(panel);
    return view;
  }

  public ILabelledValueView<Integer> addValueView(String labelText, int maxValueLength) {
    LabelledIntegerValueView view = new LabelledIntegerValueView(labelText, 0, true, maxValueLength);
    view.addComponents(panel);
    return view;
  }
}