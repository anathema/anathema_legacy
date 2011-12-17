package net.sf.anathema.character.infernal.reporting.extended;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.infernal.urge.InfernalUrgeTemplate;
import net.sf.anathema.character.infernal.urge.model.IInfernalUrgeModel;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.extended.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.extended.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.extended.util.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

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
    String urgeDescription = urge.getDescription().getText();

    Phrase phrase = new Phrase();
    phrase.add(new Chunk(resources.getString("InfernalUrge.Title"), nameFont));
    phrase.add(new Chunk(": ", nameFont)); //$NON-NLS-1$
    phrase.add(new Chunk(urgeDescription, font));
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