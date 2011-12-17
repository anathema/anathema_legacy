package net.sf.anathema.character.reporting.extended.common.anima;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.common.ListUtils;
import net.sf.anathema.character.reporting.extended.common.PdfEncodingUtilities;
import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.extended.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.encoder.IPdfTableEncoder;
import net.sf.anathema.character.reporting.extended.util.PdfLineEncodingUtilities;
import net.sf.anathema.character.reporting.extended.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import java.awt.*;

public class PdfAnimaEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  private final int fontSize;
  private final float lineHeight;
  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;
  private final IResources resources;
  private final Chunk symbolChunk;
  private final IPdfTableEncoder tableEncoder;

  public PdfAnimaEncoder(IResources resources,
                         BaseFont baseFont,
                         BaseFont symbolBaseFont,
                         int fontSize,
                         IPdfTableEncoder encoder) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.symbolBaseFont = symbolBaseFont;
    this.fontSize = fontSize;
    this.lineHeight = fontSize * 1.5f;
    this.tableEncoder = encoder;
    this.symbolChunk = PdfEncodingUtilities.createCaretSymbolChunk(baseFont);
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Anima"; //$NON-NLS-1$
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
    float powerHeight = bounds.getHeight() - AnimaTableEncoder.TABLE_HEIGHT - IVoidStateFormatConstants.TEXT_PADDING / 2f;
    Bounds animaPowerBounds = new Bounds(bounds.getMinX(),
                                         bounds.getMaxY() - powerHeight,
                                         bounds.getWidth(),
                                         powerHeight);
    encodeAnimaPowers(directContent, character, animaPowerBounds);

    Bounds animaTableBounds = new Bounds(bounds.getMinX(),
                                         bounds.getMinY(),
                                         bounds.getWidth(),
                                         AnimaTableEncoder.TABLE_HEIGHT);
    tableEncoder.encodeTable(directContent, character, animaTableBounds);
  }

  private void encodeAnimaPowers(PdfContentByte directContent, IGenericCharacter character, Bounds bounds)
      throws DocumentException {
    Phrase phrase = new Phrase("", new Font(baseFont, fontSize, Font.NORMAL, Color.BLACK)); //$NON-NLS-1$
    
    // Add standard powers for character type
    ICharacterType characterType = character.getTemplate().getTemplateType().getCharacterType();
    ListUtils.addBulletedListText(resources, symbolChunk,
                                  character.getRules().getEdition(),
                                  "Sheet.AnimaPower." + characterType.getId(), //$NON-NLS-1$
                                  phrase, false);
    
    String casteResourceKey = "Sheet.AnimaPower." + character.getCasteType().getId() + "." + character.getRules().getEdition().getId(); //$NON-NLS-1$ //$NON-NLS-2$
    boolean isCastePowerDefined = resources.supportsKey(casteResourceKey);
    if (isCastePowerDefined) {
      phrase.add(symbolChunk);
      phrase.add(resources.getString(casteResourceKey) + "\n"); //$NON-NLS-1$
    }
    phrase.add(symbolChunk);
    float yPosition = PdfTextEncodingUtilities.encodeText(directContent, phrase,
                                                          bounds, lineHeight).getYLine();
    Position lineStartPosition = new Position((bounds.getMinX() + PdfEncodingUtilities.getCaretSymbolWidth(symbolBaseFont)),
                                              yPosition);
    PdfLineEncodingUtilities.encodeHorizontalLines(directContent,
                                                   lineStartPosition,
                                                   bounds.getMinX(),
                                                   bounds.getMaxX(),
                                                   lineHeight,
                                                   1 + (int) ((yPosition - bounds.getMinY()) / lineHeight));
  }

  public boolean hasContent(IGenericCharacter character) {
	  return true;
  }
}