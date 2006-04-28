package net.sf.anathema.character.generic.impl.magic.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class CharmIO {

  public Document readCharms(IIdentificate type, IExaltedRuleSet rules) throws DocumentException {
    String urlString = createFileName(type, rules);
    final URL charmURL = type.getClass().getClassLoader().getResource(urlString);
    if (charmURL == null) {
      throw new NullPointerException("Resource not found in classpath: " + urlString); //$NON-NLS-1$
    }
    return new SAXReader().read(charmURL);
  }

  private String createFileName(IIdentificate type, IExaltedRuleSet rules) {
    return "data/Charms_" + type.getId() + "_" + rules.getId() + ".xml";//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$;
  }

  public void writeCharmInternal(ICharmData charmData, List<ICharmAttribute> keywords, IExaltedRuleSet set)
      throws IOException,
      DocumentException {
    CharacterType type = charmData.getCharacterType();
    File file = new File("../Character_" //$NON-NLS-1$
        + type.getId()
        + "/resources/" //$NON-NLS-1$
        + createFileName(type, set));
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
}