package net.sf.anathema.character.generic.impl.magic.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.collection.Predicate;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.util.Identificate;

import org.dom4j.Document;
import org.dom4j.DocumentException;

public class CharmCache implements ICharmCache {

  private static CharmCache instance = new CharmCache();
  private final MultiEntryMap<CharacterType, ICharm> charmsByType = new MultiEntryMap<CharacterType, ICharm>();
  private final MultiEntryMap<CharacterType, ICharm> powerCombatCharmsByType = new MultiEntryMap<CharacterType, ICharm>();
  private final List<ICharm> martialArtsCharms = new ArrayList<ICharm>();
  private List<ICharm> powerCombatMartialArtsCharms = new ArrayList<ICharm>();
  private final CharmIO charmIo = new CharmIO();
  private final CharmBuilder builder = new CharmBuilder();

  private CharmCache() {
    // Nothing to do
  }

  public static CharmCache getInstance() {
    return instance;
  }

  public ICharm[] getCharms(final CharacterType type, boolean powerCombat) throws PersistenceException {
    if (charmsByType.containsKey(type)) {
      return getCharmArray(type, powerCombat);
    }
    buildOfficialCharms(type);
    buildCustomCharms(type);
    return getCharmArray(type, powerCombat);
  }

  private ICharm[] getCharmArray(final CharacterType type, boolean powerCombat) {
    List<ICharm> charmList = powerCombat ? powerCombatCharmsByType.get(type) : charmsByType.get(type);
    return charmList.toArray(new ICharm[charmList.size()]);
  }

  private void buildOfficialCharms(final CharacterType type) throws PersistenceException {
    try {
      Document charmDocument = charmIo.readCharms(type);
      buildCharmsFromDocument(type, charmDocument);
    }
    catch (DocumentException e) {
      throw new CharmException(e);
    }
  }

  private void buildCustomCharms(final CharacterType type) throws PersistenceException {
    try {
      Document document = charmIo.readCustomCharms(type);
      if (document != null) {
        buildCharmsFromDocument(type, document);
      }
    }
    catch (DocumentException e) {
      throw new CharmException(e);
    }
  }

  private void buildCharmsFromDocument(final CharacterType type, Document charmDocument) throws PersistenceException {
    ICharm[] coreRulesCharmArray = builder.buildCoreRulesCharms(charmDocument);
    ICharm[] powerCombatCharmArray = builder.buildPowerCombatCharms(charmDocument);
    for (ICharm charm : coreRulesCharmArray) {
      charmsByType.add(type, charm);
    }
    for (ICharm charm : powerCombatCharmArray) {
      powerCombatCharmsByType.add(type, charm);
    }
  }

  public ICharm[] getMartialArtsCharms(boolean powerCombat) throws PersistenceException {
    List<ICharm> charmList = getMartialArtsList(powerCombat);
    if (charmList.isEmpty()) {
      try {
        Document charmDocument = charmIo.readCharms(new Identificate("MartialArts")); //$NON-NLS-1$
        charmList.addAll(Arrays.asList(builder.buildMartialArtsCharms(charmDocument, powerCombat)));
      }
      catch (DocumentException e) {
        throw new CharmException(e);
      }
    }
    return charmList.toArray(new ICharm[charmList.size()]);
  }

  private List<ICharm> getMartialArtsList(boolean powerCombat) {
    if (powerCombat) {
      return powerCombatMartialArtsCharms;
    }
    return martialArtsCharms;
  }

  public Charm searchCharm(final String charmId) {
    try {
      String[] idParts = charmId.split("\\."); //$NON-NLS-1$
      CharacterType characterTypeId = CharacterType.getById(idParts[0]);
      ICharm[] charms = getCharms(characterTypeId, false);
      ICharm parentCharm = ArrayUtilities.find(new Predicate<ICharm>() {
        @Override
        public boolean evaluate(ICharm t) {
          return t.getId().equals(charmId);
        }
      }, charms);
      return (Charm) parentCharm;
    }
    catch (PersistenceException e) {
      return null;
    }
  }

  public void addCharm(ICharmData charmData, List<ICharmAttribute> keywords) throws IOException, DocumentException {
    ICharm charm = new Charm(charmData);
    charmsByType.add(charm.getCharacterType(), charm);
    charmIo.writeCharmInternal(charm, keywords);
  }
}