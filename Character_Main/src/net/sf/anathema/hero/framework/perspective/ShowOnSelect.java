package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;

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
