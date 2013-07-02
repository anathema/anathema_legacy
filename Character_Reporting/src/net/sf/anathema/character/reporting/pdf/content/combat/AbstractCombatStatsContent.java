package net.sf.anathema.character.reporting.pdf.content.combat;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.CharacterUtilities;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractCombatStatsContent extends AbstractSubBoxContent {

  private Hero hero;
  private IGenericCharacter character;

  protected AbstractCombatStatsContent(Resources resources, Hero hero, IGenericCharacter character) {
    super(resources);
    this.hero = hero;
    this.character = character;
  }

  public int getKnockdownPool() {
    return CharacterUtilities.getKnockdownPool(getCharacter());
  }

  public int getStunningThreshold() {
    return CharacterUtilities.getStunningThreshold(getTraitCollection());
  }

  public int getKnockdownThreshold() {
    return CharacterUtilities.getKnockdownThreshold(getTraitCollection());
  }

  public int getStunningPool() {
    return CharacterUtilities.getStunningPool(getTraitCollection());
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

  protected IGenericCharacter getCharacter() {
    return character;
  }

  protected ICharacterType getCharacterType() {
    return hero.getTemplate().getTemplateType().getCharacterType();
  }

  protected IGenericTraitCollection getTraitCollection() {
    return getCharacter().getTraitCollection();
  }
}
