package net.sf.anathema.campaign.music.impl.model.selection;

import java.util.Arrays;

import net.sf.anathema.campaign.music.impl.persistence.DbMp3Track;
import net.sf.anathema.campaign.music.impl.persistence.ITrackDeletionListener;
import net.sf.anathema.campaign.music.impl.persistence.MusicDatabasePersister;
import net.sf.anathema.campaign.music.model.selection.IMusicSelection;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.campaign.music.model.selection.ITrackDetailModel;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class MusicSelectionModel implements IMusicSelectionModel {
  private IMusicSelection currentSelection = new MusicSelection();
  private IMp3Track[] markedTracks = new IMp3Track[0];
  private final MusicDatabasePersister persister;
  private final ChangeControl selectionChangeControl = new ChangeControl();
  private final ChangeControl currentSelectionControl = new ChangeControl();
  private final TrackDetailModel trackDetailModel;

  public MusicSelectionModel(final MusicDatabasePersister persister) {
    this.persister = persister;
    this.trackDetailModel = new TrackDetailModel(persister);
    persister.addTrackDeletionListener(new ITrackDeletionListener() {
      @Override
      public void trackRemoved(DbMp3Track track) {
        persister.pruneSelections(track);
        currentSelection.removeTracks(new IMp3Track[] { track });
        fireTrackSelectionChanged();
        selectionChangeControl.fireChangedEvent();
      }
    });
  }

  @Override
  public void addNewSelection(String unnamedSelectionBase) {
    int count = 1;
    IMusicSelection selection = getSelectionByName(unnamedSelectionBase + " " + count); //$NON-NLS-1$
    while (selection != null) {
      count++;
      selection = getSelectionByName(unnamedSelectionBase + " " + count); //$NON-NLS-1$
    }
    persister.addSelection(unnamedSelectionBase + " " + count); //$NON-NLS-1$
    selectionChangeControl.fireChangedEvent();
  }

  @Override
  public void addSelectionsChangeListener(IChangeListener listener) {
    selectionChangeControl.addChangeListener(listener);
  }

  @Override
  public synchronized void addToCurrentSelection(IMp3Track[] tracks) {
    if (currentSelection.addTracks(tracks)) {
      fireTrackSelectionChanged();
    }
  }

  @Override
  public void clearCurrentSelection() {
    currentSelection.clear();
    trackDetailModel.setSelectedTrack(null);
    fireTrackSelectionChanged();
  }

  @Override
  public void deleteSelection(IMusicSelection selection) {
    persister.removeSelection(selection);
    selectionChangeControl.fireChangedEvent();
  }

  private void fireTrackSelectionChanged() {
    currentSelectionControl.fireChangedEvent();
  }

  @Override
  public synchronized IMusicSelection getCurrentSelection() {
    return currentSelection;
  }

  @Override
  public IMp3Track[] getMarkedTracks() {
    return markedTracks;
  }

  @Override
  public IMusicSelection[] getPersistedSelections() {
    return persister.getAllSelections();
  }

  private IMusicSelection getSelectionByName(String name) {
    return persister.getSelectionByName(name);
  }

  @Override
  public void persistSelection(IMusicSelection selection) {
    persister.updateSelection(selection);
    selectionChangeControl.fireChangedEvent();
  }

  @Override
  public void setCurrentSelection(IMusicSelection selection) {
    this.currentSelection = selection;
    fireTrackSelectionChanged();
  }

  @Override
  public void setMarkedTracks(final IMp3Track[] tracks) {
    if (Arrays.equals(tracks, markedTracks)) {
      return;
    }
    this.markedTracks = tracks;
    trackDetailModel.setSelectedTrack(tracks.length > 0 ? tracks[0] : null);
  }

  @Override
  public ITrackDetailModel getTrackDetailModel() {
    return trackDetailModel;
  }

  @Override
  public IMp3Track getSelectedTrack() {
    return markedTracks.length == 0 ? null : markedTracks[0];
  }

  @Override
  public void addCurrentSelectionChangeListener(IChangeListener listener) {
    currentSelectionControl.addChangeListener(listener);
  }

  @Override
  public void updateSelectionName(IMusicSelection selection, String newName) {
    persister.updateSelectionName(selection, newName);
  }

  @Override
  public void removeFromCurrentSelection(int[] selectedIndices) {
    currentSelection.removeTracks(selectedIndices);
    trackDetailModel.setSelectedTrack(markedTracks.length > 0 ? markedTracks[0] : null);
    fireTrackSelectionChanged();
  }
}