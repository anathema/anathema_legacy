package net.sf.anathema.campaign.music.impl.persistence;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.campaign.music.impl.persistence.search.IExtendedSearchParameter;
import net.sf.anathema.campaign.music.model.track.IMp3Track;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

public class SearchPersister {

  public IMp3Track[] executeSearch(ObjectContainer db, IExtendedSearchParameter[] parameters) {
    Query query = db.query();
    configureQuery(parameters, query);
    ObjectSet<DbMp3Track> set = query.execute();
    List<DbMp3Track> tracks = new ArrayList<DbMp3Track>();
    while (set.hasNext()) {
      tracks.add(set.next());
    }
    return tracks.toArray(new DbMp3Track[tracks.size()]);
  }

  private void configureQuery(IExtendedSearchParameter[] parameters, Query query) {
    Constraint constraint = query.constrain(DbMp3Track.class);
    for (IExtendedSearchParameter parameter : parameters) {
      Constraint parameterConstraint = parameter.configure(query);
      if (parameterConstraint != null) {
        constraint = constraint.and(parameterConstraint);
      }
    }
  }
}