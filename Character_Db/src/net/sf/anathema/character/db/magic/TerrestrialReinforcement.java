package net.sf.anathema.character.db.magic;

import net.sf.anathema.character.generic.framework.magic.AbstractGenericCharm;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.magic.charms.duration.QualifiedAmountDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.lib.resources.IResources;

public class TerrestrialReinforcement extends AbstractGenericCharm {

  @Override
  public String getCostString(IResources resources) {
    return "1 m per 2 dice + 1m per subject";
  }

  @Override
  protected String getId() {
    return "Dragon-Blooded.TerrestrialReinforcement"; //$NON-NLS-1$
  }

  @Override
  protected boolean isComboOk() {
    return true;
  }

  @Override
  public String getDurationString(IResources resources) {
    return new QualifiedAmountDuration("1", "scene").getText(resources); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Override
  public String getType(IResources resources) {
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(CharmType.Simple);
    return new ShortCharmTypeStringBuilder(resources).createTypeString(model);
  }
}