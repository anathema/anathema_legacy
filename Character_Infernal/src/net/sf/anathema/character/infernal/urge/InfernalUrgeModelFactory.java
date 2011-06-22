package net.sf.anathema.character.infernal.urge;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.infernal.urge.model.InfernalUrgeModel;

public class InfernalUrgeModelFactory implements IAdditionalModelFactory {

  public IAdditionalModel createModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
	  return new InfernalUrgeModel((InfernalUrgeTemplate) additionalTemplate, context);
  }
}