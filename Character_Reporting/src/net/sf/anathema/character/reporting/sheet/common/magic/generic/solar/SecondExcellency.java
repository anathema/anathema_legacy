package net.sf.anathema.character.reporting.sheet.common.magic.generic.solar;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.magic.charm.type.ReflexiveSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.lib.resources.IResources;

public class SecondExcellency extends AbstractGenericCharm {
  
  public String getType(IResources resources) {
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(CharmType.Reflexive);
    model.setSpecialModel(new ReflexiveSpecialsModel(1, 2));
    return new ShortCharmTypeStringBuilder(resources).createTypeString(model);
  }
  
  public String getDurationString(IResources resources) {
    return SimpleDuration.INSTANT_DURATION.getText(resources);
  }
  
  @Override
  protected String getId() {
    return "Solar.2ndExcellency"; //$NON-NLS-1$
  }

  @Override
  protected boolean isComboOk() {
    return true;
  }
  
  public String getCostString(IResources resources) {
    return "2 m per success";
  }

}
