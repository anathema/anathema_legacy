package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.lib.resources.Resources;

public class DefaultAdvantageViewProperties implements AdvantageViewProperties {
  private Resources resources;

  public DefaultAdvantageViewProperties(Resources resources) {
    this.resources = resources;
  }

  @Override
  public String getVirtueTitle() {
    return resources.getString("AdvantagesView.Virtues.Title");
  }

  @Override
  public String getWillpowerTitle() {
    return resources.getString("AdvantagesView.Willpower.Title");
  }

  @Override
  public String getEssenceTitle() {
    return resources.getString("AdvantagesView.Essence.Title");
  }

  public String getOverallHeader() {
    return getVirtueTitle() + ", " + getWillpowerTitle() + " & " + getEssenceTitle();
  }
}