package net.sf.anathema.platform.tree.swing;

import net.sf.anathema.framework.ui.Area;
import org.junit.Before;
import org.junit.Test;

import java.awt.FontMetrics;
import java.awt.Graphics2D;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SwingGraphicsCanvas_TextMeasureTest {

  private static final Integer HEIGHT = 15;
  private static final String STRING = "ANYSTRING";
  private static final Integer WIDTH = 30;
  private Area area;

  @Before
  public void setUp() throws Exception {
    Graphics2D graphics2d = mock(Graphics2D.class);
    FontMetrics fontMetrics = mock(FontMetrics.class);
    when(graphics2d.getFontMetrics()).thenReturn(fontMetrics);
    when(fontMetrics.getHeight()).thenReturn(HEIGHT);
    when(fontMetrics.stringWidth(STRING)).thenReturn(WIDTH);
    area = new SwingGraphicsCanvas(graphics2d).measureText(STRING);
  }

  @Test
  public void transfersHeightFromFontMetrics() throws Exception {
    assertThat(area.height, is(HEIGHT));
  }

  @Test
  public void transfersWidthFromFontMetrics() throws Exception {
    assertThat(area.width, is(WIDTH));
  }
}
