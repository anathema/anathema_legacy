package net.sf.anathema.character.reporting.pdf.layout.field;

import net.sf.anathema.character.reporting.pdf.layout.Body;

public class LayoutFieldBuilder implements Height, Placement, ColumnSpan {

  private static final int SPANS_ONE_COLUMN = 1;
  private static final int FIRST_COLUMN = 0;

  private LayoutField alignField;
  private float fromTop;
  private int columnSpan = SPANS_ONE_COLUMN;
  private int columnIndex = FIRST_COLUMN;
  private HeightStrategy heightStrategy;
  private FieldEncoder encoder;

  public LayoutFieldBuilder(FieldEncoder encoder) {
    this.encoder = encoder;
  }

  @Override
  public HeightWithoutParent atStartOf(Body body) {
    this.alignField = LayoutField.CreateUpperLeftFieldWithHeightAndColumnSpan(body, 0, 1);
    this.columnIndex = 0;
    this.fromTop = 0;
    return this;
  }

  @Override
  public Height below(LayoutField field) {
    this.alignField = field;
    this.columnIndex = field.getColumnIndexBelow();
    this.fromTop = field.getFromTopBelow();
    return this;
  }

  @Override
  public Height rightOf(LayoutField field) {
    this.alignField = field;
    this.columnIndex = field.getColumnIndexOnRight();
    this.fromTop = field.getAlignedFromTop();
    return this;
  }

  @Override
  public ColumnSpan withSameHeight() {
    return withHeight(alignField.getAlignedHeight());
  }

  @Override
  public ColumnSpan withPreferredHeight() {
    this.heightStrategy = new PreferredHeight(encoder);
    return this;
  }

  @Override
  public ColumnSpan withHeight(float layoutHeight) {
    this.heightStrategy = new StaticHeight(layoutHeight);
    return this;
  }

  @Override
  public ColumnSpan fillToBottomOfPage() {
    return withHeight(alignField.getRemainingColumnHeight());
  }

  @Override
  public ColumnSpan alignBottomTo(LayoutField field) {
    return withHeight(alignField.getHeightToBottomFrom(field));
  }

  @Override
  public Encoders spanningOneColumn() {
    this.columnSpan = 1;
    return this;
  }

  @Override
  public Encoders spanningTwoColumns() {
    this.columnSpan = 2;
    return this;
  }

  @Override
  public LayoutField now() {
    LayoutField field = buildField();
    return encoder.encode(field);
  }

  private LayoutField buildField() {
    return alignField.createForFromTopAndHeightAndColumnSpanAndColumnIndex(fromTop, heightStrategy, columnSpan, columnIndex);
  }
}
