package net.sf.anathema.character.solar.reporting;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.elements.Line;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.character.solar.virtueflaw.model.ISolarVirtueFlaw;
import net.sf.anathema.character.solar.virtueflaw.presenter.ISolarVirtueFlawModel;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfSolarVirtueFlawEncoder extends AbstractPdfEncoder implements IPdfContentEncoder {

  private final BaseFont baseFont;
  private final PdfTraitEncoder traitEncoder;
  private final Font nameFont = createNameFont();
  private final Font font = createFont();

  public PdfSolarVirtueFlawEncoder(BaseFont baseFont) {
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
    ISolarVirtueFlawModel flawModel = (ISolarVirtueFlawModel) character.getAdditionalModel(IAdditionalTemplate.SOLAR_VIRTUE_FLAW_ID);
    ISolarVirtueFlaw virtueFlaw = flawModel.getVirtueFlaw();
    String name = virtueFlaw.getName().getText();
    String condition = virtueFlaw.getLimitBreak().getText();
    boolean nameDefined = !StringUtilities.isNullOrTrimEmpty(name);
    boolean conditionDefined = !StringUtilities.isNullOrEmpty(condition);
    int leading = IVoidStateFormatConstants.LINE_HEIGHT - 2;
    Bounds textBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - traitEncoder.getTraitHeight());
    if (!nameDefined && !conditionDefined) {
      encodeLines(directContent, bounds, leading, textBounds.getMaxY());
    }
    if (nameDefined && conditionDefined) {
      Phrase phrase = new Phrase();
      phrase.add(new Chunk(name, nameFont));
      phrase.add(new Chunk(": ", nameFont)); //$NON-NLS-1$
      phrase.add(new Chunk(condition, font));
      PdfTextEncodingUtilities.encodeText(directContent, phrase, textBounds, leading);
    }
    if (nameDefined && !conditionDefined) {
      Phrase phrase = new Phrase();
      phrase.add(new Chunk(name, nameFont));
      ColumnText columnText = PdfTextEncodingUtilities.encodeText(directContent, phrase, textBounds, leading);
      float baseLine = columnText.getYLine();
      encodeLines(directContent, bounds, leading, baseLine);
    }
    if (!nameDefined && conditionDefined) {
      Phrase phrase = new Phrase();
      Font undefinedFont = new Font(nameFont);
      undefinedFont.setStyle(Font.UNDERLINE);
      phrase.add(new Chunk("                                          ", undefinedFont)); //$NON-NLS-1$
      phrase.add(new Chunk(": ", nameFont)); //$NON-NLS-1$
      phrase.add(new Chunk(condition, font));
      PdfTextEncodingUtilities.encodeText(directContent, phrase, textBounds, leading);
    }
  }

  private void encodeLines(PdfContentByte directContent, Bounds bounds, int leading, float yPosition) {
    yPosition -= leading;
    while (yPosition > bounds.getMinY()) {
      Line.createHorizontalByCoordinate(new Position(bounds.x, yPosition), bounds.getMaxX()).encode(directContent);
      yPosition -= leading;
    }
  }

  private Font createNameFont() {
    Font newFont = createFont();
    newFont.setStyle(Font.BOLD);
    return newFont;
  }

  private Font createFont() {
    return TableEncodingUtilities.createFont(getBaseFont());
  }
}