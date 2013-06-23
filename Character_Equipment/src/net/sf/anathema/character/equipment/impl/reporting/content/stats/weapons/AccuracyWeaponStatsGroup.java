package net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.EquipmentOptionsProvider;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.resources.Resources;

import java.util.Arrays;

public class AccuracyWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeaponStats> {

  private final IGenericTraitCollection collection;
  private final EquipmentHeroEvaluator provider;
  private EquipmentOptionsProvider optionProvider;

  public AccuracyWeaponStatsGroup(Resources resources, IGenericTraitCollection collection, EquipmentHeroEvaluator provider,
                                  EquipmentOptionsProvider optionProvider) {
    super(resources, "Accuracy");
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
    for (IEquipmentStatsOption option : optionProvider.getEnabledStatOptions(stats)) {
      mod += option.getAccuracyModifier();
    }
    return mod;
  }

  protected int getFinalValue(IWeaponStats weapon, int weaponValue) {
    GenericTrait trait = collection.getTrait(AttributeType.Dexterity);
    if (Arrays.asList(weapon.getTags()).contains(WeaponTag.ClinchEnhancer)) {
      GenericTrait str = collection.getTrait(AttributeType.Strength);
      if (trait.getCurrentValue() < str.getCurrentValue()) {
        trait = str;
      }
    }
    return calculateFinalValue(weaponValue + getOptionModifiers(weapon), trait, collection.getTrait(weapon.getTraitType()));
  }
}
