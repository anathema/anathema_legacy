package net.sf.anathema.lib.gui.dialog.input;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.lib.control.IChangeListener;

public interface ISmartDialogPanel extends IDialogComponent  {

  public void addChangeListener(IChangeListener listener);

  public void requestFocus();
}