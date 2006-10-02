package net.sf.anathema.framework.view.messaging;

import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.gui.IView;

public interface IAnathemaStatusBar extends IView {

  public void setLatestMessage(IBasicMessage message);
}