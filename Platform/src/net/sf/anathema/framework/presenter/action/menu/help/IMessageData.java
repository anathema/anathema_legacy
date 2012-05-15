package net.sf.anathema.framework.presenter.action.menu.help;

import net.sf.anathema.lib.message.MessageType;

public interface IMessageData {

  String getKey();

  MessageType getType();
}