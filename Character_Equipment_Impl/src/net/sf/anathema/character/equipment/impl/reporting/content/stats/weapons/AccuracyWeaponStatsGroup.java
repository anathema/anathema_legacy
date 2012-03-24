package net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;

import net.sf.anathema.character.equipment.character.IEquipmentCharacterDataProvider;
import net.sf.anathema.character.equipment.character.IEquipmentCharacterOptionProvider;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.resources.IResources;

public class AccuracyWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeaponStats> {

  private final IGenericTraitCollection collection;
  private final IEquipmentCharacterDataProvider provider;
  private IEquipmentCharacterOptionProvider optionProvider;

  public AccuracyWeaponStatsGroup(IResources resources, IGenericTraitCollection collection,
                                  IEquipmentCharacterDataProvider provider,
                                  IEquipmentCharacterOptionProvider optionProvider) {
    super(resources, "Accuracy"); //$NON-NLS-1$
    this.collection = collection;
    this.optionProvider = optionProvider;
    this.provider = provider;
  }

  @Override
  public int getColumnCount() {
    return 2;
  }

  @Override
  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(createEmptyValueCell(font));
      table.addCell(createFinalValueCell(font));
    } else {
      int weaponValue = weapon.getAccuracy();
      table.addCell(createEquipmentValueCell(font, weaponValue));
      int calculateFinalValue = getFinalValue(weapon, weaponValue);
      table.addCell(createFinalValueCell(font, calculateFinalValue));
    }
  }
  
  private int getOptionModifiers(IWeaponStats stats) {
	  if (provider == null) {
        return 0;
      }
	  int mod = 0;
	  for (IEquipmentStatsOption option : optionProvider.getEnabledStatOptions(stats)){
		  mod += option.getAccuracyModifier();
      }
	  return mod;
  }

  protected int getFinalValue(IWeaponStats weapon, int weaponValue) {
    return calculateFinalValue(
            weaponValue + getOptionModifiers(weapon),
            collection.getTrait(AttributeType.Dexterity),
            collection.getTrait(weapon.getTraitType()));
  }
}
