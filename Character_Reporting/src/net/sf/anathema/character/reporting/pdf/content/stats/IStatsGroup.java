package net.sf.anathema.character.reporting.pdf.content.stats;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.util.IStats;

public interface IStatsGroup<T extends IStats> {

  int getColumnCount();

  String getTitle();

  Float[] getColumnWeights();

  void addContent(PdfPTable table, Font font, T stats);
}
