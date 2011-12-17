package net.sf.anathema.character.reporting.sheet.util;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.encoder.IPdfBoxEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfVariableContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.lib.resources.IResources;

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
    float headerPadding = IPdfBoxEncoder.HEADER_HEIGHT / 2;
    return new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - headerPadding);
  }

  public float getRequestedHeight(IPdfVariableContentBoxEncoder encoder, IGenericCharacter character, float width) {
    float boxHeight = IPdfBoxEncoder.HEADER_HEIGHT / 2f + IPdfBoxEncoder.ARCSPACE;
    return boxHeight + encoder.getRequestedHeight(character, width);
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

  public void encodeBox(PdfContentByte directContent, IPdfContentBoxEncoder encoder, IGenericCharacter character, IGenericDescription description,
                        Bounds bounds) throws DocumentException {
    encodeBox(directContent, encoder, standardBoxEncoder, character, description, bounds);
  }

  public void encodeBox(PdfContentByte directContent, IPdfContentBoxEncoder encoder, IPdfBoxEncoder boxEncoder, IGenericCharacter character,
                        IGenericDescription description, Bounds bounds) throws DocumentException {
    String header = resources.getString("Sheet.Header." + encoder.getHeaderKey(character, description)); //$NON-NLS-1$
    Bounds contentBounds = encodeBox(directContent, bounds, header, boxEncoder);
    encoder.encode(directContent, character, description, contentBounds);
  }

  private Bounds calculateInsettedBounds(Bounds contentBounds) {
    return new Bounds(contentBounds.x + CONTENT_INSET, contentBounds.y, calculateInsettedWidth(contentBounds.width),
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
