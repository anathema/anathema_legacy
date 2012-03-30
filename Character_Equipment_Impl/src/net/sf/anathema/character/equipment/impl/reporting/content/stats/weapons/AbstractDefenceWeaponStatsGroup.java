package net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.equipment.impl.reporting.content.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractDefenceWeaponStatsGroup extends AbstractValueEquipmentStatsGroup<IWeaponStats> {

  private final IGenericCharacter character;
  private final IGenericTraitCollection traitCollection;

  public AbstractDefenceWeaponStatsGroup(IResources resources, IGenericCharacter character,
                                         IGenericTraitCollection traitCollection) {
    super(resources, "Defence"); //$NON-NLS-1$
    this.character = character;
    this.traitCollection = traitCollection;
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
      table.addCell(createEquipmentValueCell(font, weapon.getDefence()));
      if (weapon.getDefence() == null) {
        table.addCell(createFinalValueCell(font, (Integer) null));
      } else {
        table.addCell(createFinalValueCell(font, getDefenceValue(weapon)));
      }
    }
  }

  protected abstract int getDefenceValue(IWeaponStats weapon);

  protected final IGenericCharacter getCharacter() {
    return character;
  }

  protected final IGenericTraitCollection getTraitCollection() {
    return traitCollection == null ? character.getTraitCollection() : traitCollection;
  }
}