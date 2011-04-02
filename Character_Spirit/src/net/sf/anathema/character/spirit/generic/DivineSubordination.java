

package net.sf.anathema.character.spirit.generic;

import net.sf.anathema.character.generic.framework.magic.AbstractGenericCharm;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.lib.resources.IResources;

public class DivineSubordination extends AbstractGenericCharm{

  @Override
  protected String getId() {
    return "Spirit.DivineSubordination";
  }

  @Override
  protected ExaltedSourceBook getSourceBook() {
    return ExaltedSourceBook.RollsOfGloriousDivinityOne;
  }

  @Override
  protected boolean isComboOk() {
    return false;
  }

  @Override
  public String getCostString(IResources resources) {
    return "5m, 1wp";
  }

  @Override
  public String getDurationString(IResources resources) {
    return SimpleDuration.INSTANT_DURATION.getText(resources);
  }

  @Override
  public String getType(IResources resources) {
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(CharmType.Permanent);
    return new ShortCharmTypeStringBuilder(resources).createTypeString(model);
  }
}
