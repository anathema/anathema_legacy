package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.NumericVersion;
import de.idos.updates.UpdateSystem;
import de.idos.updates.Updater;
import de.idos.updates.Version;
import de.idos.updates.configuration.ConfiguredUpdateSystem;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Action;
import java.awt.Component;

public class AnathemaUpdateAction extends SmartAction {

  public static Action createMenuAction(IResources resources) {
    AnathemaUpdateAction action = new AnathemaUpdateAction(resources);
    action.setName(resources.getString("Help.UpdateCheck.Title")); //$NON-NLS-1$
    return action;
  }

  private IResources resources;

  public AnathemaUpdateAction(IResources resources) {
    this.resources = resources;
  }

  @Override
  protected void execute(Component parentComponent) {
    Version currentVersion = getCurrentVersion();
    final UpdateSystem updateSystem = ConfiguredUpdateSystem.loadProperties().butDiscoverAvailableVersionThrough(new TagsOnGithub()).andIfTheVersionIsFixedSetItTo(currentVersion).create();
    final Updater updater = updateSystem.checkForUpdates();
    UpdateDialogPage page = new UpdateDialogPage(resources, updateSystem);
    UserDialog dialog = new UserDialog(parentComponent, page);
    dialog.getDialog().setModal(false);
    updateSystem.reportAllProgressTo(new UiProgressReport(page, updateSystem.getInstalledVersion()));
    updateSystem.reportAllProgressTo(new DialogUpdater(dialog));
    dialog.show();
    new Thread(new Runnable() {
      @Override
      public void run() {
        updater.runCheck();
      }
    }).start();
  }

  private Version getCurrentVersion() {
    net.sf.anathema.framework.Version version = new net.sf.anathema.framework.Version(resources);//$NON-NLS-1$
    return new NumericVersion(version.getMajorVersion(), version.getMinorVersion(), version.getRevision());
  }
}