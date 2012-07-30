package net.sf.anathema.framework.presenter.action.menu.help.updatecheck;

import de.idos.updates.NumericVersion;
import de.idos.updates.UpdateSystem;
import de.idos.updates.Updater;
import de.idos.updates.Version;
import de.idos.updates.configuration.ConfiguredUpdateSystem;
import de.idos.updates.store.ProgressReportAdapter;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.resources.IResources;
import org.apache.commons.io.FilenameUtils;

import javax.swing.Action;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class AnathemaUpdateAction extends SmartAction {

  public static final boolean AUTO_UPDATE_ENABLED = false;

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
    final UpdateSystem updateSystem;
    if (AUTO_UPDATE_ENABLED) {
      updateSystem = ConfiguredUpdateSystem.loadProperties().andIfTheInstalledVersionIsUnknownUse(currentVersion).create();
    } else {
      updateSystem = ConfiguredUpdateSystem.loadProperties().butDiscoverAvailableVersionThrough(new TagsOnGithub()).andIfTheInstalledVersionIsUnknownUse(currentVersion).create();
    }
    final Updater updater = updateSystem.checkForUpdates();
    Version installedVersion = updateSystem.getInstalledVersion();
    UpdateDialogPage page = new UpdateDialogPage(resources, installedVersion);
    prepareForInstallation(page, updater);
    UserDialog dialog = new UserDialog(parentComponent, page);
    updateSystem.reportAllProgressTo(new UiProgressReport(page, installedVersion));
    updateSystem.reportAllProgressTo(new DialogUpdater(dialog));
    updateSystem.reportAllProgressTo(new ConfigureAnathema(updateSystem));
    dialog.getDialog().setModal(false);
    dialog.show();
    runUpdateCheck(updater);
  }

  private void prepareForInstallation(UpdateDialogPage page, final Updater updater) {
    page.whenUpdateIsRequestedDo(new SmartAction() {
      @Override
      protected void execute(Component parentComponent) {
        runInThread(new Runnable() {
          @Override
          public void run() {
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