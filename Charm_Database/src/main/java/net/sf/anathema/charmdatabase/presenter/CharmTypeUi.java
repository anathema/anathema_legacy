package net.sf.anathema.charmdatabase.presenter;

import net.sf.anathema.character.main.magic.charm.type.CharmType;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;

public class CharmTypeUi extends AbstractUIConfiguration<CharmType>{
	private Resources resources;

	  public CharmTypeUi(Resources resources) {
	    this.resources = resources;
	  }

	  @Override
	  protected String labelForExistingValue(CharmType value) {
	    return resources.getString(value.getId());
	  }
}
