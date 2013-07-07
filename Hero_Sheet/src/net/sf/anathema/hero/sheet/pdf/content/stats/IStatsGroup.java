package net.sf.anathema.hero.sheet.pdf.content.stats;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.main.util.IStats;

public interface IStatsGroup<T extends IStats> {

  int getColumnCount();

  String getTitle();

  Float[] getColumnWeights();

  void addContent(PdfPTable table, Font font, T stats);
}
