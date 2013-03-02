package net.sf.anathema.character.presenter;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;

public class BackgroundListRenderer extends DefaultListCellRenderer {
  private final Displayer displayer;

  public BackgroundListRenderer(Displayer displayer) {
    this.displayer = displayer;
  }

  @Override
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    return super.getListCellRendererComponent(list, displayer.getDisplayObject(value), index, isSelected, cellHasFocus);
  }
}