package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.impl.magic.SourceList;
import net.sf.anathema.character.generic.impl.magic.Spell;
import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.CostListBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.ICostListBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.SourceBuilder;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.general.ISourceList;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SpellBuilder {
  private final ICostListBuilder costListBuilder = new CostListBuilder();
  private final SourceBuilder sourceBuilder = new SourceBuilder();
  private IExaltedEdition edition;

  public SpellBuilder(IExaltedEdition edition) {
    this.edition = edition;
  }

  public ISpell[] getSpells() throws SpellException {
    try {
      List<ISpell> spellList = new ArrayList<ISpell>();
      spellList.addAll(readSpells(getSorceryPath()));
      spellList.addAll(readSpells(getNecromancyPath()));
      return spellList.toArray(new ISpell[spellList.size()]);
    } catch (DocumentException e) {
      throw new SpellException(e);
    } catch (PersistenceException e) {
      throw new SpellException(e);
    }
  }

  private String getSorceryPath() {
    return "data/Spells_Sorcery.xml"; //$NON-NLS-1$
  }

  private String getNecromancyPath() {
    return "data/Spells_Necromancy.xml"; //$NON-NLS-1$
  }

  private List<ISpell> readSpells(String path) throws DocumentException, PersistenceException {
    URL spellURL = SpellBuilder.class.getClassLoader().getResource(path);
    Document spellDocument;
    spellDocument = new SAXReader().read(spellURL);
    return buildSpells(spellDocument);
  }

  private List<ISpell> buildSpells(Document spellDocument) throws PersistenceException {
    Element spellListElement = spellDocument.getRootElement();
    List<ISpell> spellList = new ArrayList<ISpell>();
    for (Object spellObject : spellListElement.elements("spell")) { //$NON-NLS-1$
      Element spellElement = (Element) spellObject;
      buildSpell(spellElement, spellList);
    }
    return spellList;
  }

  private void buildSpell(Element spellElement, List<ISpell> spellList) throws PersistenceException {
    String id = spellElement.attributeValue("id"); //$NON-NLS-1$
    String circleId = spellElement.attributeValue("circle"); //$NON-NLS-1$
    ICostList temporaryCost = costListBuilder.buildCostList(spellElement.element("cost")); //$NON-NLS-1$
    Element targetElement = spellElement.element("target"); //$NON-NLS-1$
    String target = null;
    if (targetElement != null) {
      target = targetElement.attributeValue("target"); //$NON-NLS-1$
    }
    ISourceList sourceList = buildSource(spellElement);
    if (!sourceList.hasSource(edition)) {
      return;
    }
    spellList.add(new Spell(id, CircleType.valueOf(circleId), temporaryCost, sourceList, target));
  }

  private ISourceList buildSource(Element spellElement) {
    IExaltedSourceBook[] sources = sourceBuilder.buildSourceList(spellElement);
    SourceList sourceList = new SourceList();
    for (IExaltedSourceBook source : sources) {
      if (source.getEdition() == edition) {
        sourceList.addSource(source);
      }
    }
    return sourceList;
  }
}