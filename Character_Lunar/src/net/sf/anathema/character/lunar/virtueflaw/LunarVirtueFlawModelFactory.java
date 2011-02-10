package net.sf.anathema.character.lunar.virtueflaw;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.library.virtueflaw.model.HighestTraitVirtueFlawModel;
import net.sf.anathema.character.lunar.virtueflaw.model.LunarVirtueFlawModel;

public class LunarVirtueFlawModelFactory implements IAdditionalModelFactory {

  public IAdditionalModel createModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
	  if (context.getBasicCharacterContext().getRuleSet().getEdition() == ExaltedEdition.SecondEdition)
		  return new LunarVirtueFlawModel(context, additionalTemplate);
	  else
		  return new HighestTraitVirtueFlawModel(context, additionalTemplate);
  }
}