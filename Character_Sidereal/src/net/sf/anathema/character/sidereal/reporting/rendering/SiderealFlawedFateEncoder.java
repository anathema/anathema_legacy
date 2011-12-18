package net.sf.anathema.character.sidereal.reporting.rendering;

import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtueflaw.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.general.AbstractPdfEncoder;
import net.sf.anathema.character.sidereal.flawedfate.SiderealFlawedFateTemplate;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;

public class SiderealFlawedFateEncoder extends AbstractPdfEncoder implements IBoxContentEncoder {

  private final BaseFont baseFont;
  private final Font boldFont;
  private final IResources resources;
  private final VirtueFlawBoxEncoder traitEncoder;

  public SiderealFlawedFateEncoder(BaseFont baseFont, IResources resources) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.traitEncoder = new VirtueFlawBoxEncoder(baseFont);
    this.boldFont = new Font(baseFont);
    boldFont.setStyle(Font.BOLD);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public void encode(PdfGraphics graphics, ReportContent reportContent) throws DocumentException {
	IVirtueFlaw virtueFlaw = ((IVirtueFlawModel) reportContent.getCharacter().getAdditionalModel(SiderealFlawedFateTemplate.ID)).getVirtueFlaw();
    Bounds textBounds = traitEncoder.encode(graphics.getDirectContent(), graphics.getBounds(), virtueFlaw.getLimitTrait().getCurrentValue());
    //float lineHeight = (textBounds.height - TEXT_PADDING) / 2;
    /*String effects = resources.getString("Sheet.GreatCurse.Sidereal.CurrentEffects") + ":"; //$NON-NLS-1$ //$NON-NLS-2$
    drawLabelledContent(
        directContent,
        effects,
        null,
        new Position(textBounds.x, textBounds.getMaxY() - lineHeight),
        bounds.width);*/

    Font font = TableEncodingUtilities.createFont(getBaseFont());
    Phrase phrase = new Phrase("", font); //$NON-NLS-1$
    phrase.add(new Chunk(resources.getString("Sheet.GreatCurse.Sidereal.LimitBreak") + ": ", boldFont)); //$NON-NLS-1$
    String fateString = resources.getString("Sheet.GreatCurse.Sidereal.FlawedFate." + reportContent.getCharacter().getCasteType().getId()) + "\n";
    if (fateString.startsWith("#")) fateString = "\n";
    phrase.add(fateString); 
    encodeTextWithReducedLineHeight(graphics.getDirectContent(), textBounds, phrase);
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "FlawedFate"; //$NON-NLS-1$
  }
  
  public boolean hasContent(ReportContent content)
  {
	  return true;
  }
}
