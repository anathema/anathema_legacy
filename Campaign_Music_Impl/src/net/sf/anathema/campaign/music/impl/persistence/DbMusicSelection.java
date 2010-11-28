package net.sf.anathema.campaign.music.impl.persistence;

import java.util.List;

import net.sf.anathema.campaign.music.impl.model.selection.AbstractMusicSelection;
import net.sf.anathema.campaign.music.model.track.IMp3Track;

import com.db4o.ObjectContainer;
import com.db4o.types.Db4oList;

public class DbMusicSelection extends AbstractMusicSelection {

  private String name;
  private final Db4oList content;

  public DbMusicSelection(String name, ObjectContainer db) {
    this.name = name;
    content = db.ext().collections().newLinkedList();
    content.deleteRemoved(false);
  }

  public void setName(String newName) {
    name = newName;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  @SuppressWarnings("unchecked")
  protected List<IMp3Track> getContentList() {
    return content;
  }
}