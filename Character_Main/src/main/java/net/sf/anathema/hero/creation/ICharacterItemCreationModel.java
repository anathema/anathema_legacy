package net.sf.anathema.hero.creation;

import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.lib.control.ChangeListener;

public interface ICharacterItemCreationModel {

  void setCharacterType(CharacterType type);

  void setSelectedTemplate(HeroTemplate newValue);

  void addListener(ChangeListener listener);

  Iterable<CharacterType> getAvailableCharacterTypes();

  HeroTemplate[] getAvailableTemplates();

  HeroTemplate getSelectedTemplate();
}