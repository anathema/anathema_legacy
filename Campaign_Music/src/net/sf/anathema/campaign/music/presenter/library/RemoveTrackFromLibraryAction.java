package net.sf.anathema.campaign.music.presenter.library;

import java.awt.Component;

import net.sf.anathema.campaign.music.model.libary.ILibrary;
import net.sf.anathema.campaign.music.model.libary.ILibraryControl;
import net.sf.anathema.campaign.music.presenter.MusicUI;
import net.sf.anathema.campaign.music.presenter.util.AbstractTrackSelectionAction;
import net.sf.anathema.campaign.music.view.library.ILibraryControlView;
import net.sf.anathema.lib.resources.IResources;

public class RemoveTrackFromLibraryAction extends AbstractTrackSelectionAction {

  private final ILibraryControlView controlView;
  private final ILibraryControl libraryModel;

  public RemoveTrackFromLibraryAction(
      IResources resources,
      ILibraryControlView controlView,
      ILibraryControl libraryModel) {
    super(controlView.getTrackListView(), new MusicUI(resources).getRemoveTrackIcon());
    this.controlView = controlView;
    this.libraryModel = libraryModel;
    setToolTipText(resources.getString("Music.Actions.RemoveTrack.Tooltip")); //$NON-NLS-1$
  }

  @Override
  protected void execute(Component parentComponent) {
    libraryModel.removeTracksFromLibrary((ILibrary) controlView.getSelectedLibrary(), getSelectedTracks());
  }
}