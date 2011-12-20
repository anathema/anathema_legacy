package net.sf.anathema.character.reporting.pdf.content.combat;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubContent;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractCombatStatsContent extends AbstractSubContent {

  private IGenericCharacter character;

  protected AbstractCombatStatsContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  public int getKnockdownPool() {
    return CharacterUtilties.getKnockdownPool(getCharacter(), getEquipment());
  }

  public int getStunningThreshold() {
    return CharacterUtilties.getStunningThreshold(getTraitCollection(), getEquipment());
  }

  public int getKnockdownThreshold() {
    return CharacterUtilties.getKnockdownThreshold(getTraitCollection(), getEquipment());
  }

  public int getStunningPool() {
    return CharacterUtilties.getStunningPool(getTraitCollection(), getEquipment());
  }

  public String getKnockdownLabel() {
    return getString("Sheet.Combat.Knockdown"); //$NON-NLS-1$
  }

  public String getStunningLabel() {
    return getString("Sheet.Combat.Stunning"); //$NON-NLS-1$
  }

  public String getThresholdPoolLabel() {
    return getString("Sheet.Combat.ThresholdPool"); //$NON-NLS-1$
  }

  @Override
  public String getHeaderKey() {
    return "Combat"; //$NON-NLS-1$
  }

  @Override
  public boolean hasContent() {
    return true;
  }

  protected IEquipmentModifiers getEquipment() {
    return getCharacter().getEquipmentModifiers();
  }

  protected IGenericCharacter getCharacter() {
    return character;
  }

  protected ICharacterType getCharacterType() {
    return getCharacter().getTemplate().getTemplateType().getCharacterType();
  }

  protected IGenericTraitCollection getTraitCollection() {
    return getCharacter().getTraitCollection();
  }
}
