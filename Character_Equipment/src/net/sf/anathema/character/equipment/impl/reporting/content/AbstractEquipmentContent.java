package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.equipment.NaturalWeaponMap;
import net.sf.anathema.hero.equipment.model.EquipmentModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentPrintModel;
import net.sf.anathema.character.equipment.impl.character.model.stats.CharacterStatsModifiers;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubContent;
import net.sf.anathema.character.reporting.pdf.content.stats.FixedLineStatsContent;
import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractEquipmentContent<STATS extends IEquipmentStats> extends AbstractSubContent implements FixedLineStatsContent<STATS> {

  private IGenericCharacter character;

  public AbstractEquipmentContent(Resources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  protected IGenericCharacter getCharacter() {
    return character;
  }

  protected IEquipmentPrintModel getEquipmentModel() {
    EquipmentModel model = (EquipmentModel) character.getAdditionalModel(NaturalWeaponMap.ID);
    return model.getPrintModel();
  }

  protected ICharacterStatsModifiers getEquipmentModifiers() {
    return CharacterStatsModifiers.extractFromCharacter(character);
  }
}
