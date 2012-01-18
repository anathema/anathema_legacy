package net.sf.anathema.charmentry.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import net.sf.anathema.character.generic.impl.magic.persistence.ICharmEntryData;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class CharmIO {

  public void writeCharmInternal(ICharmEntryData charmData) throws IOException, DocumentException {
    ICharacterType type = charmData.getCoreData().getCharacterType();
    File file = new File(createFileName(charmData, type));
    FileInputStream stream = new FileInputStream(file);
    Document document = new SAXReader().read(stream);
    try {
      new CharmWriter().writeCharm(charmData.getCoreData(), document.getRootElement());
    }
    catch (PersistenceException e) {
      throw new IOException(e.getMessage());
    }
    finally {
      IOUtils.closeQuietly(stream);
    }
    DocumentUtilities.save(document, file);
    System.err.println("Charm written in development file."); //$NON-NLS-1$
  }

  private String createFileName(ICharmEntryData charmData, ICharacterType itype) {
    CharacterType type = (CharacterType) itype;
    return "../Character_" //$NON-NLS-1$
        + type.name()
        + "/resources/" //$NON-NLS-1$
        + "data/Charms_" //$NON-NLS-1$
        + type.getId()
        + "_" //$NON-NLS-1$
        + charmData.getEdition().getDefaultRuleset().getId()
        + ".xml"; //$NON-NLS-1$
  }
}