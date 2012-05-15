package net.sf.anathema.lib.gui.dialog.action;

import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.DialogMessages;
import net.sf.anathema.lib.gui.resources.LibGuiIconResources;

public abstract class AbstractCopyAction extends SmartAction {

  public AbstractCopyAction() {
    super(DialogMessages.COPY, LibGuiIconResources.COPY);
  }
}