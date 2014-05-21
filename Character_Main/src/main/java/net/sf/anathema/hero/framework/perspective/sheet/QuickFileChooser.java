package net.sf.anathema.hero.framework.perspective.sheet;

import net.sf.anathema.character.framework.item.HeroNameFetcher;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.lang.StringUtilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class QuickFileChooser implements FileChooser {

  private final Resources resources;
  private final Hero hero;

  public QuickFileChooser(Resources resources, Hero hero) {
    this.resources = resources;
    this.hero = hero;
  }

  @Override
  public Path getPrintFile() {
      try {
        String baseName = getBaseName();
        while (baseName.length() < 3) {
          baseName = baseName.concat("_");
        }
        Path path = Files.createTempFile(baseName, PrintCommand.PDF_EXTENSION);
        path.toFile().deleteOnExit();
        return path;
      } catch (IOException e) {
        throw new RuntimeException(resources.getString("Anathema.Reporting.Message.FileCreationFailed"), e);
      }
    }

  private String getBaseName() {
    String name = new HeroNameFetcher().getName(hero);
    return StringUtilities.getFileNameRepresentation(name);
  }
}
