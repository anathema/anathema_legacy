package net.sf.anathema.campaign.music.impl.persistence;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.campaign.music.model.libary.ILibrary;
import net.sf.anathema.campaign.music.model.track.IMp3Track;

import com.db4o.ObjectContainer;
import com.db4o.types.Db4oList;

public final class DbLibrary implements ILibrary {

  private final Db4oList content;
  private String name;

  public DbLibrary(String name, ObjectContainer db) {
    Ensure.ensureNotNull(name);
    this.name = name;
    this.content = db.ext().collections().newLinkedList();
    this.content.deleteRemoved(false);
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }

  @SuppressWarnings("unchecked")
  public IMp3Track[] getMp3Items() {
    return (IMp3Track[]) content.toArray(new IMp3Track[content.size()]);
  }

  @SuppressWarnings("unchecked")
  public synchronized void addTrack(DbMp3Track item) {
    for (IMp3Track track : getMp3Items()) {
      if (track.getCheckSum().equals(item.getCheckSum())) {
        return;
      }
    }
    content.add(item);
  }

  public void setName(String name) {
    this.name = name;
  }

  public void removeTrack(IMp3Track track) {
    content.remove(track);
  }

  public void removeAllTracks() {
    content.clear();
  }
}