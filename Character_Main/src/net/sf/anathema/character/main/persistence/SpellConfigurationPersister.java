package net.sf.anathema.character.main.persistence;

import net.sf.anathema.character.main.magic.ISpell;
import net.sf.anathema.hero.spells.SpellModel;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.ATTRIB_EXPERIENCE_LEARNED;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.ATTRIB_NAME;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_SPELL;
import static net.sf.anathema.character.main.persistence.ICharacterXmlConstants.TAG_SPELLS;

public class SpellConfigurationPersister {

  public void save(Element parent, SpellModel spells) {
    Element spellsElement = parent.addElement(TAG_SPELLS);
    for (ISpell spell : spells.getLearnedSpells()) {
      Element spellElement = spellsElement.addElement(TAG_SPELL);
      spellElement.addAttribute(ATTRIB_NAME, spell.getId());
      spellElement.addAttribute(ATTRIB_EXPERIENCE_LEARNED, String.valueOf(!spells.isLearnedOnCreation(spell)));
    }
  }

  public void load(Element parent, SpellModel spells) {
    Element spellsElement = parent.element(TAG_SPELLS);
    if (spellsElement == null) {
      return;
    }
    List<ISpell> creationSpellList = new ArrayList<>();
    List<ISpell> experienceSpellList = new ArrayList<>();
    for (Object spellObjectElement : spellsElement.elements(TAG_SPELL)) {
      Element spellElement = (Element) spellObjectElement;
      boolean experienceLearned = ElementUtilities.getBooleanAttribute(spellElement, ATTRIB_EXPERIENCE_LEARNED, false);
      if (experienceLearned) {
        experienceSpellList.add(spells.getSpellById(spellElement.attributeValue(ATTRIB_NAME)));
      } else {
        creationSpellList.add(spells.getSpellById(spellElement.attributeValue(ATTRIB_NAME)));
      }
    }
    spells.addSpells(creationSpellList.toArray(new ISpell[creationSpellList.size()]), false);
    spells.addSpells(experienceSpellList.toArray(new ISpell[experienceSpellList.size()]), true);
  }
}