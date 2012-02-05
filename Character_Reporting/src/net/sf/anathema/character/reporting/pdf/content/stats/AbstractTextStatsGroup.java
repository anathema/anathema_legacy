package net.sf.anathema.character.reporting.pdf.content.stats;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;

public abstract class AbstractTextStatsGroup<T extends IStats> implements IStatsGroup<T> {

  public final int getColumnCount() {
    return getColumnWeights().length;
  }

  protected final PdfPCell createTextCell(Font font, String text) {
    int border = StringUtilities.isNullOrTrimmedEmpty(text) ? Rectangle.BOTTOM : Rectangle.NO_BORDER;
    if (StringUtilities.isNullOrTrimmedEmpty(text)) {
      text = " "; //$NON-NLS-1$
    }
    return TableEncodingUtilities.createContentCellTable(BaseColor.BLACK, text, font, 0.5f, border, Element.ALIGN_LEFT);
  }
}
