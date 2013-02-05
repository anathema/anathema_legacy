package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.model.ItemSystemModel;
import net.sf.anathema.framework.repository.IItem;

import java.util.ArrayList;
import java.util.List;

public class CharacterStackPresenter {
  private final List<CharacterIdentifier> knownCharacters = new ArrayList<>();
  private final ItemSystemModel model;
  private final CharacterStackBridge bridge;

  public CharacterStackPresenter(CharacterStackBridge bridge, ItemSystemModel model) {
    this.bridge = bridge;
    this.model = model;
  }

  public void showCharacter(CharacterIdentifier identifier) {
    if (!knownCharacters.contains(identifier)) {
      addExistingCharacter(identifier);
      knownCharacters.add(identifier);
    }
    bridge.showCharacterView(identifier);
    model.setCurrentCharacter(identifier);
  }

  public void addExistingCharacter(CharacterIdentifier identifier) {
    IItem item = model.loadItem(identifier);
    bridge.addViewForExistingCharacter(item);
  }
}
