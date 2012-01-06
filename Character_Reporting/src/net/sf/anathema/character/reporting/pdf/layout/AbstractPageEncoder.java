package net.sf.anathema.character.reporting.pdf.layout;

import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutField;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractPageEncoder implements PageEncoder {

  private RegisteredEncoderList layoutEncoder;

  protected AbstractPageEncoder(IResources resources, EncoderRegistry encoderRegistry) {
    this.layoutEncoder = new RegisteredEncoderList(resources, encoderRegistry);
  }

  protected final LayoutField encodeBox(SheetGraphics graphics, ReportContent content, LayoutField layout, String... encoderIds) {
    return layoutEncoder.encodeBox(graphics, content, layout, encoderIds);
  }

  protected final float encodeBox(SheetGraphics graphics, ReportContent content, Bounds bounds, String... encoderIds) {
    return layoutEncoder.encodeBox(graphics, content, bounds, encoderIds);
  }

  protected final float encodeOptionalBox(SheetGraphics graphics, ReportContent content, Bounds bounds, String encoderId) {
    return layoutEncoder.encodeOptionalBox(graphics, content, bounds, encoderId);
  }

  protected final float getPreferredEncoderHeight(EncodingMetrics metrics, String encoderId) {
    return layoutEncoder.getPreferredEncoderHeight(metrics, encoderId);
  }
}
