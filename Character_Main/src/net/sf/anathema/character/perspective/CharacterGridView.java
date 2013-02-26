package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;

import java.util.Collection;

public interface CharacterGridView {

  void addButtons(Collection<CharacterButtonDto> dtoList, Selector<CharacterIdentifier> characterSelector);

  void addAndSelectButton(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector);
}
