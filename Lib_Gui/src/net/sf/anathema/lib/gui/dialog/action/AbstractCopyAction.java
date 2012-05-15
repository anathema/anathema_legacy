package net.sf.anathema.lib.gui.dialog.action;

import net.disy.commons.swing.resources.DisyCommonsSwingIconResources;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.DialogMessages;

public abstract class AbstractCopyAction extends SmartAction {

  public AbstractCopyAction() {
    super(DialogMessages.COPY, DisyCommonsSwingIconResources.COPY);
  }
}