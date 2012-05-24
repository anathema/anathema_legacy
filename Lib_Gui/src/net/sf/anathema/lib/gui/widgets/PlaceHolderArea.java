package net.sf.anathema.lib.gui.widgets;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceHolderArea extends JTextArea implements FocusListener {

  private final Color placeHolder = new Color(50, 60, 180);
  private String placeHolderText;
  private boolean placeHolderVisible;

  public PlaceHolderArea(String placeHolderText) {
    this(placeHolderText, 5, 20);
  }

  public PlaceHolderArea(String placeHolderText, int rows, int columns) {
    super(rows, columns);
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
      g.setFont(g.getFont().deriveFont(Font.ITALIC));
      g.setColor(placeHolder);
      int fontHeight = g.getFontMetrics().getHeight();
      g.drawString(placeHolderText, 4, fontHeight + 3);
    }
  }
}