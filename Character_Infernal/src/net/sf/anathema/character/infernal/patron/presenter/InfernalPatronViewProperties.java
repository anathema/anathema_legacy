package net.sf.anathema.character.infernal.patron.presenter;

import net.sf.anathema.lib.resources.IResources;

public class InfernalPatronViewProperties implements IInfernalPatronViewProperties {

  private final IResources resources;

  public InfernalPatronViewProperties(IResources resources) {
    this.resources = resources;
  }

  public String getCollegeString() {
    return resources.getString("InfernalPatron.Overview.Title"); //$NON-NLS-1$
  }
}