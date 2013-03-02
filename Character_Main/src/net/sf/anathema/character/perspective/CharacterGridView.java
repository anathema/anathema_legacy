package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;

public interface CharacterGridView {

  void addButton(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector);

  void addAndSelectButton(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector);

  void setName(CharacterIdentifier identifier, String name);
}
