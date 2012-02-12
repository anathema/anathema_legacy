package net.sf.anathema.character.reporting.pdf.layout;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutField;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.GraphicsTemplate;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderAttributeType.PreferredHeight;

public class RegisteredEncoderList {
  private final IResources resources;
  private final EncoderRegistry encoderRegistry;
  private final PdfBoxEncoder boxEncoder = new PdfBoxEncoder();

  public RegisteredEncoderList(IResources resources, EncoderRegistry encoderRegistry) {
    this.resources = resources;
    this.encoderRegistry = encoderRegistry;
  }

  public LayoutField encodeBox(SheetGraphics graphics, ReportContent content, LayoutField layout, String... encoderIds) {
    GraphicsTemplate template = layout.createRenderTemplate(graphics);
    encodeBox(template.getTemplateGraphics(), content, layout.createRenderBounds(), encoderIds);
    layout.addTemplateToParent(template);
    return layout;
  }

  public float encodeBox(SheetGraphics graphics, ReportContent content, Bounds bounds, String... encoderIds) {
    try {
      ContentEncoder encoder = encoderRegistry.createEncoder(resources, content, encoderIds);
      boxEncoder.encodeBox(content, graphics, encoder, bounds);
      return bounds.getHeight();
    } catch (DocumentException e) {
      throw new RuntimeException(e);
    }
  }

  public float encodeOptionalBox(SheetGraphics graphics, ReportContent content, Bounds bounds, String encoderId) {
    if (!encoderRegistry.hasEncoder(encoderId, content)) {
      return 0;
    }
    return encodeBox(graphics, content, bounds, encoderId);
  }

  public float getPreferredEncoderHeight(EncodingMetrics metrics, String... encoderIds) {
    return encoderRegistry.getValue(PreferredHeight, metrics, encoderIds);
  }
}
