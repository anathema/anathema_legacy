package net.sf.anathema.character.reporting.pdf.rendering.boxes.health;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndEditionMovementEncoder implements IBoxContentEncoder {

  private final IResources resources;
  private final BaseFont baseFont;

  public Extended2ndEditionMovementEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "Movement"; //$NON-NLS-1$
  }

  public void encode(PdfGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    ITableEncoder tableEncoder = createTableEncoder();
    tableEncoder.encodeTable(graphics.getDirectContent(), reportContent, bounds);
  }

  protected final BaseFont getBaseFont() {
    return baseFont;
  }

  protected final IResources getResources() {
    return resources;
  }

  protected ITableEncoder createTableEncoder() {
    return new Simple2ndEditionMovementTableEncoder(getResources(), getBaseFont());
  }

  protected final IExaltedEdition getEdition() {
    return ExaltedEdition.SecondEdition;
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
