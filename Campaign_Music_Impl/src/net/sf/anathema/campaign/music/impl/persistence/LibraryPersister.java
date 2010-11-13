package net.sf.anathema.campaign.music.impl.persistence;

import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.campaign.music.model.libary.ILibrary;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.model.track.Md5Checksum;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class LibraryPersister {

  private final GenericControl<ITrackDeletionListener> listeners = new GenericControl<ITrackDeletionListener>();

  public final void addLibrary(final ObjectContainer db, String libraryName) {
    Ensure.ensureArgumentFalse("Library name must be unique.", isRegisteredLibrary(db, libraryName)); //$NON-NLS-1$
    db.set(new DbLibrary(libraryName, db));
  }

  public boolean isRegisteredLibrary(ObjectContainer db, String name) {
    ObjectSet<DbLibrary> set = getLibraryObjectSet(db, name);
    return set.size() > 0;
  }

  private ObjectSet<DbLibrary> getLibraryObjectSet(ObjectContainer db, final String name) {
    ObjectSet<DbLibrary> results = db.query(new Predicate<DbLibrary>() {
      @Override
      public boolean match(DbLibrary candidate) {
        return candidate.getName().equals(name);
      }
    });
    return results;
  }

  public DbLibrary getLibrary(ObjectContainer db, String name) {
    ObjectSet<DbLibrary> set = getLibraryObjectSet(db, name);
    return set.hasNext() ? set.next() : null;
  }

  public DbLibrary[] getAllLibraries(ObjectContainer db) {
    ObjectSet<DbLibrary> results = db.query(DbLibrary.class);
    return results.toArray(new DbLibrary[results.size()]);
  }

  public void addTracks(ObjectContainer db, String name, List<IMp3Track> tracksToAdd) {
    ObjectSet<DbLibrary> set = getLibraryObjectSet(db, name);
    DbLibrary dbLibrary = set.next();
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

  private DbMp3Track findTrack(ObjectContainer db, final Md5Checksum checkSum) {
    ObjectSet<DbMp3Track> results = db.query(new Predicate<DbMp3Track>() {
      @Override
      public boolean match(DbMp3Track candidate) {
        return candidate.getCheckSum().equals(checkSum);
      }
    });
    return results.hasNext() ? results.next() : null;
  }

  public void updateLibraryName(ObjectContainer db, ILibrary library, String newName) {
    DbLibrary storedLibrary = getLibrary(db, library.getName());
    storedLibrary.setName(newName);
    db.set(storedLibrary);
  }

  public void removeLibrary(ObjectContainer db, String name) {
    ObjectSet<DbLibrary> libraryObjectSet = getLibraryObjectSet(db, name);
    DbLibrary library = libraryObjectSet.next();
    for (IMp3Track track : library.getMp3Items()) {
      decreaseReferenceCount(db, (DbMp3Track) track);
    }
    library.removeAllTracks();
    db.delete(library);
  }

  public void removeTrackFromLibrary(ObjectContainer db, String name, IMp3Track track) {
    ObjectSet<DbLibrary> libraryObjectSet = getLibraryObjectSet(db, name);
    DbLibrary library = libraryObjectSet.next();
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

  private void fireTrackDeleted(final DbMp3Track track) {
    listeners.forAllDo(new IClosure<ITrackDeletionListener>() {
      public void execute(ITrackDeletionListener input) {
        input.trackRemoved(track);
      }
    });
  }

  public void addTrackDeletionListener(ITrackDeletionListener listener) {
    listeners.addListener(listener);
  }
}