package net.sf.anathema.character.reporting.pdf.layout;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutField;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.GraphicsTemplate;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class RegisteredEncoderList {
  private final IResources resources;
  private final EncoderRegistry encoderRegistry;
  private final PdfBoxEncoder boxEncoder = new PdfBoxEncoder();

  public RegisteredEncoderList(IResources resources, EncoderRegistry encoderRegistry) {
    this.resources = resources;
    this.encoderRegistry = encoderRegistry;
  }

  public LayoutField encodeBox(SheetGraphics graphics, ReportSession session, LayoutField layout, String... encoderIds) {
    GraphicsTemplate template = layout.createRenderTemplate(graphics);
    encodeBox(template.getTemplateGraphics(), session, layout.createRenderBounds(), encoderIds);
    layout.addTemplateToParent(template);
    return layout;
  }

  public float encodeBox(SheetGraphics graphics, ReportSession session, Bounds bounds, String... encoderIds) {
    try {
      ContentEncoder encoder = encoderRegistry.createEncoder(resources, session, encoderIds);
      boxEncoder.encodeBox(session, graphics, encoder, bounds);
      return bounds.getHeight();
    } catch (DocumentException e) {
      throw new RuntimeException(e);
    }
  }

  public float getPreferredEncoderHeight(EncodingMetrics metrics, float width, String... encoderIds) {
    return encoderRegistry.getPreferredHeight(metrics, width, encoderIds);
  }
}
