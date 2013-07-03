package net.sf.anathema.hero.othertraits.sheet.willpower.content;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.main.model.othertraits.OtherTraitModelFetcher;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.general.BulletList;
import net.sf.anathema.character.reporting.pdf.rendering.general.ListUtils;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

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
    return OtherTraitModelFetcher.fetch(hero).getTrait(OtherTraitType.Willpower).getCurrentValue();
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