package net.sf.anathema.scribe.scroll;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.RepositoryConfiguration;
import net.sf.anathema.framework.module.ItemTypeConfiguration;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.SingleFileConfiguration;
import net.sf.anathema.initialization.RegisteredItemTypeConfiguration;

@RegisteredItemTypeConfiguration
public class ScrollItemType implements ItemTypeConfiguration {

  public static final String SCROLL_ITEM_TYPE_ID = "Scroll";
  public static final RepositoryConfiguration REPOSITORY_CONFIGURATION = new SingleFileConfiguration(".scroll", "Scrolls/");
  public static final IItemType ITEM_TYPE = new ItemType(SCROLL_ITEM_TYPE_ID, REPOSITORY_CONFIGURATION);

  @Override
  public IItemType getItemType() {
    return ITEM_TYPE;
  }
}
