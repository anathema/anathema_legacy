package net.sf.anathema.character.equipment.creation.presenter.stats.properties;

import net.sf.anathema.framework.environment.Resources;

public class ArtifactStatisticsProperties extends AbstractProperties {

  public ArtifactStatisticsProperties(Resources resources) {
    super(resources);
  }

  public String getAttuneCostLabel() {
    return getLabelString("Equipment.Stats.Long.AttuneCost");
  }
  
  public String getRequireAttuneLabel() {
	    return getLabelString("Equipment.Stats.Long.RequireAttune");
	  }
  
  public String getForeignAttuneLabel() {
	    return getLabelString("Equipment.Stats.Long.ForeignAttune");
	  }
}