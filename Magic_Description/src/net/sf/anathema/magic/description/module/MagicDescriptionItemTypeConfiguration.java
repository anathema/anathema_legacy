package net.sf.anathema.magic.description.module;

import net.sf.anathema.framework.module.AbstractNonPersistableItemTypeConfiguration;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.initialization.ItemTypeConfiguration;

@ItemTypeConfiguration
public class MagicDescriptionItemTypeConfiguration extends AbstractNonPersistableItemTypeConfiguration {

  public static final String MAGIC_DESCRIPTION_ITEM_TYPE_ID = "MagicDescription";
  private static final RepositoryConfiguration REPOSITORY_CONFIGURATION = new RepositoryConfiguration(".mdsc", "MagicDescription/");

  public MagicDescriptionItemTypeConfiguration() {
    super(new ItemType(MAGIC_DESCRIPTION_ITEM_TYPE_ID, REPOSITORY_CONFIGURATION, false));
  }
}