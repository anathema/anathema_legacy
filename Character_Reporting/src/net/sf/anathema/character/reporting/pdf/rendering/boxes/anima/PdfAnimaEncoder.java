package net.sf.anathema.character.reporting.pdf.rendering.boxes.anima;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.ListUtils;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfLineEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

import java.awt.*;

public class PdfAnimaEncoder implements IBoxContentEncoder {

  private final int fontSize;
  private final float lineHeight;
  private final BaseFont baseFont;
  private final IResources resources;
  private final BaseFont symbolBaseFont;
  private final Chunk symbolChunk;
  private final ITableEncoder tableEncoder;

  public PdfAnimaEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont, int fontSize, ITableEncoder encoder) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.symbolBaseFont = symbolBaseFont;
    this.fontSize = fontSize;
    this.lineHeight = fontSize * 1.5f;
    this.tableEncoder = encoder;
    this.symbolChunk = PdfEncodingUtilities.createCaretSymbolChunk(symbolBaseFont);
  }

  public String getHeaderKey(ReportContent content) {
    return "Anima"; //$NON-NLS-1$
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    float powerHeight = bounds.getHeight() - AnimaTableEncoder.TABLE_HEIGHT - IVoidStateFormatConstants.TEXT_PADDING / 2f;
    Bounds animaPowerBounds = new Bounds(bounds.getMinX(), bounds.getMaxY() - powerHeight, bounds.getWidth(), powerHeight);
    encodeAnimaPowers(graphics, reportContent.getCharacter(), animaPowerBounds);

    Bounds animaTableBounds = new Bounds(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), AnimaTableEncoder.TABLE_HEIGHT);
    tableEncoder.encodeTable(graphics, reportContent, animaTableBounds);
  }

  private void encodeAnimaPowers(SheetGraphics graphics, IGenericCharacter character, Bounds bounds) throws DocumentException {
    Phrase phrase = new Phrase("", new Font(baseFont, fontSize, Font.NORMAL, Color.BLACK)); //$NON-NLS-1$
    PdfContentByte directContent = graphics.getDirectContent();
    // Add standard powers for character type
    ICharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    ListUtils.addBulletedListText(resources, symbolChunk, character.getRules().getEdition(), "Sheet.AnimaPower." + characterType.getId(),
      //$NON-NLS-1$
      phrase, false);

    String casteResourceKey = "Sheet.AnimaPower." + character.getCasteType().getId() + "." + character.getRules().getEdition().getId();
    //$NON-NLS-1$ //$NON-NLS-2$
    boolean isCastePowerDefined = resources.supportsKey(casteResourceKey);
    if (isCastePowerDefined) {
      phrase.add(symbolChunk);
      phrase.add(resources.getString(casteResourceKey) + "\n"); //$NON-NLS-1$
    }
    phrase.add(symbolChunk);
    float yPosition = graphics.encodeText(phrase, bounds, lineHeight, Element.ALIGN_LEFT).getYLine();
    Position lineStartPosition = new Position((bounds.getMinX() + PdfEncodingUtilities.getCaretSymbolWidth(symbolBaseFont)), yPosition);
    PdfLineEncodingUtilities.encodeHorizontalLines(directContent, lineStartPosition, bounds.getMinX(), bounds.getMaxX(), lineHeight,
      1 + (int) ((yPosition - bounds.getMinY()) / lineHeight));
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
