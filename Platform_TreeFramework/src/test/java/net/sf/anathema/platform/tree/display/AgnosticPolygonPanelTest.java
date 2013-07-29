package net.sf.anathema.platform.tree.display;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;
import net.sf.anathema.platform.tree.display.transform.Translation;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AgnosticPolygonPanelTest {

  @Test
  public void centersOnGivenCoordinate() throws Exception {
    DisplayPolygonPanel displayPanel = mock(DisplayPolygonPanel.class);
    AgnosticPolygonPanel polygonPanel = new AgnosticPolygonPanel(displayPanel);
    polygonPanel.centerOn(new Coordinate(2, 3));
    AgnosticTransform expected = new AgnosticTransform();
    expected.preconcatenate(new Translation(-2, -3));
    verify(displayPanel).setTransformation(expected);
  }
}