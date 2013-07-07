package net.sf.anathema.hero.othertraits.sheet.essence.content.pools;

import net.sf.anathema.hero.essencepool.EssencePoolModel;
import net.sf.anathema.hero.essencepool.EssencePoolModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

public class PeripheralPoolRow extends AbstractPoolRow {

  private Resources resources;
  private Hero hero;

  public PeripheralPoolRow(Resources resources, Hero hero) {
    this.resources = resources;
    this.hero = hero;
  }

  @Override
  public String getLabel() {
    return resources.getString("Sheet.Essence.PeripheralPool");
  }

  @Override
  public int getCapacityValue() {
    return getModel().getPeripheralPoolValue();
  }

  @Override
  public Integer getCommittedValue() {
    int committed = getModel().getAttunedPoolValue();
    return Math.min(getModel().getPeripheralPoolValue(), committed);
  }

  public EssencePoolModel getModel() {
    return EssencePoolModelFetcher.fetch(hero);
  }
}
