package net.sf.anathema.charmentry.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.sf.anathema.character.generic.type.CharacterType;

public class CharmEntryPropertiesPersister {

  public void addPropertyInternal(CharacterType type, String key, String value) throws IOException {
    File file = new File("../Character_" + type.getId() + "/resources/language/Charms_" + type.getId() + ".properties"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$;
    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
    writer.newLine();
    writer.write(key + "=" + value); //$NON-NLS-1$
    writer.close();
    System.err.println("Properties written in development file."); //$NON-NLS-1$
  }
}