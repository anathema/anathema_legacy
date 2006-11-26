package net.sf.anathema.charmentry.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.charms.duration.IDuration;
import net.sf.anathema.character.generic.magic.charms.duration.QualifiedAmountDuration;
import net.sf.anathema.character.generic.magic.charms.duration.SimpleDuration;
import net.sf.anathema.character.generic.magic.charms.duration.UntilEventDuration;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.resources.IResources;

public class CharmEntryPropertiesPersister {

  public void writeCharmPageProperty(CharacterType type, String key, IExaltedSourceBook book, int page)
      throws IOException {
    String fileName = "../Character_" + type.getId() + "/resources/language/Charms_" + type.getId() + "_Pages.properties";//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //;
    File file = new File(fileName);
    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
    writer.newLine();
    writer.write(book + "." + key + ".Page=" + page); //$NON-NLS-1$ //$NON-NLS-2$
    writer.close();
  }

  public void writeCharmNameProperty(CharacterType type, IExaltedEdition edition, String key, String value)
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
  }

  public void writeDurationProperty(IResources resources, IDuration duration) throws IOException {
    String fileName = "../Character_Main/resources/language/CharmDuration.properties";
    File file = new File(fileName);
    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
    writer.newLine();
    String writeString;
    if (duration instanceof UntilEventDuration) {
      writeString = createCamelCase("Charm.Event.", ((UntilEventDuration) duration).getEvent());
    }
    else if (duration instanceof SimpleDuration) {
      writeString = createCamelCase("Charm.Duration.", ((SimpleDuration) duration).getText());
    }
    else if (duration instanceof QualifiedAmountDuration) {
      QualifiedAmountDuration amountDuration = (QualifiedAmountDuration) duration;
      String amount = amountDuration.getAmount();
      writeString = createCamelCase("Charm.Unit.", (amountDuration).getUnit());
      try {
        if (Integer.parseInt(amount) == 1) {
          writeString += ".Singular";
        }
      }
      catch (NumberFormatException e) {
        // Nothing to do
      }
      writeString += ".Plural"; //$NON-NLS-2$
    }
    else {
      throw new UnreachableCodeReachedException("Unknown Duration Type");
    }
    if (resources.supportsKey(writeString)) {
      return;
    }
    writer.write(writeString);
    writer.close();
    return;
  }

  private String createCamelCase(String prefix, String string) {
    StringBuilder writeString = new StringBuilder(prefix);
    String[] split = string.split(" ");
    for (String splitPart : split) {
      writeString.append(String.valueOf(splitPart.charAt(0)).toUpperCase());
      writeString.append(splitPart.substring(1));
    }
    return writeString.toString();
  }
}