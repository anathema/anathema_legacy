package net.sf.anathema.character.reporting.stats;

import net.sf.anathema.character.generic.util.IStats;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public interface IStatsGroup<T extends IStats> {

  public int getColumnCount();

  public String getTitle();

  public Float[] getColumnWeights();

  public void addContent(PdfPTable table, Font font, T stats);
}