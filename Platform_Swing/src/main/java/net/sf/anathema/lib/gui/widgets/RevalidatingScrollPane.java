package net.sf.anathema.lib.gui.widgets;

import javax.swing.JScrollPane;
import java.awt.Component;

import static net.sf.anathema.lib.gui.swing.GuiUtilities.revalidateTree;

public class RevalidatingScrollPane extends JScrollPane {

  public RevalidatingScrollPane(Component view) {
    super(view);
  }

  @Override
  public void revalidate() {
    revalidateTree(this);
  }
}