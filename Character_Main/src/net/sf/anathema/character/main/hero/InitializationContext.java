package net.sf.anathema.character.main.hero;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.TraitContext;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.type.CharacterTypes;

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

  CharacterTypes getCharacterTypes();

  ITemplateRegistry getTemplateRegistry();

  ICharmProvider getCharmProvider();
}
