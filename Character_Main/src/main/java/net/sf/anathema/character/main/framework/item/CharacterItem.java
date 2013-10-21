package net.sf.anathema.character.main.framework.item;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import net.sf.anathema.framework.repository.ChangeManagement;
import net.sf.anathema.lib.util.Identifier;

public class CharacterItem implements Item {

  private static final String DEFAULT_PRINT_NAME = "Unnamed";
  private final ItemData itemData;
  private final ItemRepositoryLocation repositoryLocation;
  private final Identifier identifier;
  private String printName = "";

  public CharacterItem(net.sf.anathema.character.main.Character character) {
    this.repositoryLocation = new HeroRepositoryLocation(character);
    this.itemData = character;
    this.identifier = new Identifier() {
      @Override
      public String getId() {
        return repositoryLocation.getId();
      }
    };
    character.setPrintNameAdjuster(new PrintNameAdjuster(this));
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
    return Strings.isNullOrEmpty(printName) ? DEFAULT_PRINT_NAME : printName;
  }

  @Override
  public void setPrintName(String printName) {
    if (Objects.equal(this.printName, printName)) {
      return;
    }
    this.printName = printName;
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