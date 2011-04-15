package net.sf.anathema.character.ghost.passions.view;

import net.sf.anathema.lib.resources.IResources;

public class GhostPassionsConfigurationViewProperties implements IGhostPassionsViewProperties {

  private final IResources resources;

  public GhostPassionsConfigurationViewProperties(IResources resources) {
    this.resources = resources;
  }

  public String getCollegeString() {
    return resources.getString("Astrology.Border.Colleges"); //$NON-NLS-1$
  }
}