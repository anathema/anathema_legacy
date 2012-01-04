package net.sf.anathema.character.reporting.pdf.layout;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderAttributeType.PreferredHeight;

public abstract class AbstractPageEncoder implements PageEncoder {

  private IResources resources;
  private EncoderRegistry encoderRegistry;
  private final PdfBoxEncoder boxEncoder;

  protected AbstractPageEncoder(IResources resources, EncoderRegistry encoderRegistry) {
    this.resources = resources;
    this.encoderRegistry = encoderRegistry;
    this.boxEncoder = new PdfBoxEncoder();
  }

  protected final float encodeBox(SheetGraphics graphics, ReportContent content, Bounds bounds, String... encoderIds) {
    try {
      ContentEncoder encoder = encoderRegistry.createEncoder(resources, content, encoderIds);
      boxEncoder.encodeBox(content, graphics, encoder, bounds);
      return bounds.getHeight();
    } catch (DocumentException e) {
      throw new RuntimeException(e);
    }
  }

  protected final float encodeOptionalBox(SheetGraphics graphics, ReportContent content, Bounds bounds, String encoderId) {
    if (!encoderRegistry.hasEncoder(encoderId, content)) {
      return 0;
    }
    return encodeBox(graphics, content, bounds, encoderId);
  }

  protected final float getPreferredEncoderHeight(ReportContent content, String encoderId) {
    return encoderRegistry.getValue(PreferredHeight, content, encoderId);
  }
}
