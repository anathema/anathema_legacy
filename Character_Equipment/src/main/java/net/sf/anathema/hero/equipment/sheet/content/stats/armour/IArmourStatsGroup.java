package net.sf.anathema.hero.equipment.sheet.content.stats.armour;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IArmourStats;

public interface IArmourStatsGroup {

  void addTotal(PdfPTable table, Font font, IArmourStats armour);
}
