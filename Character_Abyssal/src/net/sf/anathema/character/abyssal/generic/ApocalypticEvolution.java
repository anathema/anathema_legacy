package net.sf.anathema.character.abyssal.generic;

import net.sf.anathema.character.generic.framework.magic.AbstractGenericCharm;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.lib.resources.IResources;

public class ApocalypticEvolution extends AbstractGenericCharm{

  @Override
  protected String getId() {
    return "Abyssal.ApocalypticEvolutionOf";
  }

  @Override
  protected ExaltedSourceBook getSourceBook() {
    return ExaltedSourceBook.ScrollErrata;
  }

  @Override
  protected boolean isComboOk() {
    return false;
  }

  @Override
  public String getCostString(IResources resources) {
    return "-";
  }

  @Override
  public String getDurationString(IResources resources) {
    return SimpleDuration.PERMANENT_DURATION.getText(resources);
  }

  @Override
  public String getType(IResources resources) {
    CharmTypeModel model = new CharmTypeModel();
    model.setCharmType(CharmType.Permanent);
    return new ShortCharmTypeStringBuilder(resources).createTypeString(model);
  }
}
