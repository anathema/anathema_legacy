package net.sf.anathema.campaign.music.impl.view.selection;

import net.miginfocom.layout.CC;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class LabelledStringValueView implements IValueView<String> {

  private final JLabel titleLabel;
  private final JLabel valueLabel;

  public LabelledStringValueView(String labelText) {
    this.titleLabel = new JLabel(labelText);
    this.valueLabel = new JLabel();
  }

  public void addToPanel(JPanel panel) {
    panel.add(titleLabel);
    panel.add(valueLabel, new CC().growX());
  }

  @Override
  public void setTextColor(Color color) {
    titleLabel.setForeground(color);
    valueLabel.setForeground(color);
  }

  @Override
  public void setFontStyle(int style) {
    titleLabel.setFont(titleLabel.getFont().deriveFont(style));
    valueLabel.setFont(valueLabel.getFont().deriveFont(style));
  }

  @Override
  public void setValue(String value) {
    valueLabel.setText(value);
  }
}