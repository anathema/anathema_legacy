package net.sf.anathema.character.solar.virtueflaw;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.solar.virtueflaw.model.SolarVirtueFlawModel;
import net.sf.anathema.lib.control.change.IChangeListener;

public class SolarVirtueFlawModelFactory implements IAdditionalModelFactory {

  public IAdditionalModel createModel(
      IAdditionalTemplate additionalTemplate,
      ICharacterModelContext context,
      IChangeListener[] listeners) {
    SolarVirtueFlawModel model = new SolarVirtueFlawModel(context, additionalTemplate);
    for (IChangeListener listener : listeners) {
      model.addBonusPointsChangeListener(listener);
    }
    return model;
  }
}