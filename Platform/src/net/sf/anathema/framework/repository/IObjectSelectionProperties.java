package net.sf.anathema.framework.repository;

import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.framework.IDialogProperties;

public interface IObjectSelectionProperties extends IDialogProperties {

  public String getTitle();

  public IBasicMessage getDefaultMessage();
}