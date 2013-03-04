package net.sf.anathema.scribe.scroll;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;

public class ScrollItemType {

  public static final String SCROLL_ITEM_TYPE_ID = "Scroll";
  public static final RepositoryConfiguration REPOSITORY_CONFIGURATION = new RepositoryConfiguration(".scroll", "Scrolls/");
  public static final IItemType ITEM_TYPE = new ItemType(SCROLL_ITEM_TYPE_ID, REPOSITORY_CONFIGURATION);

}
