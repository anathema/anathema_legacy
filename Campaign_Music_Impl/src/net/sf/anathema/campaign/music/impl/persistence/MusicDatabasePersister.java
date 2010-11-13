package net.sf.anathema.campaign.music.impl.persistence;

import java.util.List;

import net.sf.anathema.campaign.music.impl.persistence.search.IExtendedSearchParameter;
import net.sf.anathema.campaign.music.model.libary.ILibrary;
import net.sf.anathema.campaign.music.model.selection.IMusicSelection;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;

import com.db4o.ObjectContainer;

public class MusicDatabasePersister {

  private final LibraryPersister libaryPersister = new LibraryPersister();
  private final SelectionPersister selectionPersister = new SelectionPersister();
  private final SearchPersister searchPersister = new SearchPersister();
  private final ObjectContainer db;
  private final GenericControl<ITrackDeletionListener> listeners = new GenericControl<ITrackDeletionListener>();

  public MusicDatabasePersister(final ObjectContainer container) {
    db = container;
    libaryPersister.addTrackDeletionListener(new ITrackDeletionListener() {
      public void trackRemoved(DbMp3Track track) {
        fireTrackDeleted(track);
      }
    });
  }

  public void pruneSelections(DbMp3Track track) {
    DbMusicSelection[] allSelections = selectionPersister.getAllSelections(db);
    for (DbMusicSelection selection : allSelections) {
      selection.removeTracks(new IMp3Track[] { track });
      selectionPersister.updateSelection(db, selection);
    }
    db.commit();
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

  public void addLibrary(String libraryName) {
    libaryPersister.addLibrary(db, libraryName);
    db.commit();
  }

  public ILibrary[] getAllLibraries() {
    return libaryPersister.getAllLibraries(db);
  }

  public boolean isRegisteredLibrary(String name) {
    return libaryPersister.isRegisteredLibrary(db, name);
  }

  public IMp3Track[] getTracksFromLibrary(final String name) {
    DbLibrary library = libaryPersister.getLibrary(db, name);
    if (library == null) {
      return new IMp3Track[0];
    }
    return library.getMp3Items();
  }

  public void addTracks(String name, List<IMp3Track> tracks) {
    libaryPersister.addTracks(db, name, tracks);
    db.commit();
  }

  public void updateLibaryName(ILibrary library, String newName) {
    libaryPersister.updateLibraryName(db, library, newName);
    db.commit();
  }

  public void removeLibrary(String name) {
    libaryPersister.removeLibrary(db, name);
    db.commit();
  }

  public void removeTracksFromLibrary(String name, IMp3Track[] tracks) {
    for (IMp3Track track : tracks) {
      libaryPersister.removeTrackFromLibrary(db, name, track);
    }
    db.commit();
  }

  public void addSelection(String string) {
    selectionPersister.updateSelection(db, new DbMusicSelection(string, db));
    db.commit();
  }

  public IMusicSelection[] getAllSelections() {
    return selectionPersister.getAllSelections(db);
  }

  public DbMusicSelection getSelectionByName(String name) {
    return selectionPersister.getSelectionByName(db, name);
  }

  public void removeSelection(IMusicSelection selection) {
    if (selection instanceof DbMusicSelection) {
      selectionPersister.removeSelection(db, (DbMusicSelection) selection);
    }
    else {
      throw new IllegalArgumentException("Selection must be of type DbMusicSelection"); //$NON-NLS-1$
    }
    db.commit();
  }

  public void updateTrackInfo(IMp3Track selectedTrack) {
    if (selectedTrack instanceof DbMp3Track) {
      selectionPersister.updateTrackInfo(db, (DbMp3Track) selectedTrack);
    }
    else {
      throw new IllegalArgumentException("Track must be of type DbMp3Track"); //$NON-NLS-1$
    }
    db.commit();
  }

  public IMp3Track[] executeSearch(IExtendedSearchParameter[] parameters) {
    return searchPersister.executeSearch(db, parameters);
  }

  public <C> List<C> getAllObjects(Class<C> componentType) {
    return db.query(componentType);
  }

  public <C> void setSimpleObject(C object) {
    db.set(object);
  }

  public void updateSelectionName(IMusicSelection selection, String newName) {
    DbMusicSelection dbSelection = (DbMusicSelection) selection;
    dbSelection.setName(newName);
    selectionPersister.updateSelection(db, dbSelection);
    db.commit();
  }

  public void updateSelection(IMusicSelection selection) {
    selectionPersister.updateSelection(db, (DbMusicSelection) selection);
  }
}