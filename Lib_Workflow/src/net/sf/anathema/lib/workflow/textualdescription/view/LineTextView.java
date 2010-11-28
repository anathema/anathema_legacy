package net.sf.anathema.lib.workflow.textualdescription.view;

import javax.swing.JTextField;

public class LineTextView extends TextView {

  public LineTextView(int columns) {
    super(new JTextField(columns));
  }
}