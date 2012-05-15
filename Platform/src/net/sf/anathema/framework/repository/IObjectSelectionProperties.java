package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.IDialogProperties;
import net.sf.anathema.lib.message.IBasicMessage;

public interface IObjectSelectionProperties extends IDialogProperties {

  String getTitle();

  IBasicMessage getDefaultMessage();
}