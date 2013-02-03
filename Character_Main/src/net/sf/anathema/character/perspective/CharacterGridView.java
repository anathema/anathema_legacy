package net.sf.anathema.character.perspective;

import net.sf.anathema.character.perspective.model.CharacterIdentifier;

import java.util.List;

public interface CharacterGridView {

  void addButtons(List<CharacterButtonDto> dtoList, Selector<CharacterIdentifier> characterSelector);
}
