package net.sf.anathema.campaign.music.impl.persistence;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SelectionPersister {

  public void updateSelection(ObjectContainer db, DbMusicSelection selection) {
    db.set(selection);
  }

  public DbMusicSelection[] getAllSelections(ObjectContainer db) {
    Query query = db.query();
    query.constrain(DbMusicSelection.class);
    @SuppressWarnings("unchecked")
    ObjectSet<DbMusicSelection> set = query.execute();
    List<DbMusicSelection> selections = new ArrayList<>();
    while (set.hasNext()) {
      selections.add(set.next());
    }
    return selections.toArray(new DbMusicSelection[selections.size()]);
  }

  public DbMusicSelection getSelectionByName(ObjectContainer db, String string) {
    Query query = db.query();
    query.constrain(DbMusicSelection.class);
    query.descend("name").constrain(string); //$NON-NLS-1$
    @SuppressWarnings("unchecked")
    ObjectSet<DbMusicSelection> set = query.execute();
    if (set.isEmpty()){
      return null;
    }
    return set.next();
  }

  public void removeSelection(ObjectContainer db, DbMusicSelection selection) {
    db.delete(selection);
  }

  public void updateTrackInfo(ObjectContainer db, DbMp3Track track) {
    db.set(track);
  }
}