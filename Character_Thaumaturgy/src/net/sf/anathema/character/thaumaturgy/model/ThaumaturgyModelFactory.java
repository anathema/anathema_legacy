package net.sf.anathema.character.thaumaturgy.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.thaumaturgy.ThaumaturgyAdditionalModel;

public class ThaumaturgyModelFactory implements IAdditionalModelFactory {

  public IAdditionalModel createModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
    return new ThaumaturgyAdditionalModel(additionalTemplate, context);
  }
}