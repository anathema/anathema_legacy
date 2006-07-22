package net.sf.anathema.campaign.music.impl.module;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.Action;

import net.disy.commons.core.message.Message;
import net.disy.commons.core.progress.IProgressMonitor;
import net.disy.commons.core.progress.IRunnableWithProgress;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.message.MessageDialogFactory;
import net.disy.commons.swing.dialog.progress.ProgressMonitorDialog;
import net.sf.anathema.campaign.music.impl.model.MusicDatabase;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.presenter.IItemManagementModelListener;
import net.sf.anathema.framework.repository.AnathemaItem;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

import org.apache.commons.io.FileUtils;

import com.db4o.ext.DatabaseFileLockedException;

public class StartDatabaseAction extends SmartAction {

  private static final String MUSIC_DATABASE_ITEM_ID = "MusicDatabase.Item"; //$NON-NLS-1$
  private final IAnathemaModel anathemaModel;
  private final IResources resources;

  public StartDatabaseAction(IAnathemaModel anathemaModel, IResources resources) {
    this.anathemaModel = anathemaModel;
    this.resources = resources;
    anathemaModel.getItemManagement().addListener(new IItemManagementModelListener() {
      public void itemAdded(IItem item) throws AnathemaException {
        if (isMusicDatabaseItem(item)) {
          StartDatabaseAction.this.setEnabled(false);
        }
      }

      public void itemSelected(IItem item) {
        // Nothing to do
      }

      public void itemRemoved(IItem item) {
        if (isMusicDatabaseItem(item)) {
          StartDatabaseAction.this.setEnabled(true);
        }
      }
    });
  }

  private boolean isMusicDatabaseItem(IItem item) {
    return item.getItemType().getId().equals(MusicDatabaseItemTypeConfiguration.MUSIC_DATABASE_ITEM_TYPE_ID);
  }

  public static Action createMenuAction(IResources resources, IAnathemaModel anathemaModel) {
    StartDatabaseAction startDatabaseAction = new StartDatabaseAction(anathemaModel, resources);
    startDatabaseAction.setName(resources.getString("MusicDatabase.NewAction.Name")); //$NON-NLS-1$
    return startDatabaseAction;
  }

  public static Action createToolAction(IResources resources, IAnathemaModel anathemaModel) {
    SmartAction action = new StartDatabaseAction(anathemaModel, resources);
    action.setIcon(resources.getImageIcon("TaskBarMusic24.png")); //$NON-NLS-1$
    action.setToolTipText(resources.getString("MusicDatabase.NewAction.Tooltip")); //$NON-NLS-1$
    return action;
  }

  @Override
  protected void execute(final Component parentComponent) {
    try {
      String title = resources.getString("Music.DatabaseStart.Progress.Title"); //$NON-NLS-1$
      new ProgressMonitorDialog(parentComponent, title).run(false, new IRunnableWithProgress() {
        public void run(IProgressMonitor monitor) throws InterruptedException, InvocationTargetException {
          try {
            monitor.beginTask(resources.getString("Music.DatabaseStart.Progress.Filesystem"), IProgressMonitor.UNKNOWN); //$NON-NLS-1$
            File musicDirectory = getMusicFolder();
            monitor.beginTask(
                resources.getString("Music.DatabaseStart.Progress.Presentation"), IProgressMonitor.UNKNOWN); //$NON-NLS-1$
            IItemType itemType = anathemaModel.getItemTypeRegistry().getById(
                MusicDatabaseItemTypeConfiguration.MUSIC_DATABASE_ITEM_TYPE_ID);
            MusicDatabase musicDatabase = new MusicDatabase(new File(musicDirectory, "musicdatabase.yap")); //$NON-NLS-1$
            IItem anathemaItem = new AnathemaItem(itemType, new Identificate(MUSIC_DATABASE_ITEM_ID), musicDatabase);
            anathemaModel.getItemManagement().addItem(anathemaItem);
          }
          catch (DatabaseFileLockedException e) {
            throw new InvocationTargetException(e);
          }
          catch (IOException e) {
            throw new InvocationTargetException(e);
          }
          catch (AnathemaException e) {
            throw new InvocationTargetException(e);
          }
        }
      });
    }
    catch (InvocationTargetException exception) {
      String text = resources.getString("Errors.MusicDatabase.Launch"); //$NON-NLS-1$
      if (exception.getCause() instanceof DatabaseFileLockedException) {
        text = resources.getString("Errors.MusicDatabase.FileLocked"); //$NON-NLS-1$
      }
      if (exception.getCause() instanceof IOException) {
        text = resources.getString("Errors.MusicDatabase.CreatingRepositorySubDirectory"); //$NON-NLS-1$
      }
      MessageDialogFactory.showMessageDialog(parentComponent, new Message(text, exception));
    }
    catch (Throwable e) {
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(
          resources.getString("Errors.MusicDatabase.Launch"), e)); //$NON-NLS-1$
    }
  }

  private File getMusicFolder() throws IOException {
    File repositoryFolder = anathemaModel.getRepository().getRepositoryFolder();
    File musicDirectory = new File(repositoryFolder, "music"); //$NON-NLS-1$
    if (!musicDirectory.exists()) {
      FileUtils.forceMkdir(musicDirectory);
    }
    return musicDirectory;
  }
}