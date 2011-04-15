package net.sf.anathema.character.ghost.passions;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.ghost.passions.model.GhostPassionsModel;

public class GhostPassionsModelFactory implements IAdditionalModelFactory {

  public IAdditionalModel createModel(IAdditionalTemplate additionalTemplate, ICharacterModelContext context) {
    return new GhostPassionsModel((GhostPassionsTemplate) additionalTemplate, context);
  }
}