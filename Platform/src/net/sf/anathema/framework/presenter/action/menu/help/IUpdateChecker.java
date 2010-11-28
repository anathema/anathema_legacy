package net.sf.anathema.framework.presenter.action.menu.help;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface IUpdateChecker {

  public String getCurrentVersion();

  public String getLatestVersion();

  public IMessageData getMessageData();

  public void addDataChangedListener(IChangeListener listener);

  public Boolean isCheckSuccessful();
}