package net.sf.anathema.lib.gui.dialog.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public interface IDialogConstants {
  Dimension MINIMUM_CONTENT_SIZE = new Dimension(310, 50);

  Font MESSAGE_LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, 11);
  Font HEADER_TITLE_FONT = new Font(Font.DIALOG, Font.BOLD, 13);

  //Always use black on white color for header panel. There are no L&F colors defined for dialog headers 
  Color HEADER_TEXT_COLOR = Color.BLACK;

  //Always use a bright grey color for header panel background, since there are no L&F colors defined for dialog headers 
  Color HEADER_BACKGROUND_COLOR = Color.WHITE;

  Color HEADER_OVERLAID_BORDER_COLOR = Color.DARK_GRAY;
}