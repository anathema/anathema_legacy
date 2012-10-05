package net.sf.anathema.lib.workflow.labelledvalue.view;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.miginfocom.layout.CC;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabelledIntegerValueView extends AbstractLabelledIntegerValueView {
  public LabelledIntegerValueView(String labelText, int value, boolean adjustFontSize, int maxValueLength) {
    super(labelText, createLengthString(maxValueLength), value, adjustFontSize);
  }

  /**
   * 2 Columns
   */
  public void addComponents(JPanel panel) {
    panel.add(titleLabel, new CC().pushX().growX());
    panel.add(valueLabel, new CC().alignX("right"));
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