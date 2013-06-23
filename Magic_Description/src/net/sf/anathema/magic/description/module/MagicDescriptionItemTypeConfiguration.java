package net.sf.anathema.magic.description.module;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.initialization.ItemTypeConfiguration;

@ItemTypeConfiguration
public class MagicDescriptionItemTypeConfiguration implements net.sf.anathema.framework.module.ItemTypeConfiguration {

  public static final String MAGIC_DESCRIPTION_ITEM_TYPE_ID = "MagicDescription";
  private static final RepositoryConfiguration REPOSITORY_CONFIGURATION = new RepositoryConfiguration(".mdsc", "MagicDescription/");
  private IItemType type;

  public MagicDescriptionItemTypeConfiguration() {
    this.type = new ItemType(MAGIC_DESCRIPTION_ITEM_TYPE_ID, REPOSITORY_CONFIGURATION, false);
  }

  @Override
  public IItemType getItemType() {
    return type;
  }
}