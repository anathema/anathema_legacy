package net.sf.anathema.hero.othertraits.sheet.essence.content.pools;

import net.sf.anathema.hero.essencepool.EssencePoolModel;
import net.sf.anathema.hero.essencepool.EssencePoolModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

public class PersonalPoolRow extends AbstractPoolRow {

  private Resources resources;
  private Hero hero;

  public PersonalPoolRow(Resources resources, Hero hero) {
    this.resources = resources;
    this.hero = hero;
  }

  @Override
  public String getLabel() {
    return resources.getString("Sheet.Essence.PersonalPool");
  }

  @Override
  public int getCapacityValue() {
    return getModel().getPersonalPoolValue();
  }

  @Override
  public Integer getCommittedValue() {
    PeripheralPoolRow peripheralPool = new PeripheralPoolRow(resources, hero);
    int committed = getModel().getAttunedPoolValue();
    return committed - peripheralPool.getCommittedValue();
  }

  public EssencePoolModel getModel() {
    return EssencePoolModelFetcher.fetch(hero);
  }
}
