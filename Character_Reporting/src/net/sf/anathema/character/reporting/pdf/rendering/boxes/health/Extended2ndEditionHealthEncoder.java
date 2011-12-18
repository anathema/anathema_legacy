package net.sf.anathema.character.reporting.pdf.rendering.boxes.health;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.IPdfTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndEditionHealthEncoder extends AbstractHealthAndMovementEncoder {

  public Extended2ndEditionHealthEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    super(resources, baseFont, symbolBaseFont);
  }

  @Override
  public String getHeaderKey(ReportContent reportContent) {
    return "Health"; //$NON-NLS-1$
  }

  @Override
  public void encode(PdfGraphics graphics, ReportContent reportContent) throws DocumentException {
    Bounds tableBounds = new Bounds(graphics.getBounds().x, graphics.getBounds().y + graphics.getBounds().height - 94f, graphics.getBounds().width,
      94f);
    IPdfTableEncoder tableEncoder = createTableEncoder();
    tableEncoder.encodeTable(graphics.getDirectContent(), reportContent, tableBounds);
    float textHeight = tableBounds.getMinY() - graphics.getBounds().y - IVoidStateFormatConstants.TEXT_PADDING;
    Bounds textBounds = new Bounds(graphics.getBounds().x, graphics.getBounds().y, graphics.getBounds().width, textHeight);
    encodeText(graphics.getDirectContent(), textBounds);
  }

  @Override
  protected IPdfTableEncoder createTableEncoder() {
    return new SecondEditionHealthTableEncoder(getResources(), getBaseFont());
  }

  @Override
  protected final IExaltedEdition getEdition() {
    return ExaltedEdition.SecondEdition;
  }
}
