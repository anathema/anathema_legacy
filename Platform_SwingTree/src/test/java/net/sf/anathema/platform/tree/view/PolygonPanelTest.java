package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.tree.swing.SwingGraphicsCanvas;
import net.sf.anathema.platform.tree.view.draw.Canvas;
import net.sf.anathema.platform.tree.view.draw.FilledPolygon;
import net.sf.anathema.platform.tree.view.draw.FlexibleArrow;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;
import net.sf.anathema.platform.tree.view.draw.PolygonMother;
import net.sf.anathema.platform.tree.view.interaction.Closure;
import org.junit.Before;
import org.junit.Test;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PolygonPanelTest {

  private SwingPolygonPanel polygonPanel = new SwingPolygonPanel();
  private Graphics2D graphics = mock(Graphics2D.class);
  private Canvas canvas = new SwingGraphicsCanvas(graphics);

  @Before
  public void setUp() throws Exception {
    when(graphics.create()).thenReturn(graphics);
    polygonPanel.setSize(100, 100);
  }

  @Test
  public void hasIdentityTransformationInitially() throws Exception {
    polygonPanel.paintComponent(graphics);
    verify(graphics).transform(new AffineTransform());
  }

  @Test
  public void allowsZoomOutTo30Percent() throws Exception {
    double factor = 0.3;
    polygonPanel.scale(factor);
    polygonPanel.paintComponent(graphics);
    verify(graphics).transform(AffineTransform.getScaleInstance(factor, factor));
  }

  @Test
  public void limitsZoomOutTo30Percent() throws Exception {
    double factor = 0.29;
    polygonPanel.scale(factor);
    polygonPanel.paintComponent(graphics);
    verify(graphics).transform(AffineTransform.getScaleInstance(1, 1));
  }

  @Test
  public void allowsZoomInTo150Percent() throws Exception {
    double factor = 1.50;
    polygonPanel.scale(factor);
    polygonPanel.paintComponent(graphics);
    verify(graphics).transform(AffineTransform.getScaleInstance(1.5, 1.5));
  }

  @Test
  public void limitsZoomInTo150Percent() throws Exception {
    double factor = 1.51;
    polygonPanel.scale(factor);
    polygonPanel.paintComponent(graphics);
    verify(graphics).transform(AffineTransform.getScaleInstance(1, 1));
  }


  @Test
  public void scalesToAGivenPoint() throws Exception {
    double factor = .50;
    polygonPanel.scaleToPoint(factor, 100, 100);
    polygonPanel.paintComponent(graphics);
    AffineTransform expected = new AffineTransform(0.5, 0, 0, 0.5, 50, 50);
    verify(graphics).transform(expected);
  }

  @Test
  public void translatesGraphics() throws Exception {
    polygonPanel.translate(5, 7);
    polygonPanel.paintComponent(graphics);
    verify(graphics).transform(AffineTransform.getTranslateInstance(5, 7));
  }

  @Test
  public void translatesZoomedGraphicsRelativeToScale() throws Exception {
    double factor = 1.50;
    polygonPanel.scale(factor);
    polygonPanel.translateRelativeToScale(150, 150);
    polygonPanel.paintComponent(graphics);
    AffineTransform expected = new AffineTransform(1.5, 0, 0, 1.5, 150, 150);
    verify(graphics).transform(expected);
  }

  @Test
  public void centersOnPoint() throws Exception {
    polygonPanel.centerOn(10, 10);
    polygonPanel.paintComponent(graphics);
    verify(graphics).transform(AffineTransform.getTranslateInstance(40, 40));
  }

  @Test
  public void activatesAntiAliasing() throws Exception {
    polygonPanel.paintComponent(graphics);
    verify(graphics).setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
  }

  @Test
  public void paintsPolygon() throws Exception {
    InteractiveGraphicsElement polygon = mock(FilledPolygon.class);
    polygonPanel.add(polygon);
    polygonPanel.paintComponent(graphics);
    verify(polygon).paint(canvas);
  }

  @Test
  public void paintsArrow() throws Exception {
    FlexibleArrow arrow = mock(FlexibleArrow.class);
    polygonPanel.add(arrow);
    polygonPanel.paintComponent(graphics);
    verify(arrow).paint(canvas);
  }

  @Test
  public void paintsAllPolygon() throws Exception {
    InteractiveGraphicsElement firstPolygon = mock(FilledPolygon.class);
    InteractiveGraphicsElement secondPolygon = mock(FilledPolygon.class);
    polygonPanel.add(firstPolygon);
    polygonPanel.add(secondPolygon);
    polygonPanel.paintComponent(graphics);
    verify(firstPolygon).paint(canvas);
    verify(secondPolygon).paint(canvas);
  }

  @Test
  public void hasNoElementsAfterClear() throws Exception {
    InteractiveGraphicsElement element = mock(FilledPolygon.class);
    polygonPanel.add(element);
    polygonPanel.clear();
    polygonPanel.paintComponent(graphics);
    verifyZeroInteractions(element);
  }

  @Test
  public void transformsPointForElements() throws Exception {
    InteractiveGraphicsElement polygon = PolygonMother.squareAtOriginWithLength2();
    polygonPanel.add(polygon);
    polygonPanel.translate(2, 2);
    polygonPanel.onElementAtPoint(new Point(3, 3)).perform(new Closure() {
      @Override
      public void execute(InteractiveGraphicsElement polygon) {
        //nothing to do
      }
    }).orFallBackTo(new Runnable() {
      @Override
      public void run() {
        fail();
      }
    });
  }
}