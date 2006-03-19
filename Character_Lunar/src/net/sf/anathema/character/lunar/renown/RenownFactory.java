package net.sf.anathema.character.lunar.renown;

import javax.swing.event.ChangeListener;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.lunar.renown.model.RenownModel;

public class RenownFactory implements IAdditionalModelFactory {

  public IAdditionalModel createModel(
      IAdditionalTemplate additionalTemplate,
      ICharacterModelContext context,
      ChangeListener[] listeners) {
    return new RenownModel(context);
  }
}