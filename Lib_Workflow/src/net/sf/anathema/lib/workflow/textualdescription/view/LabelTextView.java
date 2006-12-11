package net.sf.anathema.lib.workflow.textualdescription.view;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class LabelTextView implements ITextView {

  private final ITextView textView;
  private JComponent content;
  private Color disabledLabelColor = SystemColor.textInactiveText;
  private final JLabel label;

  public LabelTextView(String labelText, ITextView textView) {
    this.label = new JLabel(labelText);
    this.textView = textView;
  }

  public void setText(String text) {
    textView.setText(text);
  }

  public void addTextChangedListener(final IObjectValueChangedListener<String> listener) {
    textView.addTextChangedListener(listener);
  }

  public JComponent getComponent() {
    if (content == null) {
      content = textView.getComponent();
    }
    return content;
  }

  public void setEnabled(boolean enabled) {
    textView.setEnabled(enabled);
    if (enabled) {
      label.setForeground(SystemColor.textText);
    }
    adjustLabelToDisabledColor();
  }

  public void setDisabledLabelColor(Color color) {
    this.disabledLabelColor = color;
    adjustLabelToDisabledColor();
  }

  private void adjustLabelToDisabledColor() {
    if (!label.isEnabled()) {
      label.setForeground(disabledLabelColor);
    }
  }

  public void addToStandardPanel(JPanel panel) {
    panel.add(label, GridDialogLayoutDataUtilities.createTopData());
    panel.add(textView.getComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
  }

  public void addToStandardPanel(JPanel panel, GridDialogLayoutData textFieldData) {
    panel.add(label, GridDialogLayoutDataUtilities.createTopData());
    panel.add(textView.getComponent(), textFieldData);
  }

  public void addToStandardPanel(JPanel panel, int columnCount) {
    panel.add(label, GridDialogLayoutDataUtilities.createTopData());
    panel.add(textView.getComponent(), GridDialogLayoutDataUtilities.createHorizontalSpanData(
        columnCount,
        GridDialogLayoutData.FILL_HORIZONTAL));
  }
}