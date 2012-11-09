package net.sf.anathema.campaign.music.presenter.library.content;

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
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.progress.ProgressMonitorDialog;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.progress.Cancelable;
import net.sf.anathema.lib.progress.IInterruptibleRunnableWithProgress;
import net.sf.anathema.lib.progress.IProgressMonitor;
import net.sf.anathema.lib.resources.IResources;

import java.awt.Component;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AddMusicFolderAction extends SmartAction {
  public static final String ADD_MUSIC_CHOOSER_VALUE = "AddMusicFolder"; //$NON-NLS-1$
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
    view.whenSelectionChanges(new Runnable() {
      @Override
      public void run() {
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
    Path directory = DirectoryFileChooser.createMusicDirectoryChooser(
            ADD_MUSIC_CHOOSER_VALUE,
            resources.getString("Music.Actions.AddFolder.FileDialogTitle")); //$NON-NLS-1$
    if (directory == null) {
      return;
    }
    final IMusicFolderWalker walker;
    try {
      walker = model.createMusicFolderWalker(directory.toFile());
    } catch (IOException e) {
      throw new UnreachableCodeReachedException();
    }
    try {
      new ProgressMonitorDialog(
              parentComponent,
              resources.getString("Music.Actions.AddFolder.ProgressMonitor.DialogTitle")).run(new IInterruptibleRunnableWithProgress() { //$NON-NLS-1$
        @Override
        public void run(final IProgressMonitor monitor,
                        Cancelable cancelable) throws InterruptedException, InvocationTargetException {
          final List<IMp3Track> foundTracks = new ArrayList<>();
          walker.walk(resources, monitor, cancelable, new ITrackHandler() {
            @Override
            public void handleMp3(IMp3Track mp3Item) {
              foundTracks.add(mp3Item);
              monitor.subTask(resources.getString("Music.Actions.AddFolder.ProgressMonitor.TracksFound") + ": " + foundTracks.size() + "."); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
            }
          });
          String selectedLibraryName = ((ILibrary) view.getSelectedLibrary()).getName();
          model.addTracks(selectedLibraryName, foundTracks);
          view.getTrackListView().setObjects(
                  searchControl.getTracks(((ILibrary) view.getSelectedLibrary()).getName()));
          monitor.done();
        }
      });
    } catch (InterruptedException e) {
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(
              resources.getString("Errors.MusicDatabase.AddFolderCancelled"), //$NON-NLS-1$
              MessageType.INFORMATION));
    } catch (InvocationTargetException e) {
      MessageUtilities.indicateMessage(getClass(), parentComponent, new Message(
              resources.getString("Errors.MusicDatabase.ReadMusicData"), //$NON-NLS-1$
              e));
    }
  }
}