package net.sf.anathema.character.abyssal.resonance;

import net.sf.anathema.character.abyssal.resonance.model.AbyssalResonanceModel;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;

public class AbyssalResonanceModelFactory implements IAdditionalModelFactory {

  public IAdditionalModel createModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
    return new AbyssalResonanceModel(context, additionalTemplate);
  }
}