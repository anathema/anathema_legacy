package net.sf.anathema.campaign.music.model.selection;

import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.control.IChangeListener;

public interface IMusicSelectionModel {

  void addCurrentSelectionChangeListener(IChangeListener listener);

  void addNewSelection(String unnamedSelectionBase);

  void addSelectionsChangeListener(IChangeListener listener);

  void addToCurrentSelection(IMp3Track[] tracks);

  void clearCurrentSelection();

  void deleteSelection(IMusicSelection selection);

  IMusicSelection getCurrentSelection();

  IMp3Track[] getMarkedTracks();

  IMusicSelection[] getPersistedSelections();

  void persistSelection(IMusicSelection currentSelection);

  void setCurrentSelection(IMusicSelection selection);

  void setMarkedTracks(IMp3Track[] tracks);

  ITrackDetailModel getTrackDetailModel();

  IMp3Track getSelectedTrack();

  void updateSelectionName(IMusicSelection selection, String newName);

  void removeFromCurrentSelection(int[] selectedIndices);
}