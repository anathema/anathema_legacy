package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.main.hero.Hero;

public interface IAdditionalModelFactory {

  IAdditionalModel createModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context, Hero hero);
}