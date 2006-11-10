package net.sf.anathema.character.reporting.sheet.common.magic.generic.solar;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.lib.resources.IResources;

public class EssenceFlow extends AbstractGenericCharm {

  @Override
  protected String getId() {
    return "Solar.EssenceFlow"; //$NON-NLS-1$
  }

  @Override
  protected boolean isComboOk() {
    return false;
  }

  public String getCostString(IResources resources) {
    return "-"; //$NON-NLS-1$
  }

  public String getDurationString(IResources resources) {
    return SimpleDuration.PERMANENT_DURATION.getText(resources);
  }

  public String getType(IResources resources) {
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(CharmType.Permanent);
    return new ShortCharmTypeStringBuilder(resources).createTypeString(model);
  }
}