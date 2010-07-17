

package net.sf.anathema.character.solar.generic;

import net.sf.anathema.character.generic.framework.magic.AbstractGenericCharm;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public class UltimateMastery extends AbstractGenericCharm{

  @Override
  protected String getId() {
    return "Solar.UltimateMastery";
  }

  @Override
  protected ExaltedSourceBook getSourceBook() {
    return ExaltedSourceBook.LordsOfCreation;
  }

  @Override
  protected boolean isComboOk() {
    return true;
  }

  @Override
  public String getCostString(IResources resources) {
    return "3 m, 1 wp";
  }

  @Override
  public String getDurationString(IResources resources) {
    return SimpleDuration.INSTANT_DURATION.getText(resources);
  }

  @Override
  public String getType(IResources resources) {
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(CharmType.Reflexive);
    return new ShortCharmTypeStringBuilder(resources).createTypeString(model);
  }
}
