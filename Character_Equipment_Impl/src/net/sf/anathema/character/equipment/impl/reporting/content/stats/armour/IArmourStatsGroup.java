package net.sf.anathema.character.equipment.impl.reporting.content.stats.armour;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;

public interface IArmourStatsGroup {

  public void addTotal(PdfPTable table, Font font, IArmourStats armour);
}
