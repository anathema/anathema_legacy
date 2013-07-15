package net.sf.anathema.platform.tree.view.draw;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;

import static java.awt.Color.RED;
import static org.mockito.Mockito.verify;

public class TextWriterTest {

  private TextWriter writer = new TextWriter(new Polygon(new int[]{0, 100}, new int[]{0, 100}, 2));
  private Font font = new Font("SansSerif", Font.PLAIN, 15);
  private Graphics2D graphics = GraphicsMother.createForFont(font);

  @Before
  public void setUp() throws Exception {
    writer.setText("ABC");
    writer.setStroke(Color.RED);
  }

  @Test
  public void writesGivenText() throws Exception {
    writer.write(graphics);
    verify(graphics).drawString("ABC", 50, 46);
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