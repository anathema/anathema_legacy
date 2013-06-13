package net.sf.anathema.character.equipment.impl.item.model.gson;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FilenameCleanerTest {

  private FilenameCleaner cleaner = new FilenameCleaner();

  @Test
  public void replacesSlashes() throws Exception {
    assertThat(cleaner.clean("x/x"), is("x_x"));
  }

  @Test
  public void replacesBackslashes() throws Exception {
    assertThat(cleaner.clean("x\\x"), is("x_x"));
  }

  @Test
  public void replacesColon() throws Exception {
    assertThat(cleaner.clean("x:x"), is("x_x"));
  }

  @Test
  public void replacesTilde() throws Exception {
    assertThat(cleaner.clean("x~x"), is("x_x"));
  }

  @Test
  public void replacesTwoInARow() throws Exception {
    assertThat(cleaner.clean("x~/x"), is("x__x"));
  }

  @Test
  public void replacesNonLatinCharacters() throws Exception {
    assertThat(cleaner.clean("x\u00FDx"), is("x_x"));
  }
}
