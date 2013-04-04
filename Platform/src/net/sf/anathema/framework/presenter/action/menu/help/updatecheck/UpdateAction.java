package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.NumericVersion;
import de.idos.updates.UpdateSystem;
import de.idos.updates.Updater;
import de.idos.updates.Version;
import de.idos.updates.configuration.ConfiguredUpdateSystem;
import de.idos.updates.store.ProgressReportAdapter;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.userdialog.DefaultDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.resources.Resources;
import org.apache.commons.io.FilenameUtils;

import javax.swing.Action;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class UpdateAction extends SmartAction {

  public static Action createMenuAction(Resources resources) {
    UpdateAction action = new UpdateAction(resources);
    action.setName(resources.getString("Help.UpdateCheck.Title")); //$NON-NLS-1$
    return action;
  }

  private Resources resources;

  public UpdateAction(Resources resources) {
    this.resources = resources;
  }

  @Override
  protected void execute(Component parentComponent) {
    Version currentVersion = getCurrentVersion();
    UpdateSystem updateSystem = createUpdateSystem(currentVersion);
    Updater updater = updateSystem.checkForUpdates();
    Version installedVersion = updateSystem.getInstalledVersion();
    UpdateDialogPage page = new UpdateDialogPage(resources, currentVersion);
    prepareForInstallation(page, updater);
    DefaultDialogConfiguration dialogConfiguration = DefaultDialogConfiguration.createWithOkOnly(page);
    UserDialog dialog = new UserDialog(parentComponent, dialogConfiguration);
    updateSystem.reportAllProgressTo(new VersionDiscoveryReport(page, installedVersion));
    updateSystem.reportAllProgressTo(new InstallationProgressReport(page));
    updateSystem.reportAllProgressTo(new DialogUpdater(dialog));
    updateSystem.reportAllProgressTo(new ConfigureAnathema(updateSystem));
    dialog.getDialog().setModal(false);
    dialog.show();
    runUpdateCheck(updater);
  }

  private UpdateSystem createUpdateSystem(Version currentVersion) {
      return ConfiguredUpdateSystem.loadProperties().andIfTheInstalledVersionIsUnknownUse(currentVersion).create();
  }

  private void prepareForInstallation(final UpdateDialogPage page, final Updater updater) {
    page.whenUpdateIsRequestedDo(new SmartAction("Install update") {
      @Override
      protected void execute(Component parentComponent) {
        runInThread(new Runnable() {
          @Override
          public void run() {
            page.disableUpdate();
            updater.updateToLatestVersion();
          }
        });
      }
    });
  }

  private void runUpdateCheck(final Updater updater) {
    runInThread(new CheckForUpdate(updater));
  }

  private void runInThread(Runnable target) {
    new Thread(target).start();
  }

  private Version getCurrentVersion() {
    net.sf.anathema.framework.Version version = new net.sf.anathema.framework.Version(resources);//$NON-NLS-1$
    return new NumericVersion(version.getMajorVersion(), version.getMinorVersion(), version.getRevision());
  }

  private static class CheckForUpdate implements Runnable {
    private final Updater updater;

    public CheckForUpdate(Updater updater) {
      this.updater = updater;
    }

    @Override
    public void run() {
      updater.runCheck();
    }
  }

  private static class ConfigureAnathema extends ProgressReportAdapter {
    private final UpdateSystem updateSystem;

    public ConfigureAnathema(UpdateSystem updateSystem) {
      this.updateSystem = updateSystem;
    }

    @Override
    public void finishedInstallation() {
      try {
        File folderForVersionToRun = updateSystem.getFolderForVersionToRun();
        Properties properties = new Properties();
        properties.setProperty("library.folder", FilenameUtils.separatorsToUnix(folderForVersionToRun.getAbsolutePath()));
        new PropertiesSaver("anathema.properties").save(properties);
      } catch (IOException e) {
        //handle exception
      }
    }
  }
}