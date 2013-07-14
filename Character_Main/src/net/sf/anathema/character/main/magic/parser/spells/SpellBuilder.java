package net.sf.anathema.character.main.magic.parser.spells;

import net.sf.anathema.character.main.magic.model.magic.cost.ICostList;
import net.sf.anathema.character.main.magic.model.magic.source.ISourceList;
import net.sf.anathema.character.main.magic.model.magic.source.SourceList;
import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.character.main.magic.model.spells.Spell;
import net.sf.anathema.character.main.magic.model.spells.SpellImpl;
import net.sf.anathema.character.main.magic.parser.magic.CostListBuilder;
import net.sf.anathema.character.main.magic.parser.magic.ICostListBuilder;
import net.sf.anathema.character.main.magic.parser.magic.IExaltedSourceBook;
import net.sf.anathema.character.main.magic.parser.magic.SourceBuilder;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class SpellBuilder {
  private final ICostListBuilder costListBuilder = new CostListBuilder();
  private final SourceBuilder sourceBuilder = new SourceBuilder();

  public Spell[] buildSpells(Document spellDocument) throws PersistenceException {
    Element spellListElement = spellDocument.getRootElement();
    List<Spell> spellList = new ArrayList<>();
    for (Object spellObject : spellListElement.elements("spell")) {
      Element spellElement = (Element) spellObject;
      buildSpell(spellElement, spellList);
    }
    return spellList.toArray(new Spell[spellList.size()]);
  }

  private void buildSpell(Element spellElement, List<Spell> spellList) throws PersistenceException {
    String id = spellElement.attributeValue("id");
    String circleId = spellElement.attributeValue("circle");
    ICostList temporaryCost = costListBuilder.buildCostList(spellElement.element("cost"));
    Element targetElement = spellElement.element("target");
    String target = null;
    if (targetElement != null) {
      target = targetElement.attributeValue("target");
    }
    ISourceList sourceList = buildSource(spellElement);
    if (sourceList.isEmpty()) {
      return;
    }
    spellList.add(new SpellImpl(id, CircleType.valueOf(circleId), temporaryCost, sourceList, target));
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