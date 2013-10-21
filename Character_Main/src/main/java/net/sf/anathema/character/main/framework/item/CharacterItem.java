package net.sf.anathema.character.main.framework.item;

import net.sf.anathema.framework.repository.ChangeManagement;

public class CharacterItem implements Item {

  public static final String DEFAULT_PRINT_NAME = "Unnamed";
  private final net.sf.anathema.character.main.Character itemData;
  private final ItemRepositoryLocation repositoryLocation;

  public CharacterItem(net.sf.anathema.character.main.Character character) {
    this.repositoryLocation = new HeroRepositoryLocation(character);
    this.itemData = character;
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
  public ItemRepositoryLocation getRepositoryLocation() {
    return repositoryLocation;
  }

  @Override
  public String toString() {
    return "Character: " + repositoryLocation.getId();
  }
}