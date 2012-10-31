package net.sf.anathema.campaign.music.impl.model.selection;

import net.sf.anathema.campaign.music.model.track.IMp3Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for the current music selection.
 */
public class MusicSelection extends AbstractMusicSelection {
  private final List<IMp3Track> content = new ArrayList<>();

  @Override
  protected List<IMp3Track> getContentList() {
    return content;
  }
}