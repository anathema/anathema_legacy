package net.sf.anathema.lib.gui.widgets;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceHolderField extends JTextField implements FocusListener {

  private final Color placeHolder = new Color(50, 60, 180);
  private String placeHolderText;
  private boolean placeHolderVisible;

  public PlaceHolderField(String placeHolderText) {
    this(placeHolderText, 20);
  }

  public PlaceHolderField(String placeHolderText, int columns) {
    super(columns);
    setPlaceHolder(placeHolderText);
    showPlaceHolder();
    validate();
    addFocusListener(this);
  }

  public void setPlaceHolder(String placeHolderText) {
    this.placeHolderText = placeHolderText;
  }

  @Override
  public void focusGained(FocusEvent e) {
    hidePlaceholder();
  }

  @Override
  public void focusLost(FocusEvent e) {
    showPlaceHolder();
  }

  private void showPlaceHolder() {
    placeHolderVisible = true;
  }

  private void hidePlaceholder() {
    placeHolderVisible = false;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (placeHolderVisible && getText().isEmpty()) {
      Font font = g.getFont().deriveFont(Font.ITALIC);
      g.setFont(font);
      g.setColor(placeHolder);
      g.drawString(placeHolderText, 3, (getHeight() / 2) + 5);
    }
  }
}