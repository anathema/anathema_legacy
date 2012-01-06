package net.sf.anathema.character.reporting.pdf.layout;

import net.sf.anathema.character.reporting.pdf.layout.field.FieldBuilder;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutEncoder;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutField;
import net.sf.anathema.character.reporting.pdf.layout.field.Placement;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class SheetPage {

  private class Encoder implements LayoutEncoder {
    private final String[] encoderIds;

    public Encoder(String... encoderIds) {
      this.encoderIds = encoderIds;
    }

    @Override
    public LayoutField encode(LayoutField field) {
      return encoderList.encodeBox(graphics, metrics.getContent(), field, encoderIds);
    }
  }

  private final RegisteredEncoderList encoderList;
  private final EncodingMetrics metrics;
  private final SheetGraphics graphics;

  public SheetPage(RegisteredEncoderList encoderList, EncodingMetrics metrics, SheetGraphics graphics) {
    this.encoderList = encoderList;
    this.metrics = metrics;
    this.graphics = graphics;
  }

  public Placement place(final String... encoderIds) {
    return new FieldBuilder(new Encoder(encoderIds));
  }

  public float getPreferredEncoderHeight(String encoderId) {
    return encoderList.getPreferredEncoderHeight(metrics, encoderId);
  }
}
