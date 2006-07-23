package net.sf.anathema.campaign.music.presenter.library.content;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.disy.commons.core.message.Message;
import net.disy.commons.core.message.MessageType;
import net.disy.commons.core.progress.IProgressMonitor;
import net.disy.commons.core.progress.IRunnableWithProgress;
import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.dialog.progress.ProgressMonitorDialog;
import net.sf.anathema.campaign.music.model.libary.ILibrary;
import net.sf.anathema.campaign.music.model.libary.ILibraryControl;
import net.sf.anathema.campaign.music.model.libary.IMusicFolderWalker;
import net.sf.anathema.campaign.music.model.libary.ITrackHandler;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.presenter.IMusicSearchControl;
import net.sf.anathema.campaign.music.presenter.MusicUI;
import net.sf.anathema.campaign.music.view.library.ILibraryControlView;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.presenter.DirectoryFileChooser;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.resources.IResources;

public class AddMusicFolderAction extends SmartAction {
  private static final String ADD_MUSIC_FOLDER_DIRECTORY_CHOOSER_VALUE = "AddMusicFolder"; //$NON-NLS-1$
  private final ILibraryControl model;
  private final ILibraryControlView view;
  private final IMusicSearchControl searchControl;
  private final IResources resources;

  public AddMusicFolderAction(
      IResources resources,
      IMusicSearchControl searchControl,
      ILibraryControl model,
      ILibraryControlView view) {
    super(new MusicUI(resources).getAddFolderIcon());
    this.resources = resources;
    this.searchControl = searchControl;
    setToolTipText(resources.getString("Music.Actions.AddFolder.Tooltip")); //$NON-NLS-1$
    this.model = model;
    this.view = view;
    view.addLibraryListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        updateEnabled();
      }
    });
    updateEnabled();
  }

  private void updateEnabled() {
    setEnabled(view.getSelectedLibrary() != null);
  }

  @Override
  protected void execute(Component parentComponent) {
    final File directory = DirectoryFileChooser.createDirectoryChooser(
        ADD_MUSIC_FOLDER_DIRECTORY_CHOOSER_VALUE,
        resources.getString("Music.Actions.AddFolder.FileDialogTitle")); //$NON-NLS-1$
    if (directory == null) {
      return;
    }
    final IMusicFolderWalker walker;
    try {
      walker = model.createMusicFolderWalker(directory);
    }
    catch (IOException e) {
      throw new UnreachableCodeReachedException();
    }
    try {
      new ProgressMonitorDialog(
          parentComponent,
          resources.getString("Music.Actions.AddFolder.ProgressMonitor.DialogTitle")).run(true, new IRunnableWithProgress() { //$NON-NLS-1$
            public void run(final IProgressMonitor monitor) throws InterruptedException, InvocationTargetException {
              final List<IMp3Track> foundTracks = new ArrayList<IMp3Track>();
              walker.walk(resources, monitor, new ITrackHandler() {
                public void handleMp3(IMp3Track mp3Item) {
                  foundTracks.add(mp3Item);
                  monitor.subTask(resources.getString("Music.Actions.AddFolder.ProgressMonitor.TracksFound") + ": " + foundTracks.size() + "."); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
                }
              });
              String selectedLibraryName = ((ILibrary) view.getSelectedLibrary()).getName();
              model.addTracks(selectedLibraryName, foundTracks);
              view.getTrackListView().setListItems(
                  searchControl.getTracks(((ILibrary) view.getSelectedLibrary()).getName()));
              monitor.done();
            }
          });
    }
    catch (InterruptedException e) {
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(
          resources.getString("Errors.MusicDatabase.AddFolderCancelled"), //$NON-NLS-1$
          MessageType.INFORMATION));
    }
    catch (InvocationTargetException e) {
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(
          resources.getString("Errors.MusicDatabase.ReadMusicData"), //$NON-NLS-1$
          e));
    }
  }
}