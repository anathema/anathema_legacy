package net.sf.anathema.lib.workflow.labelledvalue.view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public class LabelledStringValueView implements IValueView<String> {

  private final JLabel titleLabel;
  private final JLabel valueLabel;
  private final GridDialogLayoutData textData;

  public LabelledStringValueView(String labelText) {
    this(labelText, GridDialogLayoutDataFactory.createHorizontalFillNoGrab());
  }

  public LabelledStringValueView(String labelText, GridDialogLayoutData textData) {
    this.textData = textData;
    this.titleLabel = new JLabel(labelText);
    this.valueLabel = new JLabel();
  }

  public void addToStandardPanel(JPanel panel) {
    panel.add(titleLabel, new GridDialogLayoutData());
    panel.add(valueLabel, textData);
  }

  public void setTextColor(Color color) {
    titleLabel.setForeground(color);
    valueLabel.setForeground(color);
  }

  public void setFontStyle(int style) {
    titleLabel.setFont(titleLabel.getFont().deriveFont(style));
    valueLabel.setFont(valueLabel.getFont().deriveFont(style));
  }

  public void setValue(String value) {
    valueLabel.setText(value);
  }
}