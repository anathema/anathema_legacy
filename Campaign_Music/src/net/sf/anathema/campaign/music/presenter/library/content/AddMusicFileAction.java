package net.sf.anathema.campaign.music.presenter.library.content;

import java.awt.Component;
import java.io.File;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.campaign.music.model.libary.ILibrary;
import net.sf.anathema.campaign.music.model.libary.ILibraryControl;
import net.sf.anathema.campaign.music.presenter.IMusicSearchControl;
import net.sf.anathema.campaign.music.presenter.util.Mp3FileFilter;
import net.sf.anathema.campaign.music.view.library.ILibraryControlView;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.framework.presenter.DirectoryFileChooser;
import net.sf.anathema.lib.resources.IResources;

public class AddMusicFileAction extends SmartAction {
  private final ILibraryControl model;
  private final ILibraryControlView view;
  private final IMusicSearchControl searchControl;
  private final IResources resources;

  public AddMusicFileAction(
      IResources resources,
      IMusicSearchControl searchControl,
      ILibraryControl model,
      ILibraryControlView view) {
    super(resources.getImageIcon("MP3FileIcon20.png")); //$NON-NLS-1$
    this.resources = resources;
    this.searchControl = searchControl;
    setToolTipText(resources.getString("Music.Actions.AddFile.Tooltip")); //$NON-NLS-1$
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
    final File mp3File = DirectoryFileChooser.chooseSingleFile(
        parentComponent,
        DirectoryFileChooser.ADD_MUSIC_CHOOSER_VALUE,
        resources.getString("Music.Actions.AddFile.FileDialogTitle"), new Mp3FileFilter()); //$NON-NLS-1$
    if (mp3File == null) {
      return;
    }
    String libraryName = ((ILibrary) view.getSelectedLibrary()).getName();
    try {
      model.addTrack(libraryName, mp3File);
      view.getTrackListView().setListItems(searchControl.getTracks(((ILibrary) view.getSelectedLibrary()).getName()));
    }
    catch (Exception e) {
      MessageUtilities.indicateMessage(AddMusicFileAction.class, parentComponent, new Message(
          resources.getString("Errors.MusicDatabase.ReadMusicData"), e)); //$NON-NLS-1$
    }
  }
}