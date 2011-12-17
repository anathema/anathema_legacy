package net.sf.anathema.lib.workflow.labelledvalue.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;

public class LabelledIntegerValueView extends AbstractLabelledIntegerValueView {

  public LabelledIntegerValueView(String labelText, int value, boolean adjustFontSize, int maxValueLength) {
    super(labelText, createLengthString(maxValueLength), value, adjustFontSize);
  }

  /** 2 Columns */
  public void addComponents(JPanel panel) {
    panel.add(titleLabel, GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(valueLabel, GridDialogLayoutDataFactory.createHorizontalFillNoGrab());
  }

  public JLabel getValueLabel() {
    return valueLabel;
  }

  public void addComponents(JPanel panel, int columnCount) {
    panel.add(titleLabel, GridDialogLayoutData.FILL_HORIZONTAL);
    GridDialogLayoutData data = GridDialogLayoutDataFactory.createHorizontalFillNoGrab();
    data.setHorizontalSpan(columnCount - 1);
    panel.add(valueLabel, data);
  }
}