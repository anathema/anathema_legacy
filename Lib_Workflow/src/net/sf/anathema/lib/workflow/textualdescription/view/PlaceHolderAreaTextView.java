package net.sf.anathema.lib.workflow.textualdescription.view;

import net.sf.anathema.lib.gui.widgets.PlaceHolderArea;

import javax.swing.JComponent;
import javax.swing.JTextArea;

public class PlaceHolderAreaTextView extends TextView {

  private JTextArea textArea;

  public PlaceHolderAreaTextView(String placeHolderText, int rows, int columns) {
    super(new PlaceHolderArea(placeHolderText, rows, columns));
    textArea = (JTextArea) getTextComponent();
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
  }

  @Override
  public JComponent getComponent() {
    return textArea;
  }
}