package net.sf.anathema.character.reporting.pdf.content.stats;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;

import java.awt.*;

public abstract class AbstractTextStatsGroup<T extends IStats> implements IStatsGroup<T> {

  public final int getColumnCount() {
    return getColumnWeights().length;
  }

  protected final PdfPCell createTextCell(Font font, String text) {
    int border = StringUtilities.isNullOrTrimEmpty(text) ? Rectangle.BOTTOM : Rectangle.NO_BORDER;
    if (StringUtilities.isNullOrTrimEmpty(text)) {
      text = " "; //$NON-NLS-1$
    }
    return TableEncodingUtilities.createContentCellTable(Color.BLACK, text, font, 0.5f, border, Element.ALIGN_LEFT);
  }
}
