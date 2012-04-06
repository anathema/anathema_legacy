package net.sf.anathema.character.reporting.pdf.layout;

import net.sf.anathema.character.reporting.pdf.layout.field.FieldEncoder;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutField;
import net.sf.anathema.character.reporting.pdf.layout.field.LayoutFieldBuilder;
import net.sf.anathema.character.reporting.pdf.layout.field.Placement;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class SheetPage {

  private class SheetFieldEncoder implements FieldEncoder {
    private final String[] encoderIds;

    private SheetFieldEncoder(String[] encoderIds) {
      this.encoderIds = encoderIds;
    }

    @Override
    public boolean isActive() {
      return true;
    }

    @Override
    public LayoutField encode(LayoutField field) {
      if (field.isInvisible()) {
        return field;
      }
      return encoderList.encodeBox(graphics, metrics.getSession(), field, encoderIds);
    }

    @Override
    public float getPreferredHeight(float contentWidth) {
      return encoderList.getPreferredEncoderHeight(metrics, contentWidth, encoderIds);
    }
  }

  private class OptionalSheetFieldEncoder implements FieldEncoder {
    private final String encoderId;

    private OptionalSheetFieldEncoder(String encoderId) {
      this.encoderId = encoderId;
    }

    @Override
    public boolean isActive() {
      return encoderList.hasContentFor(metrics.getSession(), encoderId);
    }

    @Override
    public LayoutField encode(LayoutField field) {
      if (field.isInvisible()) {
        return field;
      }
      return encoderList.encodeBox(graphics, metrics.getSession(), field, encoderId);
    }

    @Override
    public float getPreferredHeight(float contentWidth) {
      return encoderList.getPreferredEncoderHeight(metrics, contentWidth, encoderId);
    }
  }

  private Body body;
  private final RegisteredEncoderList encoderList;
  private final EncodingMetrics metrics;
  private final SheetGraphics graphics;

  public SheetPage(Body body, RegisteredEncoderList encoderList, EncodingMetrics metrics, SheetGraphics graphics) {
    this.body = body;
    this.encoderList = encoderList;
    this.metrics = metrics;
    this.graphics = graphics;
  }

  public Placement place(String... encoderIds) {
    FieldEncoder fieldEncoder = new SheetFieldEncoder(encoderIds);
    return new LayoutFieldBuilder(fieldEncoder);
  }

  public Placement placeOptional(String encoderId) {
    FieldEncoder fieldEncoder = new OptionalSheetFieldEncoder(encoderId);
    return new LayoutFieldBuilder(fieldEncoder);
  }

  public Body getBody() {
    return body;
  }
}
