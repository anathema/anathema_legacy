package net.sf.anathema.character.main.framework.item;

public class CharacterItem implements Item {

  public static final String DEFAULT_PRINT_NAME = "Unnamed";
  private final ItemData itemData;
  private final ItemRepositoryLocation repositoryLocation = new SimpleRepositoryLocation();

  public CharacterItem(net.sf.anathema.character.main.Character character) {
    this.itemData = character;
  }

  @Override
  public ItemData getItemData() {
    return itemData;
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