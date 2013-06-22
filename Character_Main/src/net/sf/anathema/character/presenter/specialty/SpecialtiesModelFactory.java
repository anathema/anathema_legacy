package net.sf.anathema.character.presenter.specialty;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.hero.model.Hero;

public class SpecialtiesModelFactory implements IAdditionalModelFactory {

  @Override
  public IAdditionalModel createModel(IAdditionalTemplate additionalTemplate, Hero hero) {
    return new SpecialtiesAdditionalModel(hero, additionalTemplate);
  }
}