package net.sf.anathema.platform.tree.view.draw;

import net.disy.commons.swing.color.ColorUtilities;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FilledPolygon_GraphicsTest {

  FilledPolygon filledPolygon = new FilledPolygon();
  Graphics2D graphics = mock(Graphics2D.class);

  @Before
  public void createSquare() throws Exception {
    filledPolygon.addPoint(0, 0);
    filledPolygon.addPoint(0, 2);
    filledPolygon.addPoint(2, 2);
    filledPolygon.addPoint(2, 0);
  }

  @Before
  public void setUp() throws Exception {
    FontMetrics metrics = mock(FontMetrics.class);
    when(graphics.getFontMetrics(Matchers.any(Font.class))).thenReturn(metrics);
  }

  @Test
  public void fillsWithSetColor() throws Exception {
    filledPolygon.fill(Color.DARK_GRAY);
    filledPolygon.paint(graphics);
    verify(graphics).setColor(Color.DARK_GRAY);
  }

  @Test
  public void changesAlphaOfFill() throws Exception {
    filledPolygon.fill(Color.DARK_GRAY);
    filledPolygon.setAlpha(100);
    filledPolygon.paint(graphics);
    verify(graphics).setColor(ColorUtilities.getTransparentColor(Color.DARK_GRAY, 100));
  }

  @Test
  public void changesAlphaOfStroke() throws Exception {
    filledPolygon.setAlpha(17);
    filledPolygon.paint(graphics);
    verify(graphics, atLeastOnce()).setColor(ColorUtilities.getTransparentColor(Color.BLACK, 17));
  }
}
