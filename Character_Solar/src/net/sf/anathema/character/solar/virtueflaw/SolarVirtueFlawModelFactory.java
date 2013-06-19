package net.sf.anathema.character.solar.virtueflaw;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.library.virtueflaw.model.DescriptiveVirtueFlawModel;
import net.sf.anathema.hero.model.Hero;

public class SolarVirtueFlawModelFactory implements IAdditionalModelFactory {

  @Override
  public IAdditionalModel createModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context, Hero hero) {
    return new DescriptiveVirtueFlawModel(hero, additionalTemplate);
  }
}