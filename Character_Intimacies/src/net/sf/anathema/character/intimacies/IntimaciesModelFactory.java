package net.sf.anathema.character.intimacies;

import javax.swing.event.ChangeListener;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.intimacies.model.IntimaciesModel;

public class IntimaciesModelFactory implements IAdditionalModelFactory {

  public IAdditionalModel createModel(
      IAdditionalTemplate additionalTemplate,
      ICharacterModelContext context,
      ChangeListener[] listeners) {
    return new IntimaciesModel(additionalTemplate, context, listeners);
  }

}
