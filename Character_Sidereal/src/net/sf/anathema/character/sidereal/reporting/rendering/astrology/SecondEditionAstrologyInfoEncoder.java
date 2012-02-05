package net.sf.anathema.character.sidereal.reporting.rendering.astrology;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.sidereal.reporting.rendering.*;
import net.sf.anathema.character.sidereal.reporting.rendering.resplendentdestiny.ResplendentDestinyTableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionAstrologyInfoEncoder implements ContentEncoder {

  private final IResources resources;
  private final float SPACING = 5;

  public SecondEditionAstrologyInfoEncoder(IResources resources) {
    this.resources = resources;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent report, Bounds bounds) throws DocumentException {
    int height = (int) SPACING;
    height += (int) new SecondEditionAstrologyTableEncoder(resources).encodeTable(graphics, report, getBounds(bounds, 0, height)) + SPACING + 1;
    height += (int) new DestinyTypeTableEncoder(resources).encodeTable(graphics, report, getBounds(bounds, 0, height)) + SPACING + 2;
    height += (int) new ResplendentDestinyTableEncoder(resources).encodeTable(graphics, report, getBounds(bounds, 0, height)) + SPACING + 2;

    int horizontalSpan = (int) (bounds.width / 2);
    height = (int) SPACING;

    height += (int) new TriggerTypeTableEncoder(resources).encodeTable(graphics, report, getBounds(bounds, horizontalSpan, height)) + SPACING;
    height += (int) new ScopeTableEncoder(resources).encodeTable(graphics, report, getBounds(bounds, horizontalSpan, height)) + SPACING;
    height += (int) new DurationTableEncoder(resources).encodeTable(graphics, report, getBounds(bounds, horizontalSpan, height)) + SPACING;
    height += (int) new FrequencyTableEncoder(resources).encodeTable(graphics, report, getBounds(bounds, horizontalSpan, height)) + SPACING;

  }

  private Bounds getBounds(Bounds bounds, int offsetX, int offsetY) {
    return new Bounds(bounds.x + offsetX, bounds.y - offsetY, bounds.width / 2 - SPACING, bounds.height);
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header.Sidereal.Astrology");
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}
