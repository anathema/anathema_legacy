package net.sf.anathema.character.reporting.pdf.rendering.boxes.health;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndEditionHealthEncoder extends AbstractHealthAndMovementEncoder {

  public Extended2ndEditionHealthEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    super(resources, baseFont, symbolBaseFont);
  }

  @Override
  public String getHeaderKey(ReportContent content) {
    return "Health"; //$NON-NLS-1$
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    Bounds tableBounds = new Bounds(bounds.x, bounds.y + bounds.height - 94f, bounds.width, 94f);
    ITableEncoder tableEncoder = createTableEncoder();
    tableEncoder.encodeTable(graphics, reportContent, tableBounds);
    float textHeight = tableBounds.getMinY() - bounds.y - IVoidStateFormatConstants.TEXT_PADDING;
    Bounds textBounds = new Bounds(bounds.x, bounds.y, bounds.width, textHeight);
    encodeText(graphics, textBounds);
  }

  @Override
  protected ITableEncoder createTableEncoder() {
    return new SecondEditionHealthTableEncoder(getResources(), getBaseFont());
  }

  @Override
  protected final IExaltedEdition getEdition() {
    return ExaltedEdition.SecondEdition;
  }
}
