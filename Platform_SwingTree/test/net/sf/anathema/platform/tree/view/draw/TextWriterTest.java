package net.sf.anathema.platform.tree.view.draw;

import org.junit.Test;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import static java.awt.Color.RED;
import static org.mockito.Mockito.verify;

public class TextWriterTest {

  private TextWriter writer = new TextWriter(new Rectangle(0, 0, 100, 100), RED, "ABC");
  private Font font = new Font("SansSerif", Font.PLAIN, 15);
  private Graphics2D graphics = GraphicsMother.createForFont(font);

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