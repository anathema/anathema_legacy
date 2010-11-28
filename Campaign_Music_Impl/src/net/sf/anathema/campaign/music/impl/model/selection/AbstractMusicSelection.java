package net.sf.anathema.campaign.music.impl.model.selection;

import java.util.Arrays;
import java.util.List;

import net.sf.anathema.campaign.music.model.selection.IMusicSelection;
import net.sf.anathema.campaign.music.model.track.IMp3Track;

public abstract class AbstractMusicSelection implements IMusicSelection {

  protected abstract List<IMp3Track> getContentList();

  public void clear() {
    getContentList().clear();
  }

  public IMp3Track[] getContent() {
    return getContentList().toArray(new IMp3Track[getContentList().size()]);
  }

  public boolean removeTracks(IMp3Track[] tracks) {
    return getContentList().removeAll(Arrays.asList(tracks));
  }

  public boolean addTracks(IMp3Track[] tracks) {
    return getContentList().addAll(Arrays.asList(tracks));
  }

  public void removeTracks(int[] indices) {
    for (int index : indices) {
      getContentList().remove(index);
    }
  }
}
