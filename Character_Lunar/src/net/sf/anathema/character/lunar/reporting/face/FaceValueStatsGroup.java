package net.sf.anathema.character.lunar.reporting.face;

import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractTextStatsGroup;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class FaceValueStatsGroup extends AbstractTextStatsGroup<IFaceStats> {

  public void addContent(PdfPTable table, Font font, IFaceStats stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, "")); //$NON-NLS-1$
    }
    else {
      table.addCell(createTextCell(font, stats.getValue()));
    }
  }

  public Float[] getColumnWeights() {
    return new Float[] { new Float(2) };
  }

  public String getTitle() {
    return "Sheet.Lunar.Face.Value";
  }
}