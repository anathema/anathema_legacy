package net.sf.anathema.character.sidereal.reporting.rendering;

import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtueflaw.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.sidereal.flawedfate.SiderealFlawedFateTemplate;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.REDUCED_LINE_HEIGHT;

public class SiderealFlawedFateEncoder implements IBoxContentEncoder {

  private final IResources resources;
  private final VirtueFlawBoxEncoder traitEncoder;

  public SiderealFlawedFateEncoder(BaseFont baseFont, IResources resources) {
    this.resources = resources;
    this.traitEncoder = new VirtueFlawBoxEncoder();
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    Font boldFont = graphics.createBoldFont();
	IVirtueFlaw virtueFlaw = ((IVirtueFlawModel) reportContent.getCharacter().getAdditionalModel(SiderealFlawedFateTemplate.ID)).getVirtueFlaw();
    Bounds textBounds = traitEncoder.encode(graphics, bounds, virtueFlaw.getLimitTrait().getCurrentValue());
     Font font = TableEncodingUtilities.createTableFont(graphics.getBaseFont());
    Phrase phrase = new Phrase("", font); //$NON-NLS-1$
    phrase.add(new Chunk(resources.getString("Sheet.GreatCurse.Sidereal.LimitBreak") + ": ", boldFont)); //$NON-NLS-1$
    String fateString = resources.getString("Sheet.GreatCurse.Sidereal.FlawedFate." + reportContent.getCharacter().getCasteType().getId()) + "\n";
    if (fateString.startsWith("#")) fateString = "\n";
    phrase.add(fateString);
    graphics.createSimpleColumn(textBounds).withLeading(REDUCED_LINE_HEIGHT).andTextPart(phrase).encode();
  }

  public String getHeaderKey(ReportContent content) {
    return "FlawedFate"; //$NON-NLS-1$
  }
  
  public boolean hasContent(ReportContent content)
  {
	  return true;
  }
}
