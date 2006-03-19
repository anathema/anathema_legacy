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

  public String getOverviewString() {
    return resources.getString("Overview.Title"); //$NON-NLS-1$
  }

  public String getFavoredCollegeDotsString() {
    return resources.getString("Astrology.Overview.FavoredDots"); //$NON-NLS-1$
  }

  public String getGeneralCollegeDotsString() {
    return resources.getString("Astrology.Overview.GeneralDots"); //$NON-NLS-1$
  }

  public String getCollegeBonusPointsString() {
    return resources.getString("Astrology.Overview.BonusPoints"); //$NON-NLS-1$
  }

  public String getExperiencePointsString() {
    return resources.getString("Astrology.Overview.Experience"); //$NON-NLS-1$
  }
}