package net.sf.anathema.character.presenter;

import javax.swing.*;
import java.awt.*;

public class BackgroundListRenderer extends DefaultListCellRenderer {
  private static final long serialVersionUID = 1L;
  private final Displayer displayer;

  public BackgroundListRenderer(Displayer displayer) {
    this.displayer = displayer;
  }

  @Override
  public Component getListCellRendererComponent(
          JList list,
          Object value,
          int index,
          boolean isSelected,
          boolean cellHasFocus) {
    return super.getListCellRendererComponent(list, displayer.getDisplayObject(value), index, isSelected, cellHasFocus);
  }
}