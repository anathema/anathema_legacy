package net.sf.anathema.charmentry.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.CharacterType;

public class CharmEntryPropertiesPersister {

  public void addPropertyInternal(CharacterType type, IExaltedEdition edition, String key, String value)
      throws IOException {
    String fileName = null;
    if (edition == ExaltedEdition.SecondEdition) {
      fileName = "../Character_" + type.getId() + "/resources/language/Charms_" + type.getId() + "_" + edition.getId() + ".properties";//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$;
    }
    else {
      fileName = "../Character_" + type.getId() + "/resources/language/Charms_" + type.getId() + ".properties";//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$;
    }
    File file = new File(fileName);
    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
    writer.newLine();
    writer.write(key + "=" + value); //$NON-NLS-1$
    writer.close();
    System.err.println("Properties written in development file."); //$NON-NLS-1$
  }
}