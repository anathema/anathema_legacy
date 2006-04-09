package net.sf.anathema.character.sidereal.colleges;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.sidereal.colleges.model.SiderealCollegeModel;
import net.sf.anathema.lib.control.IChangeListener;

public class SiderealCollegeModelFactory implements IAdditionalModelFactory {

  public IAdditionalModel createModel(
      IAdditionalTemplate additionalTemplate,
      ICharacterModelContext context,
      IChangeListener[] listeners) {
    SiderealCollegeModel siderealCollegeModel = new SiderealCollegeModel(
        (SiderealCollegeTemplate) additionalTemplate,
        context);
    for (IChangeListener listener : listeners) {
      siderealCollegeModel.addBonusPointsChangeListener(listener);
    }
    return siderealCollegeModel;
  }
}