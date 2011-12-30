package net.sf.anathema.character.abyssal.reporting;

import net.sf.anathema.character.abyssal.resonance.AbyssalResonanceTemplate;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtueflaw.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.REDUCED_LINE_HEIGHT;

public class Abyssal2ndResonanceEncoder implements IBoxContentEncoder {

  private final VirtueFlawBoxEncoder traitEncoder;
  private final IResources resources;

  public Abyssal2ndResonanceEncoder(IResources resources) {
    this.resources = resources;
    this.traitEncoder = new VirtueFlawBoxEncoder();
  }

  public String getHeaderKey(ReportContent content) {
    return "GreatCurse.Abyssal"; //$NON-NLS-1$
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    IVirtueFlaw resonance = ((IVirtueFlawModel) reportContent.getCharacter().getAdditionalModel(AbyssalResonanceTemplate.ID)).getVirtueFlaw();
    Bounds textBounds = traitEncoder.encode(graphics, bounds, resonance.getLimitTrait().getCurrentValue());
    Font font = createFont(graphics.getBaseFont());
    Font nameFont = createNameFont(graphics.getBaseFont());
    Phrase phrase = new Phrase("", font); //$NON-NLS-1$
    phrase.add(new Chunk(resources.getString("Sheet.GreatCurse.FlawedVirtue") + ": ", nameFont)); //$NON-NLS-1$ //$NON-NLS-2$
    if (resonance.isFlawComplete()) {
      phrase.add(resonance.getRoot().getId() + ".\n");
    } else {
      Font undefinedFont = new Font(font);
      undefinedFont.setStyle(Font.UNDERLINE);
      phrase.add(new Chunk("                                          ", undefinedFont)); //$NON-NLS-1$
      phrase.add(".\n");
    }
    phrase.add(resources.getString("Sheet.GreatCurse.ResonanceReference")); //$NON-NLS-1$
    graphics.createSimpleColumn(textBounds).withLeading(REDUCED_LINE_HEIGHT).andTextPart(phrase).encode();
  }
  
  public boolean hasContent(ReportContent content) {
	  return true;
  }

  private Font createNameFont(BaseFont baseFont) {
    Font newFont = createFont(baseFont);
    newFont.setStyle(Font.BOLD);
    return newFont;
  }

  private Font createFont(BaseFont baseFont) {
    return TableEncodingUtilities.createTableFont(baseFont);
  }
}
