package net.sf.anathema.character.reporting.sheet.common.magic.generic.solar;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.magic.charm.type.ReflexiveSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.lib.resources.IResources;

public class FirstExcellency extends AbstractGenericCharm {

  public String getCostString(IResources resources) {
    return "1 m per die";
  }

  protected String getId() {
    return "Solar.1stExcellency";
  }

  protected boolean isComboOk() {
    return false;
  }

  public String getDurationString(IResources resources) {
    return SimpleDuration.INSTANT_DURATION.getText(resources);
  }

  public String getType(IResources resources) {
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(CharmType.Reflexive);
    model.setSpecialModel(new ReflexiveSpecialsModel(1, 2));
    return new ShortCharmTypeStringBuilder(resources).createTypeString(model);
  }
}