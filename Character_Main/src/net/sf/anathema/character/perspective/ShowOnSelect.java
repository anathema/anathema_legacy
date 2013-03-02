package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;

public class ShowOnSelect implements Selector<CharacterIdentifier> {
  private final CharacterStackPresenter characterStack;

  public ShowOnSelect(CharacterStackPresenter characterStack) {
    this.characterStack = characterStack;
  }

  @Override
  public void selected(CharacterIdentifier identifier) {
    characterStack.showCharacter(identifier);
  }
}
