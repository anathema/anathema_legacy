package net.sf.anathema.development.reporting.encoder.voidstate.columns;

import java.awt.Point;
import java.awt.Rectangle;

import org.dom4j.Element;

public interface IOneColumnEncoder {

  public int addCentralHeader(Element bandElement, Point headerPosition, int width, String text);

  public Rectangle createOneColumnBoxBoundsWithTitle(int lineCount, int paddingCount, Point position);

  public Rectangle encodeBoxAndQuotifyHeader(Element bandElement, Rectangle boxRectangle, String header);

  public void addParameterOrLine(Element parent, String parameter, int x, int y, int width);

  public int addCentralHeader(Element parent, Rectangle box, int y, String header);

  public void addEmptyLines(Element parent, Point position, int width, int lineCount);
}