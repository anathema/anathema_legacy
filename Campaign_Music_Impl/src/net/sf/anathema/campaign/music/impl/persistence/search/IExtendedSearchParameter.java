package net.sf.anathema.campaign.music.impl.persistence.search;

import com.db4o.query.Constraint;
import com.db4o.query.Query;

public interface IExtendedSearchParameter {

  Constraint configure(Query query);
}