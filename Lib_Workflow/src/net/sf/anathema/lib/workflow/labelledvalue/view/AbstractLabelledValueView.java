package net.sf.anathema.lib.workflow.labelledvalue.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public abstract class AbstractLabelledValueView {

  private static Dimension createBoldSize(String text, boolean smallFontSize) {
    JLabel sizeLabel = new JLabel(text);
    if (smallFontSize) {
      sizeLabel.setFont(deriveSmallerFont(sizeLabel.getFont()));
    }
    sizeLabel.setFont(sizeLabel.getFont().deriveFont(Font.BOLD));
      return sizeLabel.getPreferredSize();
  }

  protected static JLabel createLabel(
      String text,
      String sizeText,
      int horizontalAlignment,
      boolean adjustFontSize) {
    JLabel label = new JLabel(text);
    label.setPreferredSize(createBoldSize(sizeText, adjustFontSize));
    if (adjustFontSize) {
      label.setFont(deriveSmallerFont(label.getFont()));
    }
    label.setHorizontalAlignment(horizontalAlignment);
    return label;
  }

  public static Font deriveSmallerFont(Font font) {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    float fontSize = screenSize.width / 110;
    if (fontSize >= font.getSize()) {
      return font;
    }
    return font.deriveFont(fontSize);
  }

  protected final JLabel titleLabel;
  protected final JLabel valueLabel;

  protected AbstractLabelledValueView(String labelText, String value, String valueLabelSizeText, boolean adjustFontSize) {
    this.titleLabel = createLabel(labelText, labelText, SwingConstants.LEFT, adjustFontSize);
    this.valueLabel = createLabel(value, valueLabelSizeText, SwingConstants.RIGHT, adjustFontSize);
    setText(value);
  }

  protected final void setText(String value) {
    valueLabel.setText(value);
  }

  public void setTextColor(Color color) {
    for (JComponent component : getComponents()) {
      component.setForeground(color);
    }
  }

  public void setFontStyle(int style) {
    for (JComponent component : getComponents()) {
      component.setFont(component.getFont().deriveFont(style));
    }
  }

  protected Collection<JComponent> getComponents() {
    List<JComponent> components = new ArrayList<JComponent>();
    components.add(titleLabel);
    components.add(valueLabel);
    return components;
  }
}