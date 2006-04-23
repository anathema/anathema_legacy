package net.sf.anathema.character.generic.impl.magic.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class CharmIO {

  private static final String ATTRIBUTE_BASE_RULES = "baseRules"; //$NON-NLS-1$

  public Document readCharms(final IIdentificate type) throws DocumentException {
    String urlString = "data/Charms_" + type.getId() + ".xml"; //$NON-NLS-1$ //$NON-NLS-2$;
    final URL charmURL = type.getClass().getClassLoader().getResource(urlString);
    if (charmURL == null) {
      throw new NullPointerException("Resource not found in classpath: " + urlString); //$NON-NLS-1$
    }
    return new SAXReader().read(charmURL);
  }

  public Document readCustomCharms(final IIdentificate type) throws DocumentException {
    File charmFile = new File("./data/Charms_" + type.getId() + "_Custom.xml"); //$NON-NLS-1$ //$NON-NLS-2$
    if (charmFile.exists()) {
      try {
        InputStream externalStream = new FileInputStream(charmFile);
        SAXReader saxReader = new SAXReader();
        return saxReader.read(externalStream);
      }
      catch (FileNotFoundException e) {
        // Nothing to do
      }
    }
    return null;
  }

  public void writeCharmInternal(ICharmData charmData, List<ICharmAttribute> keywords)
      throws IOException,
      DocumentException {
    CharacterType type = charmData.getCharacterType();
    File file = new File("../Character_" + type.getId() + "/resources/data/Charms_" + type.getId() + ".xml"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$;
    Document document = new SAXReader().read(new FileInputStream(file));
    try {
      new CharmWriter().writeCharm(charmData, keywords, document.getRootElement());
    }
    catch (PersistenceException e) {
      throw new IOException(e.getMessage());
    }
    DocumentUtilities.save(document, file);
    System.err.println("Charm written in development file."); //$NON-NLS-1$
  }

  public String getBaseRules(Document charmDocument) {
    return charmDocument.getRootElement().attributeValue(ATTRIBUTE_BASE_RULES);
  }
}