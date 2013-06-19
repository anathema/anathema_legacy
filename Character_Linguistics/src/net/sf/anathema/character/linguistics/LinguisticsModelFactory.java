package net.sf.anathema.character.linguistics;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.linguistics.model.LinguisticsAdditionalModel;
import net.sf.anathema.character.main.hero.Hero;

public class LinguisticsModelFactory implements IAdditionalModelFactory {

  @Override
  public IAdditionalModel createModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context, Hero hero) {
    return new LinguisticsAdditionalModel(hero, additionalTemplate, context);
  }
}