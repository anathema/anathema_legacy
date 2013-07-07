package net.sf.anathema.hero.sheet.pdf.page.layout;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.sheet.pdf.page.layout.field.LayoutField;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.EncoderRegistry;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.EncodingMetrics;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.GraphicsTemplate;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.Resources;

public class RegisteredEncoderList {
  private final Resources resources;
  private final EncoderRegistry encoderRegistry;
  private final PdfBoxEncoder boxEncoder = new PdfBoxEncoder();

  public RegisteredEncoderList(Resources resources, EncoderRegistry encoderRegistry) {
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

  public boolean hasContentFor(ReportSession session, String encoderId) {
    if (!encoderRegistry.hasEncoder(encoderId, session)) {
      return false;
    }
    ContentEncoder encoder = encoderRegistry.createEncoder(resources, session, encoderId);
    return encoder.hasContent(session);
  }

  public float getPreferredEncoderHeight(EncodingMetrics metrics, float width, String... encoderIds) {
    return encoderRegistry.getPreferredHeight(metrics, width, encoderIds);
  }
}
