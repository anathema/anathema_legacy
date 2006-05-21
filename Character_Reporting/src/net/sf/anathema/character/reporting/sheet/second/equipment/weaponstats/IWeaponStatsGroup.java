package net.sf.anathema.character.reporting.sheet.second.equipment.weaponstats;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public interface IWeaponStatsGroup {

  public int getColumnCount();

  public String getTitle();
  
  public Float[] getColumnWeights();

  public void addContent(PdfPTable table, Font font);
}