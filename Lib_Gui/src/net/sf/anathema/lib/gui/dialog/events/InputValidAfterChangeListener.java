package net.sf.anathema.lib.gui.dialog.events;

import net.sf.anathema.lib.control.IChangeListener;

public class InputValidAfterChangeListener implements IChangeListener {

  private final IInputValidCheckable checkable;

  public InputValidAfterChangeListener(IInputValidCheckable checkable) {
    this.checkable = checkable;
  }

  @Override
  public void changeOccurred() {
    checkable.checkInputValid();
  }
}