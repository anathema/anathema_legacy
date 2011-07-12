package net.sf.anathema.character.equipment.impl.reporting;

import net.sf.anathema.character.equipment.impl.reporting.second.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class AccuracyWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeaponStats> {

  private final IGenericTraitCollection collection;
  private final IEquipmentModifiers equipment;

  public AccuracyWeaponStatsGroup(IResources resources, IGenericTraitCollection collection, IEquipmentModifiers equipment) {
    super(resources, "Accuracy"); //$NON-NLS-1$
    this.collection = collection;
    this.equipment = equipment;
  }

  public int getColumnCount() {
    return 2;
  }

  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(createEmptyValueCell(font));
      table.addCell(createFinalValueCell(font));
    }
    else {
      int weaponValue = weapon.getAccuracy();
      table.addCell(createEquipmentValueCell(font, weaponValue));
      int calculateFinalValue = getFinalValue(weapon, weaponValue, equipment);
      table.addCell(createFinalValueCell(font, calculateFinalValue));
    }
  }

  protected int getFinalValue(IWeaponStats weapon, int weaponValue, IEquipmentModifiers equipment) {
    return calculateFinalValue(
        weaponValue,
        collection.getTrait(AttributeType.Dexterity),
        collection.getTrait(weapon.getTraitType()));
  }
}