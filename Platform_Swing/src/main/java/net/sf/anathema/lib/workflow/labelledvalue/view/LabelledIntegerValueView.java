package net.sf.anathema.lib.workflow.labelledvalue.view;

import net.miginfocom.layout.CC;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabelledIntegerValueView extends AbstractLabelledIntegerValueView {
  public LabelledIntegerValueView(String labelText, int value, boolean adjustFontSize, int maxValueLength) {
    super(labelText, createLengthString(maxValueLength), value, adjustFontSize);
  }

  public void addComponents(JPanel panel) {
    panel.add(titleLabel, new CC().pushX().growX());
    panel.add(valueLabel, new CC().spanX().growX());
  }

  public JLabel getValueLabel() {
    return valueLabel;
  }
}