package net.sf.anathema.character.lunar.heartsblood;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.lunar.heartsblood.model.HeartsBloodModel;
import net.sf.anathema.lib.control.change.IChangeListener;

public class HeartsBloodFactory implements IAdditionalModelFactory {

  public IAdditionalModel createModel(
      IAdditionalTemplate additionalTemplate,
      ICharacterModelContext context,
      IChangeListener[] listeners) {
    return new HeartsBloodModel(context);
  }
}