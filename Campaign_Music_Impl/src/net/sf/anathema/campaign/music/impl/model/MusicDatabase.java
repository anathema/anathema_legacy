package net.sf.anathema.campaign.music.impl.model;

import java.io.File;

import net.sf.anathema.campaign.music.impl.model.library.LibraryControl;
import net.sf.anathema.campaign.music.impl.model.player.MusicPlayerModel;
import net.sf.anathema.campaign.music.impl.model.selection.MusicSelectionModel;
import net.sf.anathema.campaign.music.impl.persistence.MusicDatabaseConnectionManager;
import net.sf.anathema.campaign.music.impl.persistence.MusicDatabasePersister;
import net.sf.anathema.campaign.music.model.IMusicDatabase;
import net.sf.anathema.campaign.music.model.libary.ILibraryControl;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.campaign.music.presenter.IMusicSearchControl;
import net.sf.anathema.campaign.music.presenter.selection.player.IMusicPlayerModel;
import net.sf.anathema.framework.itemdata.model.NonPersistableItemData;
import net.sf.anathema.lib.logging.Logger;

import com.db4o.ObjectContainer;

public class MusicDatabase extends NonPersistableItemData implements IMusicDatabase {

  private static final Logger logger = Logger.getLogger(MusicDatabase.class);
  private final IMusicSelectionModel musicSelection;
  private final ILibraryControl libraryControl;
  private IMusicPlayerModel musicPlayerModel;
  private final IMusicSearchControl musicSearchControl;

  public MusicDatabase(File databaseFile) {
    ObjectContainer container = MusicDatabaseConnectionManager.createConnection(databaseFile);
    MusicDatabasePersister persister = new MusicDatabasePersister(container);
    this.libraryControl = new LibraryControl(persister);
    musicSelection = new MusicSelectionModel(persister);
    musicSearchControl = new MusicSearchControl(persister);
    try {
      musicPlayerModel = new MusicPlayerModel();
    }
    catch (ClassNotFoundException e) {
      logger.warn("Music player not installed: " + e.getMessage()); //$NON-NLS-1$
    }
  }

  public ILibraryControl getLibraryControl() {
    return libraryControl;
  }

  public IMusicSelectionModel getMusicSelectionModel() {
    return musicSelection;
  }

  public IMusicPlayerModel getMusicPlayerModel() {
    return musicPlayerModel;
  }

  public IMusicSearchControl getMusicSearchControl() {
    return musicSearchControl;
  }
}