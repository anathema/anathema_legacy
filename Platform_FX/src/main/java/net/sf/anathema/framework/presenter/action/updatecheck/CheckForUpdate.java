package net.sf.anathema.framework.presenter.action.updatecheck;

import de.idos.updates.Updater;

public class CheckForUpdate implements Runnable {
  private final Updater updater;

  public CheckForUpdate(Updater updater) {
    this.updater = updater;
  }

  @Override
  public void run() {
    updater.runCheck();
  }
}
