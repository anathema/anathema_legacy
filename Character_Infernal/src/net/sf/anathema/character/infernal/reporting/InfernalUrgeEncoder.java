package net.sf.anathema.character.infernal.reporting;

import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.infernal.urge.InfernalUrgeTemplate;
import net.sf.anathema.character.infernal.urge.model.IInfernalUrgeModel;
import net.sf.anathema.character.reporting.common.TableEncodingUtilities;
import net.sf.anathema.character.reporting.common.boxes.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.common.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class InfernalUrgeEncoder implements IPdfContentBoxEncoder {

  private final VirtueFlawBoxEncoder traitEncoder;
  private final IResources resources;
  private final Font nameFont;
  private final Font font;

  public InfernalUrgeEncoder(IResources resources, BaseFont baseFont) {
    this.font = createFont(baseFont);
    this.nameFont = createNameFont(baseFont);
    this.resources = resources;
    this.traitEncoder = new VirtueFlawBoxEncoder(baseFont);
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "InfernalUrge.Title"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
	IInfernalUrgeModel urge = ((IInfernalUrgeModel) character.getAdditionalModel(InfernalUrgeTemplate.ID));
    Bounds textBounds = traitEncoder.encode(directContent, bounds, urge.getVirtueFlaw().getLimitTrait().getCurrentValue());
    float leading = IVoidStateFormatConstants.LINE_HEIGHT - 2;
    String descriptionText = urge.getDescription().getText();

    Phrase phrase = new Phrase();
    phrase.add(new Chunk(resources.getString("InfernalUrge.Title"), nameFont));
    phrase.add(new Chunk(": ", nameFont)); //$NON-NLS-1$
    phrase.add(new Chunk(descriptionText, font));
    PdfTextEncodingUtilities.encodeText(directContent, phrase, textBounds, leading);
 
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return false;
  }

  private Font createNameFont(BaseFont baseFont) {
    Font newFont = createFont(baseFont);
    newFont.setStyle(Font.BOLD);
    return newFont;
  }

  private Font createFont(BaseFont baseFont) {
    return TableEncodingUtilities.createFont(baseFont);
  }
}
