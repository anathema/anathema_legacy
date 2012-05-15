package net.sf.anathema.framework.presenter.action.menu.help;

import net.sf.anathema.lib.control.IChangeListener;

public interface IUpdateChecker {

  String getCurrentVersion();

  String getLatestVersion();

  IMessageData getMessageData();

  void addDataChangedListener(IChangeListener listener);

  Boolean isCheckSuccessful();
}