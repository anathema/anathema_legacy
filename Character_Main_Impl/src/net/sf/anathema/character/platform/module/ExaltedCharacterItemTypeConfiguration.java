package net.sf.anathema.character.platform.module;

import net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.module.ItemTypeConfiguration;
import net.sf.anathema.framework.repository.ItemType;
import net.sf.anathema.framework.repository.RepositoryConfiguration;
import net.sf.anathema.lib.exception.AnathemaException;

@net.sf.anathema.initialization.ItemTypeConfiguration
public class ExaltedCharacterItemTypeConfiguration implements ItemTypeConfiguration {

  private final IItemType type;

  public ExaltedCharacterItemTypeConfiguration() throws AnathemaException {
    this.type = new ItemType(CharacterItemTypeRetrieval.CHARACTER_ITEM_TYPE_ID, new RepositoryConfiguration(".ecg", "ExaltedCharacter/", "main"));
  }

  @Override
  public final IItemType getItemType() {
    return type;
  }
}