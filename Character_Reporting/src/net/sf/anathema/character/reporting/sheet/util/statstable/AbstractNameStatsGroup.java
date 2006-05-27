package net.sf.anathema.character.reporting.sheet.util.statstable;

import java.awt.Color;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public abstract class AbstractNameStatsGroup<T extends IStats> implements IStatsGroup<T> {
  private final String title;
  private final IResources resources;

  public AbstractNameStatsGroup(IResources resources) {
    this.resources = resources;
    this.title = resources.getString(getHeaderResourceKey());
  }

  public final int getColumnCount() {
    return 1;
  }

  public final String getTitle() {
    return title;
  }

  public final Float[] getColumnWeights() {
    return new Float[] { new Float(6) };
  }

  public final void addContent(PdfPTable table, Font font, T stats) {
    if (stats == null) {
      table.addCell(createNameCell(font, "")); //$NON-NLS-1$
    }
    else {
      String resourceKey = getResourceBase() + stats.getName().getId();
      table.addCell(createNameCell(font, resources.getString(resourceKey)));
    }
  }

  protected abstract String getHeaderResourceKey();

  protected abstract String getResourceBase();

  private final PdfPCell createNameCell(Font font, String text) {
    int border = StringUtilities.isNullOrTrimEmpty(text) ? Rectangle.BOTTOM : Rectangle.NO_BORDER;
    if (StringUtilities.isNullOrTrimEmpty(text)) {
      text = " "; //$NON-NLS-1$
    }
    return TableEncodingUtilities.createContentCellTable(Color.BLACK, text, font, 0.5f, border, Element.ALIGN_LEFT);
  }
}