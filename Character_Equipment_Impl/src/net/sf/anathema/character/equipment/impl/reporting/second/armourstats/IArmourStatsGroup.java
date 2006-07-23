package net.sf.anathema.character.equipment.impl.reporting.second.armourstats;

import net.sf.anathema.character.equipment.impl.reporting.second.stats.IEquipmentStatsGroup;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public interface IArmourStatsGroup extends IEquipmentStatsGroup<IArmourStats> {

  public void addTotal(PdfPTable table, Font font, IArmourStats armour);
}