package net.sf.anathema.lib.gui.dialog.input;

import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.lib.control.IChangeListener;

public interface ISmartDialogPanel extends IDialogComponent  {

  void addChangeListener(IChangeListener listener);

  void requestFocus();
}