package net.sf.anathema.lib.workflow.textualdescription.view;

import net.miginfocom.layout.CC;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.SwingTextView;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;

public class LabelTextView implements ITextView {
  private final SwingTextView textView;
  private JComponent content;
  private Color disabledLabelColor = SystemColor.textInactiveText;
  private final JLabel label;

  public LabelTextView(String labelText, SwingTextView textView) {
    this.label = new JLabel(labelText);
    this.textView = textView;
  }

  @Override
  public void setText(String text) {
    textView.setText(text);
  }

  @Override
  public void addTextChangedListener(ObjectValueListener<String> listener) {
    textView.addTextChangedListener(listener);
  }

  public JComponent getComponent() {
    if (content == null) {
      content = textView.getComponent();
    }
    return content;
  }

  @Override
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

  public void addToMigPanel(JPanel panel) {
    addToMigPanel(panel, new CC().growX());
  }

  public void addToMigPanelSpanning(JPanel panel) {
    addToMigPanel(panel, new CC().growX().spanX());
  }

  public void addToMigPanel(JPanel panel, CC data) {
    panel.add(label);
    panel.add(textView.getComponent(), data);
  }
}