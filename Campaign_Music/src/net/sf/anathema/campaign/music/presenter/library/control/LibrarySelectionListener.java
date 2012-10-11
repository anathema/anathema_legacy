package net.sf.anathema.campaign.music.presenter.library.control;

import net.sf.anathema.campaign.music.model.libary.ILibrary;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.presenter.IMusicSearchControl;
import net.sf.anathema.campaign.music.view.library.ILibraryControlView;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;
import net.sf.anathema.lib.resources.IResources;

public final class LibrarySelectionListener implements Runnable {
  private final ILibraryControlView view;
  private final IMusicSearchControl searchControl;
  private final IResources resources;

  public LibrarySelectionListener(ILibraryControlView view, IMusicSearchControl searchControl, IResources resources) {
    this.view = view;
    this.searchControl = searchControl;
    this.resources = resources;
  }

  @Override
  public void run() {
    ILibrary library = (ILibrary) view.getSelectedLibrary();
    updateTracks(library);
  }

  private void updateTracks(ILibrary selectedLibrary) {
    IActionAddableListView<IMp3Track> trackListView = view.getTrackListView();
    if (selectedLibrary == null) {
      trackListView.setObjects(new IMp3Track[0]);
      trackListView.setListTitle(resources.getString("Music.Labels.LibraryTrackView.NoContentTitle")); //$NON-NLS-1$
    } else {
      IMp3Track[] tracks = searchControl.getTracks(selectedLibrary.getName());
      trackListView.setObjects(tracks);
      trackListView.setListTitle(resources.getString("Music.Labels.LibraryTrackView.ContentTitleSnippet") + " \"" + selectedLibrary.getName() + "\":"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
    }
  }
}