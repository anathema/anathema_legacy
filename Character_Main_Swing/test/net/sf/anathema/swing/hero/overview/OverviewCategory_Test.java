package net.sf.anathema.swing.hero.overview;

import org.junit.Assume;
import org.junit.Test;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GraphicsEnvironment;

public class OverviewCategory_Test {

  @Test
  public void canCreateBorderOnJava7() {
    Assume.assumeTrue(!GraphicsEnvironment.isHeadless());
    JComponent parent = new JPanel();
    new SwingOverviewCategory(parent, "Title", true);
  }
}