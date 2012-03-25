package net.sf.anathema.character.reporting.pdf.layout;

import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutField;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractPageEncoder implements PageEncoder {

  private RegisteredEncoderList layoutEncoder;

  protected AbstractPageEncoder(IResources resources, EncoderRegistry encoderRegistry) {
    this.layoutEncoder = new RegisteredEncoderList(resources, encoderRegistry);
  }

  protected final LayoutField encodeBox(SheetGraphics graphics, ReportSession session, LayoutField layout, String... encoderIds) {
    return layoutEncoder.encodeBox(graphics, session, layout, encoderIds);
  }

  protected final float encodeBox(SheetGraphics graphics, ReportSession session, Bounds bounds, String... encoderIds) {
    return layoutEncoder.encodeBox(graphics, session, bounds, encoderIds);
  }
}
