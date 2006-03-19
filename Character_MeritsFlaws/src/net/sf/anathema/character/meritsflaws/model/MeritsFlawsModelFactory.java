package net.sf.anathema.character.meritsflaws.model;

import javax.swing.event.ChangeListener;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;

public class MeritsFlawsModelFactory implements IAdditionalModelFactory {

  public IAdditionalModel createModel(
      IAdditionalTemplate additionalTemplate,
      ICharacterModelContext context,
      ChangeListener[] listeners) {
    return new MeritsFlawsAdditionalModel(additionalTemplate, context, listeners);
  }
}