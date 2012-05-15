package net.sf.anathema.lib.gui.dialog.widgets;

import net.sf.anathema.lib.gui.swing.LookAndFeelUtilities;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Font;

/**
 * Component similar to JLabel, but automatically wrapping text.
 */
public class AutoWrappingLabel {

  private static final int INITIAL_WIDTH = 330;

  //10.11.2007 (Markus Gebhard): I have tried to implement this using a JTextArea, but this failed.
  // Problem is the size: there is no good way to find out the required height in order not to crop
  // the text. So after trying really hard, I decided to write my own text swing
  private final AutoWrappingTextComponent component;

  public AutoWrappingLabel() {
    this(""); //$NON-NLS-1$
  }

  public AutoWrappingLabel(String text) {
    this(text, INITIAL_WIDTH);
  }

  public AutoWrappingLabel(String text, int width) {
    component = new AutoWrappingTextComponent(text, width);
    LookAndFeelUtilities.installColorsAndFont(component, LookAndFeelUtilities.COMPONENT_TYPE_LABEL);
  }

  public void setFont(Font font) {
    component.setFont(font);
  }

  public JComponent getContent() {
    return component;
  }

  public void setForeground(Color color) {
    component.setForeground(color);
  }

  public void setBackground(Color color) {
    component.setBackground(color);
  }

  public void setText(String text) {
    component.setText(text);
  }

  public void setOpaque(boolean opaque) {
    component.setOpaque(opaque);
  }
}