package net.sf.anathema.campaign.music.impl.persistence;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.campaign.music.model.libary.ILibrary;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.model.track.Md5Checksum;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class LibraryPersister {

  private static final String LIBRARY_FIELD_NAME = "name"; //$NON-NLS-1$
  private static final String TRACK_FIELD_CHECKSUM = "checksum"; //$NON-NLS-1$
  private final List<ITrackDeletionListener> listeners = new ArrayList<ITrackDeletionListener>();

  public final void addLibrary(final ObjectContainer db, String libraryName) {
    Ensure.ensureArgumentFalse("Library name must be unique.", isRegisteredLibrary(db, libraryName)); //$NON-NLS-1$
    db.set(new DbLibrary(libraryName, db));
  }

  public boolean isRegisteredLibrary(ObjectContainer db, String name) {
    ObjectSet set = getLibraryObjectSet(db, name);
    return set.size() > 0;
  }

  private ObjectSet getLibraryObjectSet(ObjectContainer db, String name) {
    Query query = db.query();
    query.constrain(DbLibrary.class);
    query.descend(LIBRARY_FIELD_NAME).constrain(name);
    ObjectSet set = query.execute();
    return set;
  }

  public DbLibrary getLibrary(ObjectContainer db, String name) {
    ObjectSet set = getLibraryObjectSet(db, name);
    return (DbLibrary) (set.size() == 0 ? null : set.next());
  }

  public DbLibrary[] getAllLibraries(ObjectContainer db) {
    Query query = db.query();
    query.constrain(DbLibrary.class);
    ObjectSet set = query.execute();
    List<DbLibrary> libraries = new ArrayList<DbLibrary>();
    while (set.hasNext()) {
      libraries.add((DbLibrary) set.next());
    }
    return libraries.toArray(new DbLibrary[libraries.size()]);
  }

  public void addTracks(ObjectContainer db, String name, List<IMp3Track> tracksToAdd) {
    ObjectSet set = getLibraryObjectSet(db, name);
    DbLibrary dbLibrary = (DbLibrary) set.next();
    for (IMp3Track track : tracksToAdd) {
      DbMp3Track dbTrack = findTrack(db, track.getCheckSum());
      if (dbTrack == null) {
        dbTrack = new DbMp3Track(track, db);
      }
      else {
        for (String fileReference : track.getFileReferences()) {
          dbTrack.addFileReference(fileReference);
        }
      }
      dbLibrary.addTrack(dbTrack);
      dbTrack.increaseLibraryReferenceCount();
    }
    db.set(dbLibrary);
  }

  private DbMp3Track findTrack(ObjectContainer db, Md5Checksum checkSum) {
    Query query = db.query();
    query.constrain(DbMp3Track.class);
    query.descend(TRACK_FIELD_CHECKSUM).constrain(checkSum);
    ObjectSet set = query.execute();
    return set.hasNext() ? (DbMp3Track) set.next() : null;
  }

  public void updateLibraryName(ObjectContainer db, ILibrary library, String newName) {
    DbLibrary storedLibrary = getLibrary(db, library.getName());
    storedLibrary.setName(newName);
    db.set(storedLibrary);
  }

  public void removeLibrary(ObjectContainer db, String name) {
    ObjectSet libraryObjectSet = getLibraryObjectSet(db, name);
    DbLibrary library = (DbLibrary) libraryObjectSet.next();
    for (IMp3Track track : library.getMp3Items()) {
      decreaseReferenceCount(db, (DbMp3Track) track);
    }
    library.removeAllTracks();
    db.delete(library);
  }

  public void removeTrackFromLibrary(ObjectContainer db, String name, IMp3Track track) {
    ObjectSet libraryObjectSet = getLibraryObjectSet(db, name);
    DbLibrary library = (DbLibrary) libraryObjectSet.next();
    library.removeTrack(track);
    decreaseReferenceCount(db, (DbMp3Track) track);
    db.set(library);
  }

  private void decreaseReferenceCount(ObjectContainer db, DbMp3Track track) {
    track.decreaseLibraryReferenceCount();
    if (track.getLibraryReferenceCount() == 0) {
      db.delete(track);
      fireTrackDeleted(track);
    }
    else {
      db.set(track);
    }
  }

  private synchronized void fireTrackDeleted(DbMp3Track track) {
    List<ITrackDeletionListener> cloneList = new ArrayList<ITrackDeletionListener>(listeners);
    for (ITrackDeletionListener listener : cloneList) {
      listener.trackRemoved(track);
    }
  }

  public synchronized void addTrackDeletionListener(ITrackDeletionListener listener) {
    listeners.add(listener);
  }
}