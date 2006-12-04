package net.sf.anathema.character.generic.impl.magic.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import net.sf.anathema.character.generic.impl.template.Table;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class CharmIO {

  private final Table<IIdentificate, IExaltedRuleSet, URL> table = new Table<IIdentificate, IExaltedRuleSet, URL>(
      URL.class);

  public void registerCharmFile(IIdentificate type, IExaltedRuleSet ruleSet, URL resource) {
    table.add(type, ruleSet, resource);
  }

  public Document readCharms(IIdentificate type, IExaltedRuleSet rules) throws DocumentException {
    return new SAXReader().read(table.get(type, rules));
  }

  public void writeCharmInternal(ICharmEntryData charmData) throws IOException, DocumentException {
    CharacterType type = charmData.getCoreData().getCharacterType();
    File file = new File(createFileName(charmData, type));
    Document document = new SAXReader().read(new FileInputStream(file));
    try {
      new CharmWriter().writeCharm(charmData.getCoreData(), document.getRootElement());
    }
    catch (PersistenceException e) {
      throw new IOException(e.getMessage());
    }
    DocumentUtilities.save(document, file);
    System.err.println("Charm written in development file."); //$NON-NLS-1$
  }

  private String createFileName(ICharmEntryData charmData, CharacterType type) {
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