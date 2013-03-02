package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;

public interface CharacterGridView {

  void addButton(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector);

  void selectButton(CharacterIdentifier identifier);

  void updateButton(CharacterButtonDto dto);
}
