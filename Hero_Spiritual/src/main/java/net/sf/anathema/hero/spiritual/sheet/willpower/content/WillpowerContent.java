package net.sf.anathema.hero.spiritual.sheet.willpower.content;

import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.sheet.pdf.encoder.general.ListUtils;
import net.sf.anathema.hero.spiritual.SpiritualTraitModelFetcher;
import net.sf.anathema.hero.traits.sheet.content.BulletList;
import net.sf.anathema.framework.environment.Resources;

public class WillpowerContent extends AbstractSubBoxContent {

  private Hero hero;

  public WillpowerContent(Resources resources, Hero hero) {
    super(resources);
    this.hero = hero;
  }

  @Override
  public String getHeaderKey() {
    return "Willpower";
  }

  public int getWillpowerValue() {
    return SpiritualTraitModelFetcher.fetch(hero).getTrait(OtherTraitType.Willpower).getCurrentValue();
  }

  public String getWillpowerSpendingNote() {
    return getString("Sheet.WillpowerSpendingNote");
  }

  public BulletList getWillpowerSpendingRules() {
    return createBulletList("Sheet.WillpowerSpendingRules");
  }

  public BulletList getWillpowerRegainingRules() {
    return createBulletList("Sheet.WillpowerRegainingRules");
  }

  private BulletList createBulletList(String resourceBase) {
    String header = ListUtils.getRequiredString(getResources(), resourceBase);
    String[] items = ListUtils.getAvailableLineItems(getResources(), resourceBase);
    return new BulletList(header, items);
  }
}