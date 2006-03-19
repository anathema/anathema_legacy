package net.sf.anathema.character.solar.virtueflaw;

import javax.swing.event.ChangeListener;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.solar.virtueflaw.model.SolarVirtueFlawModel;

public class SolarVirtueFlawModelFactory implements IAdditionalModelFactory {

  public IAdditionalModel createModel(
      IAdditionalTemplate additionalTemplate,
      ICharacterModelContext context,
      ChangeListener[] listeners) {
    SolarVirtueFlawModel model = new SolarVirtueFlawModel(context, additionalTemplate);
    for (ChangeListener listener : listeners) {
      model.addBonusPointsChangeListener(listener);
    }
    return model;
  }
}