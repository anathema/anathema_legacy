package net.sf.anathema.platform.tree.view.draw;

import org.mockito.Matchers;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GraphicsMother {

  public static Graphics2D createForAnyFont(){
    Graphics2D graphics = mock(Graphics2D.class);
    FontMetrics metrics = mock(FontMetrics.class);
    when(graphics.getFontMetrics(Matchers.any(Font.class))).thenReturn(metrics);
    when(metrics.stringWidth(isA(String.class))).thenReturn(1);
    return graphics;
  }

  public static Graphics2D createForFont(Font font){
    Graphics2D graphics = mock(Graphics2D.class);
    FontMetrics metrics = mock(FontMetrics.class);
    when(graphics.getFontMetrics(font)).thenReturn(metrics);
    when(metrics.stringWidth(isA(String.class))).thenReturn(1);
    return graphics;
  }
}