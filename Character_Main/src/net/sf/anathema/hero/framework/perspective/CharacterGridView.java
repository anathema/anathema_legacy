package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;

public interface CharacterGridView {

  void addButton(CharacterButtonDto dto, Selector<CharacterIdentifier> characterSelector);

  void selectButton(CharacterIdentifier identifier);

  void updateButton(CharacterButtonDto dto);
}
