package net.sf.anathema.character.main.framework.item;

import net.sf.anathema.framework.repository.ChangeManagement;
import net.sf.anathema.lib.util.Identifier;

public class CharacterItem implements Item {

  public static final String DEFAULT_PRINT_NAME = "Unnamed";
  private final net.sf.anathema.character.main.Character itemData;
  private final ItemRepositoryLocation repositoryLocation;
  private final Identifier identifier;

  public CharacterItem(net.sf.anathema.character.main.Character character) {
    this.repositoryLocation = new HeroRepositoryLocation(character);
    this.itemData = character;
    this.identifier = new Identifier() {
      @Override
      public String getId() {
        return repositoryLocation.getId();
      }
    };
  }

  @Override
  public ItemData getItemData() {
    return itemData;
  }

  @Override
  public ChangeManagement getChangeManagement() {
    return itemData.getChangeManagement();
  }

  @Override
  public final synchronized String getId() {
    return identifier.getId();
  }

  @Override
  public String getDisplayName() {
    return new HeroNameFetcher().getName(itemData);
  }

  @Override
  public ItemRepositoryLocation getRepositoryLocation() {
    return repositoryLocation;
  }

  @Override
  public String toString() {
    return "Character: " + getDisplayName();
  }
}