package net.sf.anathema.character.lunar.reporting.rendering.knacks;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class KnackEncoder implements IBoxContentEncoder {

  private final IResources resources;
  private final BaseFont baseFont;

  public KnackEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  private KnackContent getKnackContent(ReportContent reportContent) {
    IGenericCharacter character = reportContent.getCharacter();
    return new KnackContent(resources, character);
  }

  @Override
  public String getHeaderKey(ReportContent content) {
    KnackContent knackContent = getKnackContent(content);
    return knackContent.getHeaderKey();
  }

  @Override
  public void encode(PdfGraphics graphics, ReportContent reportContent) throws DocumentException {
    KnackTableEncoder encoder = new KnackTableEncoder(resources, baseFont);
    PdfContentByte directContent = graphics.getDirectContent();
    Bounds bounds = graphics.getBounds();
    encoder.encodeTable(directContent, reportContent, bounds);
  }

  @Override
  public boolean hasContent(ReportContent content) {
    KnackContent knackContent = getKnackContent(content);
    return knackContent.hasContent();
  }
}
