package net.sf.anathema.character.main.model;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.TraitContext;
import net.sf.anathema.character.generic.template.HeroTemplate;

public interface InitializationContext {

  @Deprecated
  IBasicCharacterData getBasicCharacterContext();

  @Deprecated
  ICharacterListening getCharacterListening();

  @Deprecated
  TraitContext getTraitContext();

  HeroTemplate getTemplate();

  ChangeAnnouncer getChangeAnnouncer();
}
