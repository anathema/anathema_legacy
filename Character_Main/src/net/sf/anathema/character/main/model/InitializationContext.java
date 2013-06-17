package net.sf.anathema.character.main.model;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.TraitContext;
import net.sf.anathema.character.generic.template.HeroTemplate;

import java.util.List;

public interface InitializationContext {

  @Deprecated
  IBasicCharacterData getBasicCharacterContext();

  @Deprecated
  ICharacterListening getCharacterListening();

  @Deprecated
  TraitContext getTraitContext();

  @Deprecated
  IMagicCollection getMagicCollection();

  @Deprecated
  ICharmContext getCharmContext();

  <T> List<T> getAllRegistered(Class<T> interfaceClass);

  HeroTemplate getTemplate();

  ChangeAnnouncer getChangeAnnouncer();
}
