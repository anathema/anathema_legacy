package net.sf.anathema.character.sidereal.reporting;

import static net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants.TEXT_PADDING;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SiderealFlawedFateEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  private final BaseFont baseFont;
  private final IResources resources;
  private final VirtueFlawBoxEncoder traitEncoder;
  private final Chunk symbolChunk;

  public SiderealFlawedFateEncoder(BaseFont baseFont, BaseFont symbolBaseFont, IResources resources) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.traitEncoder = new VirtueFlawBoxEncoder(baseFont);
    this.symbolChunk = PdfEncodingUtilities.createCaretSymbolChunk(symbolBaseFont);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    Bounds textBounds = traitEncoder.encode(directContent, bounds);
    float lineHeight = (textBounds.height - TEXT_PADDING) / 2;
    String effects = resources.getString("Sheet.GreatCurse.Sidereal.CurrentEffects") + ":"; //$NON-NLS-1$ //$NON-NLS-2$
    drawLabelledContent(
        directContent,
        effects,
        null,
        new Position(textBounds.x, textBounds.getMaxY() - lineHeight),
        bounds.width);

    Font font = TableEncodingUtilities.createFont(getBaseFont());
    Phrase phrase = new Phrase("", font); //$NON-NLS-1$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.GreatCurse.Sidereal.RulesPages")); //$NON-NLS-1$
    Bounds infoBounds = new Bounds(bounds.x, bounds.y, bounds.width, textBounds.height - lineHeight);
    PdfTextEncodingUtilities.encodeText(directContent, phrase, infoBounds, IVoidStateFormatConstants.LINE_HEIGHT - 2);
  }

  public String getHeaderKey() {
    return "Sheet.Sidereal.FlawedFate"; //$NON-NLS-1$
  }
}