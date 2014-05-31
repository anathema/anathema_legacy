package net.sf.anathema.framework.environment.fx;

import org.controlsfx.dialog.Dialog;

public interface DialogFactory {
  Dialog createDialog(String title);
}