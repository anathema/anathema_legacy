package net.sf.anathema.character.reporting.extended.common.willpower;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.extended.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.ListUtils;
import net.sf.anathema.character.reporting.common.PdfEncodingUtilities;
import net.sf.anathema.character.reporting.common.Position;
import net.sf.anathema.lib.resources.IResources;

import java.awt.*;

public class NewPdfWillpowerEncoder implements IPdfContentBoxEncoder {

  private PdfTraitEncoder traitEncoder;
  private final IResources resources;
  private final BaseFont baseFont;
  private final Chunk symbolChunk;
  
  private final float fontSize = 5f;
  private final float lineHeight = 1.1f * fontSize;

  public NewPdfWillpowerEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    this.traitEncoder = PdfTraitEncoder.createMediumTraitEncoder(baseFont);
    this.resources = resources;
    this.baseFont = baseFont;
    this.symbolChunk = PdfEncodingUtilities.createCaretSymbolChunk(symbolBaseFont);
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Willpower"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds contentBounds) throws DocumentException {
    float padding = IVoidStateFormatConstants.PADDING / 2f;
    float width = contentBounds.width - 2f * padding;
    float leftX = contentBounds.x + padding;
    float height = contentBounds.height - padding;
    float topY = contentBounds.getMaxY();
    
    int value = character.getTraitCollection().getTrait(OtherTraitType.Willpower).getCurrentValue();
    float entryHeight = traitEncoder.getTraitHeight();
    float yPosition = topY - entryHeight;
    traitEncoder.encodeDotsCenteredAndUngrouped(directContent, new Position(leftX, yPosition), width, value, 10);
    yPosition -= entryHeight;
    traitEncoder.encodeSquaresCenteredAndUngrouped(directContent, new Position(leftX, yPosition), width, 0, 10);
    height -= 2f * entryHeight;
    
    yPosition -= lineHeight;
    height -= lineHeight;

    float columnPadding = padding / 2f;
    float columnWidth = (width - columnPadding) / 2f;
    
    Bounds spendingBounds = new Bounds(leftX, yPosition - height, columnWidth, height);
    Phrase spendingPhrase = new Phrase("", new Font(baseFont, fontSize, //$NON-NLS-1$
                                                    Font.NORMAL, Color.BLACK));
    ListUtils.addBulletedListText(resources, symbolChunk,
      character.getRules().getEdition(),
      "Sheet.WillpowerSpendingRules", //$NON-NLS-1$
      spendingPhrase, true);
    spendingPhrase.add("\n"); //$NON-NLS-1$
    String spendingNote = ListUtils.getRequiredString(resources, "Sheet.WillpowerSpendingNote", character.getRules().getEdition());
    spendingPhrase.add(spendingNote + "\n"); //$NON-NLS-1$
    PdfTextEncodingUtilities.encodeText(directContent, spendingPhrase,
                                        spendingBounds, lineHeight).getYLine();
    
    float centerX = leftX + columnWidth + columnPadding;
    Bounds regainingBounds = new Bounds(centerX, yPosition - height, columnWidth, height);
    Phrase regainingPhrase = new Phrase("", new Font(baseFont, fontSize, //$NON-NLS-1$
                                                     Font.NORMAL, Color.BLACK));
    ListUtils.addBulletedListText(resources, symbolChunk,
                                  character.getRules().getEdition(),
                                  "Sheet.WillpowerRegainingRules", //$NON-NLS-1$
                                  regainingPhrase, true);
    PdfTextEncodingUtilities.encodeText(directContent, regainingPhrase,
                                        regainingBounds, lineHeight).getYLine();
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}
