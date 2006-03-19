package net.sf.anathema.campaign.music.presenter.library.control;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import net.sf.anathema.campaign.music.model.libary.ILibrary;

public class LibraryCellRenderer extends DefaultListCellRenderer {

  @Override
  public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus) {
    ILibrary library = (ILibrary) value;
    return super.getListCellRendererComponent(list, library.getName(), index, isSelected, cellHasFocus);
  }
}