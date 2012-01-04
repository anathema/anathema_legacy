package net.sf.anathema.test.character.library.overview;

import net.sf.anathema.character.library.overview.OverviewCategory;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assume.assumeTrue;

public class OverviewCategory_Test {

  @Test
  public void canCreateBorderOnJava7(){
    assumeTrue(!GraphicsEnvironment.isHeadless());
    JComponent parent = new JPanel();
    new OverviewCategory(parent, "Title", true);
  }
}