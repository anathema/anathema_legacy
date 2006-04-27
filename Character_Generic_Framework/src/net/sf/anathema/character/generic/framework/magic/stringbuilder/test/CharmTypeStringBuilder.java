package net.sf.anathema.character.generic.framework.magic.stringbuilder.test;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.ICharmTypeStringBuilder;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.lib.resources.IResources;

public class CharmTypeStringBuilder implements ICharmTypeStringBuilder {

  private final IResources resources;

  public CharmTypeStringBuilder(IResources resources) {
    this.resources = resources;
  }

  public String buildString(ICharmTypeModel charmTypeModel) {
    return resources.getString(charmTypeModel.getCharmType().getId());
  }
}
