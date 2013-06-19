package net.sf.anathema.character.main.hero;

import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.impl.magic.persistence.ISpellCache;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.type.CharacterTypes;

import java.util.List;

public interface InitializationContext {

  @Deprecated
  IMagicCollection getMagicCollection();

  <T> List<T> getAllRegistered(Class<T> interfaceClass);

  CharacterTypes getCharacterTypes();

  ITemplateRegistry getTemplateRegistry();

  ICharmProvider getCharmProvider();

  ISpellCache getSpellCache();
}
