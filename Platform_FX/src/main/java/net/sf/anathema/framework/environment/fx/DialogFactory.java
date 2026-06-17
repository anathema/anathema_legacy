package net.sf.anathema.framework.environment.fx;

import net.sf.anathema.framework.fx.compat.Dialog;

public interface DialogFactory {
  Dialog createDialog(String title);
}