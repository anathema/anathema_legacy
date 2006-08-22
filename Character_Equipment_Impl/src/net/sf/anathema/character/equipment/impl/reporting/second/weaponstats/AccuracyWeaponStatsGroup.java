package net.sf.anathema.character.equipment.impl.reporting.second.weaponstats;

import net.sf.anathema.character.equipment.impl.reporting.second.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class AccuracyWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeaponStats> {

  private final IGenericCharacter character;

  public AccuracyWeaponStatsGroup(IResources resources, IGenericCharacter character) {
    super(resources, "Accuracy"); //$NON-NLS-1$
    this.character = character;
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
      final int weaponValue = weapon.getAccuracy();
      table.addCell(createEquipmentValueCell(font, weaponValue));
      IGenericTraitCollection traitCollection = character.getTraitCollection();
      final int calculateFinalValue = calculateFinalValue(
          weaponValue,
          traitCollection.getTrait(AttributeType.Dexterity),
          traitCollection.getTrait(weapon.getTraitType()));
      table.addCell(createFinalValueCell(font, calculateFinalValue));
    }
  }
}