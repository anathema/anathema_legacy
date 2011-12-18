package net.sf.anathema.character.reporting.pdf.content.stats;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import net.sf.anathema.character.generic.util.IStats;

public interface IStatsGroup<T extends IStats> {

  public int getColumnCount();

  public String getTitle();

  public Float[] getColumnWeights();

  public void addContent(PdfPTable table, Font font, T stats);
}
