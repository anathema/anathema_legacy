package net.sf.anathema.character.reporting.pdf.content.combat;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionCombatStatsContent extends AbstractCombatStatsContent {

  private IGenericCharacter character;

  protected SecondEditionCombatStatsContent(IGenericCharacter character, IResources resources) {
    super(resources);
    this.character = character;
  }

  public String getJoinLabel() {
    return getResources().getString("Sheet.Combat.JoinBattle"); //$NON-NLS-1$
  }

  public String getDodgeLabel() {
    return getResources().getString("Sheet.Combat.DodgeDV"); //$NON-NLS-1$
  }

  public String getKnockdownLabel() {
    return getResources().getString("Sheet.Combat.Knockdown"); //$NON-NLS-1$
  }

  public String getStunningLabel() {
    return getResources().getString("Sheet.Combat.Stunning"); //$NON-NLS-1$
  }

  public String getThresholdPoolLabel() {
    return getResources().getString("Sheet.Combat.ThresholdPool"); //$NON-NLS-1$
  }

  public int getJoinBattle() {
    return CharacterUtilties.getJoinBattle(getTraitCollection(), getEquipment());
  }

  public int getDodgeDv() {
    return CharacterUtilties.getDodgeDv(getCharacterType(), getTraitCollection(), getEquipment());
  }

  public int getKnockdownThreshold() {
    return CharacterUtilties.getKnockdownThreshold(getTraitCollection(), getEquipment());
  }

  public int getKnockdownPool() {
    return CharacterUtilties.getKnockdownPool(character, getTraitCollection(), getEquipment());
  }

  public int getStunningThreshold() {
    return CharacterUtilties.getStunningThreshold(getTraitCollection(), getEquipment());
  }

  public int getStunningPool() {
    return CharacterUtilties.getStunningPool(getTraitCollection(), getEquipment());
  }

  private ICharacterType getCharacterType() {
    return character.getTemplate().getTemplateType().getCharacterType();
  }

  private IEquipmentModifiers getEquipment() {
    return character.getEquipmentModifiers();
  }

  private IGenericTraitCollection getTraitCollection() {
    return character.getTraitCollection();
  }
}
