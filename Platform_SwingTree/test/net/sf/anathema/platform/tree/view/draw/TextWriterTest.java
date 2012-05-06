package net.sf.anathema.platform.tree.view.draw;

import org.junit.Before;
import org.junit.Test;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static java.awt.Color.RED;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TextWriterTest {

  private TextWriter writer = new TextWriter(new Rectangle(0, 0, 100, 100), RED, "ABC");
  private Graphics2D graphics = mock(Graphics2D.class);
  private Font font = new Font("SansSerif", Font.PLAIN, 15);

  @Before
  public void setUp() throws Exception {
    FontMetrics metrics = mock(FontMetrics.class);
    when(graphics.getFontMetrics(font)).thenReturn(metrics);
  }

  @Test
  public void writesGivenText() throws Exception {
    writer.write(graphics);
    verify(graphics).drawString("ABC", 50, 50);
  }

  @Test
  public void usesGivenTextColor() throws Exception {
    writer.write(graphics);
    verify(graphics).setColor(RED);
  }

  @Test
  public void setsFontForText() throws Exception {
    writer.write(graphics);
    verify(graphics).setFont(font);
  }
}