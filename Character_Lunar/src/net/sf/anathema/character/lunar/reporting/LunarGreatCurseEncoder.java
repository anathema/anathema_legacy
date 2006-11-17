package net.sf.anathema.character.lunar.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawTemplate;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
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

public class LunarGreatCurseEncoder implements IPdfContentBoxEncoder {

  private final PdfTraitEncoder traitEncoder;
  private final Chunk symbolChunk;
  private Font font;
  private Font nameFont;
  private final IResources resources;

  public LunarGreatCurseEncoder(BaseFont baseFont, BaseFont symbolBaseFont, IResources resources) {
    this.resources = resources;
    this.font = createFont(baseFont);
    this.nameFont = createNameFont(baseFont);
    this.traitEncoder = PdfTraitEncoder.createMediumTraitEncoder(baseFont);
    this.symbolChunk = PdfEncodingUtilities.createCaretSymbolChunk(symbolBaseFont);
  }

  private Font createNameFont(BaseFont baseFont) {
    Font newFont = createFont(baseFont);
    newFont.setStyle(Font.BOLD);
    return newFont;
  }

  private Font createFont(BaseFont baseFont) {
    return TableEncodingUtilities.createFont(baseFont);
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    float traitBaseLine = bounds.getMaxY() - traitEncoder.getTraitHeight();
    float padding = IVoidStateFormatConstants.PADDING / 2.0f;
    Position traitPosition = new Position(bounds.x + padding, traitBaseLine);
    traitEncoder.encodeSquaresCenteredAndUngrouped(directContent, traitPosition, bounds.width - 2 * padding, 0, 10);
    IVirtueFlawModel flawModel = (IVirtueFlawModel) character.getAdditionalModel(LunarVirtueFlawTemplate.TEMPLATE_ID);
    IVirtueFlaw virtueFlaw = flawModel.getVirtueFlaw();
    int leading = IVoidStateFormatConstants.LINE_HEIGHT - 2;
    Bounds textBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - traitEncoder.getTraitHeight());
    Phrase phrase = new Phrase();
    String virtue;
    ITraitType rootVirtue = virtueFlaw.getRoot();
    if (rootVirtue != null) {
      String name = virtueFlaw.getName().getText();
      phrase.add(new Chunk(name + "\n", nameFont)); //$NON-NLS-1$
      virtue = resources.getString(rootVirtue.getId());
    }
    else {
      virtue = resources.getString("Sheet.GreatCurse.Lunar.Virtue"); //$NON-NLS-1$
      phrase.add(symbolChunk);
    }
    phrase.add(new Chunk(resources.getString("Sheet.GreatCurse.Lunar.GainMessage", virtue) + "\n", font)); //$NON-NLS-1$//$NON-NLS-2$
    if (rootVirtue == null) {
      phrase.add(symbolChunk);
      phrase.add(new Chunk("See pages 109 and 110 for full rules.", font)); //$NON-NLS-1$      
    }
    PdfTextEncodingUtilities.encodeText(directContent, phrase, textBounds, leading);
  }

  public String getHeaderKey() {
    return "GreatCurse.Lunar"; //$NON-NLS-1$
  }
}