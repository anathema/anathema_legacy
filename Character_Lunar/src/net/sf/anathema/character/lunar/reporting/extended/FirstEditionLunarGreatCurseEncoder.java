package net.sf.anathema.character.lunar.reporting.extended;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawTemplate;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.PdfEncodingUtilities;
import net.sf.anathema.character.reporting.common.encoder.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.common.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.common.TableEncodingUtilities;
import net.sf.anathema.character.reporting.common.*;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionLunarGreatCurseEncoder implements IPdfContentBoxEncoder {

  private final VirtueFlawBoxEncoder traitEncoder;
  private final Chunk symbolChunk;
  private final Font font;
  private final Font nameFont;
  private final IResources resources;

  public FirstEditionLunarGreatCurseEncoder(BaseFont baseFont, BaseFont symbolBaseFont, IResources resources) {
    this.resources = resources;
    this.font = createFont(baseFont);
    this.nameFont = createNameFont(baseFont);
    this.traitEncoder = new VirtueFlawBoxEncoder(baseFont);
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

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
    IVirtueFlaw virtueFlaw = ((IVirtueFlawModel) character.getAdditionalModel(LunarVirtueFlawTemplate.TEMPLATE_ID)).getVirtueFlaw();
    Bounds textBounds = traitEncoder.encode(directContent, bounds, virtueFlaw.getLimitTrait().getCurrentValue());
    float leading = IVoidStateFormatConstants.LINE_HEIGHT - 2;
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
      phrase.add(new Chunk(resources.getString("Sheet.GreatCurse.Lunar.Rules"), font)); //$NON-NLS-1$      
    }
    PdfTextEncodingUtilities.encodeText(directContent, phrase, textBounds, leading);
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "GreatCurse.Lunar"; //$NON-NLS-1$
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}
