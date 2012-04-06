package net.sf.anathema.character.reporting.pdf.content.combat;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.generic.impl.CharacterUtilities;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractCombatStatsContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  protected AbstractCombatStatsContent(IResources resources, IGenericCharacter character) {
    super(resources);
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

  protected ICharacterStatsModifiers getEquipment() {
    IEquipmentAdditionalModel equipment = (IEquipmentAdditionalModel) character.getAdditionalModel(
            IEquipmentAdditionalModelTemplate.ID);
    return equipment.createStatsModifiers(character);
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
