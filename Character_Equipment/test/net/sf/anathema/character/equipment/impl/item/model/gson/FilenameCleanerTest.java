package net.sf.anathema.character.equipment.impl.item.model.gson;

import net.sf.anathema.character.equipment.item.model.gson.FilenameCleaner;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FilenameCleanerTest {

  @Test
  public void replacesSlashes() throws Exception {
    assertThat(FilenameCleaner.clean("x/x"), is("x_x"));
  }

  @Test
  public void replacesBackslashes() throws Exception {
    assertThat(FilenameCleaner.clean("x\\x"), is("x_x"));
  }

  @Test
  public void replacesColon() throws Exception {
    assertThat(FilenameCleaner.clean("x:x"), is("x_x"));
  }

  @Test
  public void replacesTilde() throws Exception {
    assertThat(FilenameCleaner.clean("x~x"), is("x_x"));
  }

  @Test
  public void replacesTwoInARow() throws Exception {
    assertThat(FilenameCleaner.clean("x~/x"), is("x__x"));
  }

  @Test
  public void replacesNonLatinCharacters() throws Exception {
    assertThat(FilenameCleaner.clean("x\u00FDx"), is("x_x"));
  }
}
