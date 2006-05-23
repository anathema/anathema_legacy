package net.sf.anathema.character.equipment.impl.reporting.second.armourstats;

import net.sf.anathema.character.equipment.impl.reporting.second.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IArmour;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class SoakArmourStatsGroup extends AbstractValueEquipmentStatsGroup<IArmour> implements IArmourStatsGroup {

  private String valuePrefix = ""; //$NON-NLS-1$

  public SoakArmourStatsGroup(IResources resources) {
    super(resources, "Soak"); //$NON-NLS-1$
  }

  public void addContent(PdfPTable table, Font font, IArmour armour, IGenericTrait... traits) {
    if (armour == null) {
      table.addCell(createEmptyEquipmentValueCell(font));
      table.addCell(createEmptyEquipmentValueCell(font));
      table.addCell(createEmptyEquipmentValueCell(font));
    }
    else {
      table.addCell(createEquipmentValueCell(font, armour.getSoak(HealthType.Bashing)));
      table.addCell(createEquipmentValueCell(font, armour.getSoak(HealthType.Lethal)));
      table.addCell(createEquipmentValueCell(font, armour.getSoak(HealthType.Aggravated)));
    }
    valuePrefix = "+"; //$NON-NLS-1$
  }

  public void addTotal(PdfPTable table, Font font, IArmour armour) {
    table.addCell(createFinalValueCell(font, armour.getSoak(HealthType.Bashing)));
    table.addCell(createFinalValueCell(font, armour.getSoak(HealthType.Lethal)));
    table.addCell(createFinalValueCell(font, armour.getSoak(HealthType.Aggravated)));
  }

  public int getColumnCount() {
    return 3;
  }

  @Override
  protected String getPositivePrefix() {
    return valuePrefix;
  }

  @Override
  protected String getZeroPrefix() {
    return valuePrefix;
  }
}