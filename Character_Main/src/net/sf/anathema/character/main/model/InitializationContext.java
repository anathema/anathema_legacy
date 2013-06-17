package net.sf.anathema.character.main.model;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.TraitContext;
import net.sf.anathema.character.generic.template.HeroTemplate;

public interface InitializationContext {

  HeroTemplate getTemplate();

  TraitContext getTraitContext();

  ChangeAnnouncer getChangeAnnouncer();
}
