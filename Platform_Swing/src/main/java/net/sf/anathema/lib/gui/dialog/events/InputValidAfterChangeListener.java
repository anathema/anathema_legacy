package net.sf.anathema.lib.gui.dialog.events;

import net.sf.anathema.lib.control.ChangeListener;

public class InputValidAfterChangeListener implements ChangeListener {

  private final IInputValidCheckable checkable;

  public InputValidAfterChangeListener(IInputValidCheckable checkable) {
    this.checkable = checkable;
  }

  @Override
  public void changeOccurred() {
    checkable.checkInputValid();
  }
}