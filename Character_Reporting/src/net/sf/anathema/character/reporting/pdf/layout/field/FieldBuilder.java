package net.sf.anathema.character.reporting.pdf.layout.field;

import net.sf.anathema.character.reporting.pdf.layout.Body;

public class FieldBuilder implements Height, Placement, ColumnSpan, Encoders {

  private LayoutField parent;
  private float fromTop;
  public int columnSpan = 1;
  public int columnIndex = 0;
  private float height;
  private LayoutEncoder encoder;

  public FieldBuilder(LayoutEncoder encoder) {
    this.encoder = encoder;
  }

  @Override
  public HeightWithoutParent atStartOf(Body body) {
    this.parent = LayoutField.CreateUpperLeftFieldWithHeightAndColumnSpan(body, 0, 1);
    this.columnIndex = 0;
    this.fromTop = 0;
    return this;
  }

  public Height below(LayoutField field) {
    this.parent = field;
    this.columnIndex = field.getColumnIndexBelow();
    this.fromTop = field.getFromTopBelow();
    return this;
  }

  @Override
  public Height rightOf(LayoutField field) {
    this.parent = field;
    this.columnIndex = field.getColumnIndexOnRight();
    this.fromTop = field.getAlignedFromTop();
    return this;
  }

  @Override
  public ColumnSpan withSameHeight() {
    this.height = parent.getAlignedHeight();
    return this;
  }

  @Override
  public ColumnSpan withHeight(float height) {
    this.height = height;
    return this;
  }

  @Override
  public ColumnSpan fillToBottomOfPage() {
    this.height = parent.getRemainingColumnHeight();
    return this;
  }

  @Override
  public ColumnSpan alignBottomTo(LayoutField parent) {
    this.height = this.parent.getHeightToBottomFrom(parent);
    return this;
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
     return parent.createForFromTopAndHeightAndColumnSpanAndColumnIndex(fromTop, height, columnSpan, columnIndex);
  }
}
