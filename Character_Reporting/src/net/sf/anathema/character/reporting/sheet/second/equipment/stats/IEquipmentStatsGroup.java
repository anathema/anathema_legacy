package net.sf.anathema.character.reporting.sheet.second.equipment.stats;

import net.sf.anathema.character.generic.equipment.weapon.IEquipment;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public interface IEquipmentStatsGroup <T extends IEquipment> {

  public int getColumnCount();

  public String getTitle();
  
  public Float[] getColumnWeights();

  public void addContent(PdfPTable table, Font font, T equipment);
}