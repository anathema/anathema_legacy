package net.sf.anathema.character.reporting.sheet.common.anima;

import java.awt.Color;
import java.io.IOException;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfLineEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfAnimaEncoder extends AbstractPdfEncoder implements IPdfContentEncoder {

  private static final int FONT_SIZE = IVoidStateFormatConstants.FONT_SIZE - 1;
  private static final float LINE_HEIGHT = FONT_SIZE * 1.5f;
  private static final String SYMBOL = "\u00A8  "; //$NON-NLS-1$
  private final BaseFont baseFont;
  private final IResources resources;

  public PdfAnimaEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds)
      throws DocumentException,
      IOException {
    float halfWidth = bounds.getHeight() / 2;
    Bounds animaPowerBounds = new Bounds(bounds.getMinX(), bounds.getCenterY(), bounds.getWidth(), halfWidth);
    Position lineStartPosition = encodeAnimaPowers(directContent, character, animaPowerBounds);
    encodeLines(directContent, bounds, lineStartPosition);
    Bounds animaTableBounds = new Bounds(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), halfWidth);
    new SolarAnimaTableEncoder(resources, baseFont, FONT_SIZE).encodeTable(directContent, animaTableBounds);
  }

  private void encodeLines(PdfContentByte directContent, Bounds bounds, Position lineStartPosition) {
    float minX = bounds.getMinX();
    float maxX = bounds.getMaxX();
    PdfLineEncodingUtilities.encodeHorizontalLines(directContent, lineStartPosition, minX, maxX, LINE_HEIGHT, 3);
  }

  private Position encodeAnimaPowers(PdfContentByte directContent, IGenericCharacter character, Bounds bounds)
      throws DocumentException,
      IOException {
    BaseFont symbolBaseFont =  PdfTextEncodingUtilities.createBaseFont(BaseFont.SYMBOL);
    Phrase phrase = new Phrase("", new Font(baseFont, FONT_SIZE, Font.NORMAL, Color.BLACK)); //$NON-NLS-1$
    addAnimaPowerText(character, phrase, new Font(symbolBaseFont, FONT_SIZE, Font.NORMAL, Color.BLACK));
    float yPosition = PdfTextEncodingUtilities.encodeText(directContent, phrase, bounds, LINE_HEIGHT);
    return new Position((bounds.getMinX() + symbolBaseFont.getWidthPoint(SYMBOL, FONT_SIZE)), yPosition);
  }

  private void addAnimaPowerText(IGenericCharacter character, Phrase phrase, Font symbolFont) {
    CharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    Chunk symbolChunk = new Chunk(SYMBOL, symbolFont);
    String resourceBase = "Sheet.AnimaPower." + characterType.getId() + "."; //$NON-NLS-1$ //$NON-NLS-2$
    for (String resourceId : new String[] { "First", "Second", "Third" }) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      phrase.add(symbolChunk);
      phrase.add(resources.getString(resourceBase + resourceId) + "\n"); //$NON-NLS-1$
    }
    phrase.add(symbolChunk);
  }
}