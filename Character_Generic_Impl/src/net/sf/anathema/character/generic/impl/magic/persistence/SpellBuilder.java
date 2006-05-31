package net.sf.anathema.character.generic.impl.magic.persistence;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.character.generic.impl.magic.SourceList;
import net.sf.anathema.character.generic.impl.magic.Spell;
import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.CostListBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.ICostListBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.SourceBuilder;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.character.generic.magic.general.IPermanentCostList;
import net.sf.anathema.character.generic.magic.general.ISourceList;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SpellBuilder {
  private static ISpell[] spells;
  private final ICostListBuilder costListBuilder = new CostListBuilder();
  private final SourceBuilder sourceBuilder = new SourceBuilder();
  private final static SpellBuilder instance = new SpellBuilder();

  private SpellBuilder() {
    // Nothing to do
  }

  public static SpellBuilder getInstance() {
    return instance;
  }

  public ISpell[] getSpells() throws SpellException {
    if (spells != null) {
      return spells;
    }
    try {
      List<ISpell> spellList = new ArrayList<ISpell>();
      spellList.addAll(Arrays.asList(readSpells(getSorceryPath())));
      spellList.addAll(Arrays.asList(readSpells(getNecromancyPath())));
      spells = spellList.toArray(new ISpell[spellList.size()]);
      return spells;
    }
    catch (DocumentException e) {
      throw new SpellException(e);
    }
    catch (PersistenceException e) {
      throw new SpellException(e);
    }
  }

  private String getSorceryPath() {
    return "data/Spells_Sorcery.xml"; //$NON-NLS-1$
  }

  private String getNecromancyPath() {
    return "data/Spells_Necromancy.xml"; //$NON-NLS-1$
  }

  private ISpell[] readSpells(String path) throws DocumentException, PersistenceException {
    final URL spellURL = SpellBuilder.class.getClassLoader().getResource(path);
    Document spellDocument;
    spellDocument = new SAXReader().read(spellURL);
    return buildSpells(spellDocument);
  }

  private ISpell[] buildSpells(Document spellDocument) throws PersistenceException {
    Element spellListElement = spellDocument.getRootElement();
    List<ISpell> spellList = new ArrayList<ISpell>();
    for (Object spellObject : spellListElement.elements("spell")) { //$NON-NLS-1$
      Element spellElement = (Element) spellObject;
      spellList.add(buildSpell(spellElement));
    }
    return spellList.toArray(new ISpell[0]);
  }

  private ISpell buildSpell(Element spellElement) throws PersistenceException {
    String id = spellElement.attributeValue("id"); //$NON-NLS-1$
    String circleId = spellElement.attributeValue("circle"); //$NON-NLS-1$
    ICostList temporaryCost = costListBuilder.buildTemporaryCostList(spellElement.element("cost").element("temporary")); //$NON-NLS-1$ //$NON-NLS-2$
    IPermanentCostList permanentCost = costListBuilder.buildPermanentCostList(spellElement.element("cost").element("permanent")); //$NON-NLS-1$ //$NON-NLS-2$
    final ISourceList sourceList = buildSource(spellElement);
    return new Spell(id, CircleType.valueOf(circleId), temporaryCost, permanentCost, sourceList);
  }

  private ISourceList buildSource(Element spellElement) {
    final IMagicSource[] sources = sourceBuilder.buildSourceList(spellElement);
    final SourceList sourceList = new SourceList();
    for (IMagicSource source : sources) {
      sourceList.addSource(source);
    }
    return sourceList;
  }
}