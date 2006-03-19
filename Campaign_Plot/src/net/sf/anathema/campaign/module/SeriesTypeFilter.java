package net.sf.anathema.campaign.module;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.collection.IFilter;

public class SeriesTypeFilter implements IFilter<IItemType> {

  public boolean accept(IItemType object) {
    return !object.getId().equals(SeriesTypeConfiguration.SERIES_ITEM_TYPE_ID);
  }
}