package net.sf.anathema.character.sidereal.reporting.rendering;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtueflaw.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.sidereal.paradox.SiderealParadoxTemplate;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.REDUCED_LINE_HEIGHT;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.TEXT_PADDING;

public class SiderealParadoxEncoder implements IBoxContentEncoder {

  private final IResources resources;

  public SiderealParadoxEncoder(IResources resources) {
    this.resources = resources;
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    IVirtueFlaw virtueFlaw = ((IVirtueFlawModel) reportContent.getCharacter().getAdditionalModel(SiderealParadoxTemplate.ID)).getVirtueFlaw();
    Bounds textBounds = new VirtueFlawBoxEncoder().encode(graphics, bounds, virtueFlaw.getLimitTrait().getCurrentValue());
    float lineHeight = (textBounds.height - TEXT_PADDING) / 2;
    String effects = resources.getString("Sheet.GreatCurse.Sidereal.CurrentEffects") + ":"; //$NON-NLS-1$ //$NON-NLS-2$
    graphics.drawLabelledContent(effects, null, new Position(textBounds.x, textBounds.getMaxY() - lineHeight), bounds.width);

    Font font = TableEncodingUtilities.createFont(graphics.getBaseFont());
    Phrase phrase = new Phrase("", font); //$NON-NLS-1$
    phrase.add(graphics.createSymbolChunk());
    phrase.add(resources.getString("Sheet.GreatCurse.Sidereal." +
                                   (reportContent.getCharacter().getRules().getEdition() == ExaltedEdition.SecondEdition ? "2E." : "") +
                                   "RulesPages")); //$NON-NLS-1$
    Bounds infoBounds = new Bounds(bounds.x, bounds.y, bounds.width, textBounds.height - lineHeight);
    graphics.createSimpleColumn(infoBounds).withLeading(REDUCED_LINE_HEIGHT).andTextPart(phrase).encode();
  }

  public String getHeaderKey(ReportContent content) {
    return "GreatCurse.Sidereal"; //$NON-NLS-1$
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
