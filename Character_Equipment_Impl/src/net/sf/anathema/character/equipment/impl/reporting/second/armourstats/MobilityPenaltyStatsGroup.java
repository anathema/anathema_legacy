package net.sf.anathema.character.equipment.impl.reporting.second.armourstats;

import net.sf.anathema.character.equipment.impl.reporting.second.stats.AbstractValueEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IArmour;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class MobilityPenaltyStatsGroup extends AbstractValueEquipmentStatsGroup<IArmour> {

  public MobilityPenaltyStatsGroup(IResources resources) {
    super(resources, "MobilityPenalty"); //$NON-NLS-1$
  }

  public int getColumnCount() {
    return 1;
  }

  public void addContent(PdfPTable table, Font font, IGenericTrait trait, IArmour armour) {
    if (armour == null) {
      table.addCell(createEmptyEquipmentValueCell(font));
    }
    else {
      table.addCell(createEquipmentValueCell(font, armour.getMobilityPenalty()));
    }
  }

  @Override
  protected String getZeroPrefix() {
    return "-"; //$NON-NLS-1$
  }
}