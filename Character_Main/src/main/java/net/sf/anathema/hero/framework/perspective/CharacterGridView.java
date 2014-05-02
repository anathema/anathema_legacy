package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;
import net.sf.anathema.hero.creation.CharacterTemplateCreator;

public interface CharacterGridView {

  void addButton(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector);

  void selectButton(CharacterIdentifier identifier);

  void updateButton(CharacterButtonDto dto);

  CharacterTemplateCreator createNewCharacter();
}
