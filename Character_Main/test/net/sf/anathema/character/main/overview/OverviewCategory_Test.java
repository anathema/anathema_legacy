package net.sf.anathema.character.main.overview;

import net.sf.anathema.character.main.library.overview.SwingOverviewCategory;
import org.junit.Test;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GraphicsEnvironment;

import static org.junit.Assume.assumeTrue;

public class OverviewCategory_Test {

  @Test
  public void canCreateBorderOnJava7() {
    assumeTrue(!GraphicsEnvironment.isHeadless());
    JComponent parent = new JPanel();
    new SwingOverviewCategory(parent, "Title", true);
  }
}