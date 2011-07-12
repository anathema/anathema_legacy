package net.sf.anathema.character.equipment.impl.reporting;

import net.sf.anathema.character.equipment.impl.reporting.second.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public abstract class AbstractDefenceWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeaponStats> {

  private final IGenericCharacter character;
  private final IGenericTraitCollection traitCollection;
  private final IEquipmentModifiers equipment;

  public AbstractDefenceWeaponStatsGroup(IResources resources, IGenericCharacter character, IEquipmentModifiers equipment) {
    super(resources, "Defence"); //$NON-NLS-1$
    this.character = character;
    this.traitCollection = null;
    this.equipment = equipment;
  }
  
  public AbstractDefenceWeaponStatsGroup(IResources resources,
		  IGenericCharacter character,
		  IGenericTraitCollection traitCollection,
		  IEquipmentModifiers equipment) {
	    super(resources, "Defence"); //$NON-NLS-1$
	    this.character = character;
	    this.traitCollection = traitCollection;
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
      table.addCell(createEquipmentValueCell(font, weapon.getDefence()));
      if (weapon.getDefence() == null) {
        table.addCell(createFinalValueCell(font, (Integer) null));
      }
      else {
        table.addCell(createFinalValueCell(font, getDefenceValue(weapon, equipment)));
      }
    }
  }

  protected abstract int getDefenceValue(IWeaponStats weapon, IEquipmentModifiers equipment);

  protected final IGenericCharacter getCharacter() {
    return character;
  }
  
  protected final IGenericTraitCollection getTraitCollection()
  {
	return traitCollection == null ? character.getTraitCollection() : traitCollection;
  }
}