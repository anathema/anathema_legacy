package net.sf.anathema.character.reporting.pdf.rendering.graphics;

public interface TextMetrics {

  float getDefaultTextWidth(String text);

  float getCommentTextWidth(String text);

  float getTableTextWidth(String text);
}
