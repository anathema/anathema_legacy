package net.sf.anathema.hero.combat.sheet.combat.content;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.util.CharacterUtilities;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractCombatStatsContent extends AbstractSubBoxContent {

  private Hero hero;

  protected AbstractCombatStatsContent(Resources resources, Hero hero) {
    super(resources);
    this.hero = hero;
  }

  public int getKnockdownPool() {
    return CharacterUtilities.getKnockdownPool(hero);
  }

  public int getStunningThreshold() {
    return CharacterUtilities.getStunningThreshold(getTraits());
  }

  public int getKnockdownThreshold() {
    return CharacterUtilities.getKnockdownThreshold(getTraits());
  }

  public int getStunningPool() {
    return CharacterUtilities.getStunningPool(getTraits());
  }

  public String getKnockdownLabel() {
    return getString("Sheet.Combat.Knockdown");
  }

  public String getStunningLabel() {
    return getString("Sheet.Combat.Stunning");
  }

  public String getThresholdPoolLabel() {
    return getString("Sheet.Combat.ThresholdPool");
  }

  @Override
  public String getHeaderKey() {
    return "Combat";
  }

  @Override
  public boolean hasContent() {
    return true;
  }

  protected CharacterType getCharacterType() {
    return hero.getTemplate().getTemplateType().getCharacterType();
  }

  protected TraitMap getTraits() {
    return TraitModelFetcher.fetch(hero);
  }
}
