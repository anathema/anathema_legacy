package net.sf.anathema.campaign.music.impl.model.selection;

import java.util.Arrays;

import net.sf.anathema.campaign.music.impl.persistence.DbMp3Track;
import net.sf.anathema.campaign.music.impl.persistence.ITrackDeletionListener;
import net.sf.anathema.campaign.music.impl.persistence.MusicDatabasePersister;
import net.sf.anathema.campaign.music.model.selection.IMusicSelection;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.campaign.music.model.selection.ITrackDetailModel;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.control.ChangeControl;
import net.sf.anathema.lib.control.IChangeListener;

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
      public void trackRemoved(DbMp3Track track) {
        persister.pruneSelections(track);
        currentSelection.removeTracks(new IMp3Track[] { track });
        fireTrackSelectionChanged();
        selectionChangeControl.fireChangedEvent();
      }
    });
  }

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

  public void addSelectionsChangeListener(IChangeListener listener) {
    selectionChangeControl.addChangeListener(listener);
  }

  public synchronized void addToCurrentSelection(IMp3Track[] tracks) {
    if (currentSelection.addTracks(tracks)) {
      fireTrackSelectionChanged();
    }
  }

  public void clearCurrentSelection() {
    currentSelection.clear();
    trackDetailModel.setSelectedTrack(null);
    fireTrackSelectionChanged();
  }

  public void deleteSelection(IMusicSelection selection) {
    persister.removeSelection(selection);
    selectionChangeControl.fireChangedEvent();
  }

  private void fireTrackSelectionChanged() {
    currentSelectionControl.fireChangedEvent();
  }

  public synchronized IMusicSelection getCurrentSelection() {
    return currentSelection;
  }

  public IMp3Track[] getMarkedTracks() {
    return markedTracks;
  }

  public IMusicSelection[] getPersistedSelections() {
    return persister.getAllSelections();
  }

  private IMusicSelection getSelectionByName(String name) {
    return persister.getSelectionByName(name);
  }

  public void persistSelection(IMusicSelection selection) {
    persister.updateSelection(selection);
    selectionChangeControl.fireChangedEvent();
  }

  public void setCurrentSelection(IMusicSelection selection) {
    this.currentSelection = selection;
    fireTrackSelectionChanged();
  }

  public void setMarkedTracks(final IMp3Track[] tracks) {
    if (Arrays.equals(tracks, markedTracks)) {
      return;
    }
    this.markedTracks = tracks;
    trackDetailModel.setSelectedTrack(tracks.length > 0 ? tracks[0] : null);
  }

  public ITrackDetailModel getTrackDetailModel() {
    return trackDetailModel;
  }

  public IMp3Track getSelectedTrack() {
    return markedTracks.length == 0 ? null : markedTracks[0];
  }

  public void addCurrentSelectionChangeListener(IChangeListener listener) {
    currentSelectionControl.addChangeListener(listener);
  }

  public void updateSelectionName(IMusicSelection selection, String newName) {
    persister.updateSelectionName(selection, newName);
  }

  public void removeFromCurrentSelection(int[] selectedIndices) {
    currentSelection.removeTracks(selectedIndices);
    trackDetailModel.setSelectedTrack(markedTracks.length > 0 ? markedTracks[0] : null);
    fireTrackSelectionChanged();
  }
}