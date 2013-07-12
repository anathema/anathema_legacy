package net.sf.anathema.hero.spiritual.sheet.essence.content.pools;

import net.sf.anathema.hero.spiritual.model.pool.EssencePoolModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

public class OverdrivePoolRow extends AbstractPoolRow {

  private Resources resources;
  private Hero hero;

  public OverdrivePoolRow(Resources resources, Hero hero) {
    this.resources = resources;
    this.hero = hero;
  }

  @Override
  public String getLabel() {
    return resources.getString("Sheet.Essence.OverdrivePool");
  }

  @Override
  public int getCapacityValue() {
    return EssencePoolModelFetcher.fetch(hero).getOverdrivePoolValue();
  }

  @Override
  public Integer getCommittedValue() {
   return null;
  }
}
