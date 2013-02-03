package net.sf.anathema.character.perspective;

import net.sf.anathema.framework.repository.IItem;

import java.util.ArrayList;
import java.util.List;

public class CharacterStackPresenter {
  private final List<String> knownCharacters = new ArrayList<>();
  private final CharacterSystemModel model;
  private final CharacterStackBridge bridge;

  public CharacterStackPresenter(CharacterStackBridge bridge, CharacterSystemModel model) {
    this.bridge = bridge;
    this.model = model;
  }

  public void showCharacter(String repositoryId) {
    if (!knownCharacters.contains(repositoryId)) {
      addCharacter(repositoryId);
      knownCharacters.add(repositoryId);
    }
    bridge.showCharacterView(repositoryId);
  }

  public void addCharacter(String repositoryId) {
    IItem item = model.loadItem(repositoryId);
    bridge.addCharacterView(item);
  }
}
