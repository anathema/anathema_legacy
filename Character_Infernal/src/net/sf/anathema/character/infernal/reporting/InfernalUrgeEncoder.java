package net.sf.anathema.character.infernal.reporting;

import net.sf.anathema.character.infernal.urge.InfernalUrgeTemplate;
import net.sf.anathema.character.infernal.urge.model.IInfernalUrgeModel;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtueflaw.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;

public class InfernalUrgeEncoder implements IBoxContentEncoder {

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

  public String getHeaderKey(ReportContent reportContent) {
    return "InfernalUrge.Title"; //$NON-NLS-1$
  }

  public void encode(PdfGraphics graphics, ReportContent reportContent) throws DocumentException {
	IInfernalUrgeModel urge = ((IInfernalUrgeModel) reportContent.getCharacter().getAdditionalModel(InfernalUrgeTemplate.ID));
    Bounds textBounds = traitEncoder.encode(graphics.getDirectContent(), graphics.getBounds(), urge.getVirtueFlaw().getLimitTrait().getCurrentValue());
    float leading = IVoidStateFormatConstants.LINE_HEIGHT - 2;
    String urgeDescription = urge.getDescription().getText();

    Phrase phrase = new Phrase();
    phrase.add(new Chunk(resources.getString("InfernalUrge.Title"), nameFont));
    phrase.add(new Chunk(": ", nameFont)); //$NON-NLS-1$
    phrase.add(new Chunk(urgeDescription, font));
    PdfTextEncodingUtilities.encodeText(graphics.getDirectContent(), phrase, textBounds, leading);
 
  }
  
  public boolean hasContent(ReportContent content)
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
