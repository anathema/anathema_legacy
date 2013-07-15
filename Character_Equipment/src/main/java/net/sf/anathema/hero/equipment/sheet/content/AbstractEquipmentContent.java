package net.sf.anathema.hero.equipment.sheet.content;

import net.sf.anathema.character.equipment.character.model.IEquipmentPrintModel;
import net.sf.anathema.character.main.equipment.HeroStatsModifiers;
import net.sf.anathema.character.main.equipment.weapon.IArmourStats;
import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.EquipmentModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubContent;
import net.sf.anathema.hero.sheet.pdf.content.stats.FixedLineStatsContent;
import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractEquipmentContent<STATS extends IEquipmentStats> extends AbstractSubContent implements FixedLineStatsContent<STATS> {

  private Hero hero;

  public AbstractEquipmentContent(Hero hero, Resources resources) {
    super(resources);
    this.hero = hero;
  }

  protected Hero getHero() {
    return hero;
  }

  protected IEquipmentPrintModel getEquipmentModel() {
    EquipmentModel model = EquipmentModelFetcher.fetch(hero);
    IArmourStats naturalArmor = model.getNaturalArmor();
    return new EquipmentPrintModel(model, naturalArmor);
  }

  protected HeroStatsModifiers getEquipmentModifiers() {
    EquipmentModel model = EquipmentModelFetcher.fetch(hero);
    return model.createStatsModifiers(hero);
  }
}
