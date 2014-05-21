package net.sf.anathema.character.framework.itemtype;

import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.item.RepositoryConfiguration;
import net.sf.anathema.framework.module.ItemTypeConfiguration;
import net.sf.anathema.framework.repository.FolderRepositoryConfiguration;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.initialization.RegisteredItemTypeConfiguration;

@RegisteredItemTypeConfiguration
@Weight(weight=10)
public class CharacterItemType implements ItemTypeConfiguration {

  public static final String CHARACTER_ITEM_TYPE_ID = "ExaltedCharacter";
  private static final RepositoryConfiguration REPOSITORY_CONFIGURATION = new FolderRepositoryConfiguration(".ecg", "ExaltedCharacter/", "main");
  private static final IItemType ITEM_TYPE = new ItemType(CHARACTER_ITEM_TYPE_ID, REPOSITORY_CONFIGURATION);

  @Override
  public final IItemType getItemType() {
    return ITEM_TYPE;
  }
}