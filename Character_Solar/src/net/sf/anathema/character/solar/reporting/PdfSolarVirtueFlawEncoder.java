package net.sf.anathema.character.solar.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.character.solar.virtueflaw.model.ISolarVirtueFlaw;
import net.sf.anathema.character.solar.virtueflaw.presenter.ISolarVirtueFlawModel;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfSolarVirtueFlawEncoder extends AbstractPdfEncoder implements IPdfContentEncoder {

  private final IResources resources;
  private final BaseFont baseFont;
  private final PdfTraitEncoder traitEncoder;

  public PdfSolarVirtueFlawEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.traitEncoder = PdfTraitEncoder.createMediumTraitEncoder(baseFont);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    float traitBaseLine = bounds.getMaxY() - traitEncoder.getTraitHeight();
    float padding = IVoidStateFormatConstants.PADDING / 2.0f;
    Position traitPosition = new Position(bounds.x + padding, traitBaseLine);
    traitEncoder.encodeSquaresCenteredAndUngrouped(directContent, traitPosition, bounds.width - 2 * padding, 0, 10);
    Phrase phrase = new Phrase();
    Font nameFont = createNameFont();
    ISolarVirtueFlawModel flawModel = (ISolarVirtueFlawModel) character.getAdditionalModel(IAdditionalTemplate.SOLAR_VIRTUE_FLAW_ID);
    ISolarVirtueFlaw virtueFlaw = flawModel.getVirtueFlaw();
    phrase.add(new Chunk(virtueFlaw.getName().getText() + ": ", nameFont)); //$NON-NLS-1$
    phrase.add(new Chunk(virtueFlaw.getLimitBreak().getText(), createFont()));
  }

  private Font createNameFont() {
    Font nameFont = createFont();
    nameFont.setStyle(Font.BOLD);
    return nameFont;
  }

  private Font createFont() {
    return TableEncodingUtilities.createFont(getBaseFont());
  }
}