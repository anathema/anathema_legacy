package net.sf.anathema.character.reporting.pdf.layout.field;

public interface ColumnSpan extends Encoders {
  
  Encoders spanningOneColumn();

  Encoders spanningTwoColumns();
}
