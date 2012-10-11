package net.sf.anathema.campaign.music.impl.model.selection;

import net.sf.anathema.campaign.music.impl.persistence.DbMp3Track;
import net.sf.anathema.campaign.music.impl.persistence.ITrackDeletionListener;
import net.sf.anathema.campaign.music.impl.persistence.MusicDatabasePersister;
import net.sf.anathema.campaign.music.model.selection.IMusicSelection;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.campaign.music.model.selection.ITrackDetailModel;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.Arrays;

public class MusicSelectionModel implements IMusicSelectionModel {
  private IMusicSelection currentSelection = new MusicSelection();
  private IMp3Track[] markedTracks = new IMp3Track[0];
  private final MusicDatabasePersister persister;
  private final Announcer<IChangeListener> selectionChangeControl = Announcer.to(IChangeListener.class);
  private final Announcer<IChangeListener> currentSelectionControl = Announcer.to(IChangeListener.class);

  private final TrackDetailModel trackDetailModel;

  public MusicSelectionModel(final MusicDatabasePersister persister) {
    this.persister = persister;
    this.trackDetailModel = new TrackDetailModel(persister);
    persister.addTrackDeletionListener(new ITrackDeletionListener() {
      @Override
      public void trackRemoved(DbMp3Track track) {
        persister.pruneSelections(track);
        currentSelection.removeTracks(new IMp3Track[]{track});
        fireTrackSelectionChanged();
        selectionChangeControl.announce().changeOccurred();
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
    selectionChangeControl.announce().changeOccurred();
  }

  @Override
  public void addSelectionsChangeListener(IChangeListener listener) {
    selectionChangeControl.addListener(listener);
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
    selectionChangeControl.announce().changeOccurred();
  }

  private void fireTrackSelectionChanged() {
    currentSelectionControl.announce().changeOccurred();
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
    selectionChangeControl.announce().changeOccurred();
  }

  @Override
  public void setCurrentSelection(IMusicSelection selection) {
    this.currentSelection = selection;
    fireTrackSelectionChanged();
  }

  @Override
  public void setMarkedTracks(IMp3Track[] tracks) {
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
    currentSelectionControl.addListener(listener);
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