package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.impl.magic.SourceList;
import net.sf.anathema.character.generic.impl.magic.Spell;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.CostListBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.ICostListBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.SourceBuilder;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.general.ISourceList;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class SpellBuilder {
  private final ICostListBuilder costListBuilder = new CostListBuilder();
  private final SourceBuilder sourceBuilder = new SourceBuilder();

  public ISpell[] buildSpells(Document spellDocument) throws PersistenceException {
    Element spellListElement = spellDocument.getRootElement();
    List<ISpell> spellList = new ArrayList<ISpell>();
    for (Object spellObject : spellListElement.elements("spell")) { //$NON-NLS-1$
      Element spellElement = (Element) spellObject;
      buildSpell(spellElement, spellList);
    }
    return spellList.toArray(new ISpell[0]);
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
    if (sourceList.isEmpty()) {
      return;
    }
    spellList.add(new Spell(id, CircleType.valueOf(circleId), temporaryCost, sourceList, target));
  }

  private ISourceList buildSource(Element spellElement) {
    IExaltedSourceBook[] sources = sourceBuilder.buildSourceList(spellElement);
    SourceList sourceList = new SourceList();
    for (IExaltedSourceBook source : sources) {
      sourceList.addSource(source);
    }
    return sourceList;
  }
}