package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.lib.gui.swing.ColorUtilities;
import net.sf.anathema.platform.tree.swing.SwingGraphicsCanvas;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.Graphics2D;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class FilledPolygon_GraphicsTest {

  FilledPolygon filledPolygon = new FilledPolygon();
  Graphics2D graphics = GraphicsMother.createForAnyFont();
  private Canvas canvas = new SwingGraphicsCanvas(graphics);

  @Before
  public void createSquare() throws Exception {
    filledPolygon.addPoint(0, 0);
    filledPolygon.addPoint(0, 2);
    filledPolygon.addPoint(2, 2);
    filledPolygon.addPoint(2, 0);
  }

  @Test
  public void fillsWithSetColor() throws Exception {
    filledPolygon.fill(RGBColor.Pink);
    filledPolygon.paint(canvas);
    verify(graphics).setColor(Color.PINK);
  }

  @Test
  public void changesAlphaOfFill() throws Exception {
    filledPolygon.fill(RGBColor.Pink);
    filledPolygon.setAlpha(100);
    filledPolygon.paint(canvas);
    verify(graphics).setColor(ColorUtilities.getTransparentColor(Color.PINK, 100));
  }

  @Test
  public void changesAlphaOfStroke() throws Exception {
    filledPolygon.setAlpha(17);
    filledPolygon.paint(canvas);
    verify(graphics, atLeastOnce()).setColor(ColorUtilities.getTransparentColor(Color.BLACK, 17));
  }

  @Test
  public void writesSetTextAndSetsColor() throws Exception {
    filledPolygon.setAlpha(17);
    filledPolygon.setText("A");
    filledPolygon.paint(canvas);
    verify(graphics, atLeast(2)).setColor(ColorUtilities.getTransparentColor(Color.BLACK, 17));
    verify(graphics, atLeastOnce()).drawString(eq("A"), anyInt(), anyInt());
  }
}
