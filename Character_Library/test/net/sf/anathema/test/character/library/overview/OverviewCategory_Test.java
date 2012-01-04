package net.sf.anathema.test.character.library.overview;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sf.anathema.character.library.overview.OverviewCategory;

import org.junit.Test;

public class OverviewCategory_Test {

  @Test
  public void canCreateBorderOnJava7(){
    JComponent parent = new JPanel();
    new OverviewCategory(parent, "Title", true);
  }
}