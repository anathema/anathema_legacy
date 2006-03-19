package net.sf.anathema.character.sidereal.colleges;

import javax.swing.event.ChangeListener;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.sidereal.colleges.model.SiderealCollegeModel;

public class SiderealCollegeModelFactory implements IAdditionalModelFactory {

  public IAdditionalModel createModel(
      IAdditionalTemplate additionalTemplate,
      ICharacterModelContext context,
      ChangeListener[] listeners) {
    SiderealCollegeModel siderealCollegeModel = new SiderealCollegeModel(
        (SiderealCollegeTemplate) additionalTemplate,
        context);
    for (ChangeListener listener : listeners) {
      siderealCollegeModel.addBonusPointsChangeListener(listener);
    }
    return siderealCollegeModel;
  }
}