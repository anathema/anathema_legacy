package net.sf.anathema.character.reporting.sheet.second.equipment.stats;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public interface IEquipmentStatsGroup {

  public int getColumnCount();

  public String getTitle();
  
  public Float[] getColumnWeights();

  public void addContent(PdfPTable table, Font font);
}