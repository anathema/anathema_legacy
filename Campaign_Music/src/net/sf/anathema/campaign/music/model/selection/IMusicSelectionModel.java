package net.sf.anathema.campaign.music.model.selection;

import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IMusicSelectionModel {

  public void addCurrentSelectionChangeListener(IChangeListener listener);

  public void addNewSelection(String unnamedSelectionBase);

  public void addSelectionsChangeListener(IChangeListener listener);

  public void addToCurrentSelection(IMp3Track[] tracks);

  public void clearCurrentSelection();

  public void deleteSelection(IMusicSelection selection);

  public IMusicSelection getCurrentSelection();

  public IMp3Track[] getMarkedTracks();

  public IMusicSelection[] getPersistedSelections();

  public void persistSelection(IMusicSelection currentSelection);

  public void setCurrentSelection(IMusicSelection selection);

  public void setMarkedTracks(IMp3Track[] tracks);

  public ITrackDetailModel getTrackDetailModel();

  public IMp3Track getSelectedTrack();

  public void updateSelectionName(IMusicSelection selection, String newName);

  public void removeFromCurrentSelection(int[] selectedIndices);
}