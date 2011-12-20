package net.sf.anathema.character.sidereal.reporting.rendering.astrology;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.DestinyTypeTableEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.DurationTableEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.FrequencyTableEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.ScopeTableEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.TriggerTypeTableEncoder;
import net.sf.anathema.character.sidereal.reporting.rendering.resplendentdestiny.ResplendentDestinyTableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionAstrologyInfoEncoder implements IBoxContentEncoder {

  private final IResources resources;
  private final BaseFont basefont;
  private final float SPACING = 5;

  public SecondEditionAstrologyInfoEncoder(BaseFont baseFont, IResources resources) {
    this.resources = resources;
    this.basefont = baseFont;
  }

  public void encode(SheetGraphics graphics, ReportContent report, Bounds bounds) throws DocumentException {
    int height = (int) SPACING;
    height += (int) new SecondEditionAstrologyTableEncoder(resources, basefont).encodeTable(graphics, report, getBounds(bounds, 0,
      height)) + SPACING + 1;
    height += (int) new DestinyTypeTableEncoder(resources, basefont).encodeTable(graphics, report, getBounds(bounds, 0, height)) + SPACING + 2;
    height += (int) new ResplendentDestinyTableEncoder(resources, basefont).encodeTable(graphics, report, getBounds(bounds, 0,
      height)) + SPACING + 2;

    int horizSpan = (int) (bounds.width / 2);
    height = (int) SPACING;

    height += (int) new TriggerTypeTableEncoder(resources, basefont).encodeTable(graphics, report, getBounds(bounds, horizSpan, height)) + SPACING;
    height += (int) new ScopeTableEncoder(resources, basefont).encodeTable(graphics, report, getBounds(bounds, horizSpan, height)) + SPACING;
    height += (int) new DurationTableEncoder(resources, basefont).encodeTable(graphics, report, getBounds(bounds, horizSpan, height)) + SPACING;
    height += (int) new FrequencyTableEncoder(resources, basefont).encodeTable(graphics, report, getBounds(bounds, horizSpan, height)) + SPACING;

  }

  private Bounds getBounds(Bounds bounds, int offsetX, int offsetY) {
    return new Bounds(bounds.x + offsetX, bounds.y - offsetY, bounds.width / 2 - SPACING, bounds.height);
  }

  public String getHeaderKey(ReportContent report) {
    return "Sidereal.Astrology"; //$NON-NLS-1$
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
