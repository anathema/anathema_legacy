package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.lib.gui.swing.ColorUtilities;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.Graphics2D;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

public class FilledPolygon_GraphicsTest {

  FilledPolygon filledPolygon = new FilledPolygon();
  Graphics2D graphics = GraphicsMother.createForAnyFont();

  @Before
  public void createSquare() throws Exception {
    filledPolygon.addPoint(0, 0);
    filledPolygon.addPoint(0, 2);
    filledPolygon.addPoint(2, 2);
    filledPolygon.addPoint(2, 0);
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
