package net.sf.anathema.character.lunar.beastform;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.lunar.beastform.model.FirstEditionBeastformModel;
import net.sf.anathema.character.lunar.beastform.model.SecondEditionBeastformModel;

public class BeastformModelFactory implements IAdditionalModelFactory {

  public IAdditionalModel createModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
	  if (context.getBasicCharacterContext().getRuleSet().getEdition() == ExaltedEdition.FirstEdition)
	  	return new FirstEditionBeastformModel(context);
	  else
		return new SecondEditionBeastformModel(context);
	  		
  }
}