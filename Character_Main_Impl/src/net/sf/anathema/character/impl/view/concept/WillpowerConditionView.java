package net.sf.anathema.character.impl.view.concept;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.sf.anathema.character.view.concept.IWillpowerConditionView;

public class WillpowerConditionView implements IWillpowerConditionView {
  private final JTextArea conditionLabel;
  private final JLabel headerLabel;

  public WillpowerConditionView(String labelText) {
    conditionLabel = new JTextArea(2, 45);
    conditionLabel.setEditable(false);
    conditionLabel.setWrapStyleWord(true);
    conditionLabel.setLineWrap(true);
    conditionLabel.setDisabledTextColor(Color.DARK_GRAY);
    JLabel label = new JLabel();
    conditionLabel.setFont(label.getFont());
    conditionLabel.setBackground(label.getBackground());
    this.headerLabel = new JLabel(labelText);
  }

  public void addToStandardPanel(JPanel panel) {
    panel.add(headerLabel, GridDialogLayoutDataUtilities.createTopData());
    panel.add(conditionLabel, GridDialogLayoutDataUtilities.createFillNoGrab());
  }

  public void setEnabled(boolean enabled) {
    if (enabled) {
      headerLabel.setForeground(SystemColor.textText);
    }
    else {
      headerLabel.setForeground(Color.DARK_GRAY);
    }
    conditionLabel.setEnabled(enabled);
  }

  public void setText(String text) {
    conditionLabel.setText(text);
  }
}