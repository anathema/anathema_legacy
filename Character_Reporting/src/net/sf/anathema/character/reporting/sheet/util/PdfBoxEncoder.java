package net.sf.anathema.character.reporting.sheet.util;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfBoxEncoder extends AbstractPdfEncoder {

  public static final int CONTENT_INSET = 5;

  private BaseFont baseFont;
  private final IResources resources;
  private final PdfHeaderBoxEncoder headerBoxEncoder;
  private final IPdfBoxEncoder standardBoxEncoder;

  public PdfBoxEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.headerBoxEncoder = new PdfHeaderBoxEncoder(baseFont);
    this.standardBoxEncoder = new StandardBoxEncoder(baseFont);
  }

  public Bounds calculateContentBounds(Bounds bounds) {
    Bounds contentBoxBounds = calculateContentBoxBounds(bounds);
    return calculateInsettedBounds(contentBoxBounds);
  }

  private Bounds calculateContentBoxBounds(Bounds bounds) {
    int headerPadding = IPdfBoxEncoder.HEADER_HEIGHT / 2;
    return new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - headerPadding);
  }

  private Bounds encodeBox(PdfContentByte directContent, Bounds bounds, String title, IPdfBoxEncoder boxEncoder) {
    Bounds contentBounds = calculateContentBoxBounds(bounds);
    boxEncoder.encodeContentBox(directContent, contentBounds);
    headerBoxEncoder.encodeHeaderBox(directContent, bounds, title);
    return calculateInsettedBounds(contentBounds);
  }

  public Bounds encodeBox(PdfContentByte directContent, Bounds bounds, String title) {
    return encodeBox(directContent, bounds, title, standardBoxEncoder);
  }

  public void encodeBox(
      PdfContentByte directContent,
      IPdfContentBoxEncoder encoder,
      IGenericCharacter character,
      Bounds bounds) throws DocumentException {
    encodeBox(directContent, encoder, standardBoxEncoder, character, bounds);
  }

  public void encodeBox(
      PdfContentByte directContent,
      IPdfContentBoxEncoder encoder,
      IPdfBoxEncoder boxEncoder,
      IGenericCharacter character,
      Bounds bounds) throws DocumentException {
    String header = resources.getString("Sheet.Header." + encoder.getHeaderKey()); //$NON-NLS-1$
    Bounds contentBounds = encodeBox(directContent, bounds, header, boxEncoder);
    encoder.encode(directContent, character, contentBounds);
  }

  private Bounds calculateInsettedBounds(Bounds contentBounds) {
    return new Bounds(
        contentBounds.x + CONTENT_INSET,
        contentBounds.y,
        calculateInsettedWidth(contentBounds.width),
        contentBounds.height - IPdfBoxEncoder.ARCSPACE);
  }

  public float calculateInsettedWidth(float width) {
    return width - 2 * CONTENT_INSET;
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }
}