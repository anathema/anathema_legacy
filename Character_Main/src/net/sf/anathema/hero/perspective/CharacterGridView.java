package net.sf.anathema.hero.perspective;

import net.sf.anathema.hero.perspective.model.CharacterIdentifier;

public interface CharacterGridView {

  void addButton(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector);

  void selectButton(CharacterIdentifier identifier);

  void updateButton(CharacterButtonDto dto);
}
