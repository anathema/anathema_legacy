package net.sf.anathema.lib.workflow.labelledvalue.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    Dimension screenSize = getScreenSize();
    float fontSize = screenSize.width / 110;
    if (fontSize >= font.getSize()) {
      return font;
    }
    return font.deriveFont(fontSize);
  }

  private static Dimension getScreenSize() {
    if (!GraphicsEnvironment.isHeadless()) {
      return Toolkit.getDefaultToolkit().getScreenSize();
    } else {
      return new Dimension(1024, 768);
    }
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

  protected Collection<JComponent> getComponents() {
    List<JComponent> components = new ArrayList<JComponent>();
    components.add(titleLabel);
    components.add(valueLabel);
    return components;
  }
}