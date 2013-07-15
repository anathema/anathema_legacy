package net.sf.anathema.hero.sheet.pdf.content.stats;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import net.sf.anathema.character.main.util.IStats;
import net.sf.anathema.hero.sheet.pdf.encoder.table.TableEncodingUtilities;
import net.sf.anathema.lib.lang.StringUtilities;

public abstract class AbstractTextStatsGroup<T extends IStats> implements IStatsGroup<T> {

  @Override
  public final int getColumnCount() {
    return getColumnWeights().length;
  }

  protected final PdfPCell createTextCell(Font font, String text) {
    int border = StringUtilities.isNullOrTrimmedEmpty(text) ? Rectangle.BOTTOM : Rectangle.NO_BORDER;
    if (StringUtilities.isNullOrTrimmedEmpty(text)) {
      text = " ";
    }
    return TableEncodingUtilities.createContentCellTable(BaseColor.BLACK, text, font, 0.5f, border, Element.ALIGN_LEFT);
  }
}
