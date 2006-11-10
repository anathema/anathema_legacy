package net.sf.anathema.character.abyssal.reporting;

import java.util.List;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.traits.ValueWeightGenericTraitSorter;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class AbyssalResonanceEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {
  private final BaseFont baseFont;
  private final PdfTraitEncoder traitEncoder;
  private final IResources resources;
  private final Chunk symbolChunk;

  public AbyssalResonanceEncoder(BaseFont baseFont, BaseFont symbolBaseFont, IResources resources) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.traitEncoder = PdfTraitEncoder.createMediumTraitEncoder(baseFont);
    this.symbolChunk = PdfEncodingUtilities.createCaretSymbolChunk(symbolBaseFont);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public String getHeaderKey() {
    return "GreatCurse.Abyssal"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    float traitBaseLine = bounds.getMaxY() - traitEncoder.getTraitHeight();
    float padding = IVoidStateFormatConstants.PADDING / 2.0f;
    Position traitPosition = new Position(bounds.x + padding, traitBaseLine);
    traitEncoder.encodeSquaresCenteredAndUngrouped(directContent, traitPosition, bounds.width - 2 * padding, 0, 10);
    Bounds textBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - traitEncoder.getTraitHeight());
    Font font = TableEncodingUtilities.createFont(getBaseFont());
    Phrase phrase = new Phrase("", font); //$NON-NLS-1$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.GreatCurse.SocialPoolMessage", getMaxVirtueValue(character)) + "\n"); //$NON-NLS-1$ //$NON-NLS-2$    
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.GreatCurse.VirtueDifficulty")); //$NON-NLS-1$
    PdfTextEncodingUtilities.encodeText(directContent, phrase, textBounds, IVoidStateFormatConstants.LINE_HEIGHT - 2);
  }

  private int getMaxVirtueValue(IGenericCharacter character) {
    IGenericTrait[] virtues = character.getTraitCollection().getTraits(VirtueType.values());
    List<IGenericTrait> sortedVirtues = new ValueWeightGenericTraitSorter().sortDescending(virtues);
    return sortedVirtues.get(0).getCurrentValue();
  }
}