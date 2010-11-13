package net.sf.anathema.character.sidereal.colleges.presenter;

import net.sf.anathema.lib.resources.IResources;

public class SiderealCollegeViewProperties implements ISiderealCollegeViewProperties {

  private final IResources resources;

  public SiderealCollegeViewProperties(IResources resources) {
    this.resources = resources;
  }

  public String getCollegeString() {
    return resources.getString("Astrology.Border.Colleges"); //$NON-NLS-1$
  }
}