package net.sf.anathema.character.equipment.impl.reporting.stats.armour;

import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public interface IArmourStatsGroup {

  public void addTotal(PdfPTable table, Font font, IArmourStats armour);
}