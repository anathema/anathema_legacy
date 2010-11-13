package net.sf.anathema.character.impl.persistence;

import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_EXPERIENCE_LEARNED;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.ATTRIB_NAME;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_SPELL;
import static net.sf.anathema.character.impl.persistence.ICharacterXmlConstants.TAG_SPELLS;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class SpellConfigurationPersister {

  public void save(Element parent, ISpellConfiguration spells) {
    Element spellsElement = parent.addElement(TAG_SPELLS);
    for (ISpell spell : spells.getLearnedSpells()) {
      Element spellElement = spellsElement.addElement(TAG_SPELL);
      spellElement.addAttribute(ATTRIB_NAME, spell.getId());
      spellElement.addAttribute(ATTRIB_EXPERIENCE_LEARNED, String.valueOf(!spells.isLearnedOnCreation(spell)));
    }
  }

  public void load(Element parent, ISpellConfiguration spells) {
    Element spellsElement = parent.element(TAG_SPELLS);
    if (spellsElement == null) {
      return;
    }
    List<ISpell> creationSpellList = new ArrayList<ISpell>();
    List<ISpell> experienceSpellList = new ArrayList<ISpell>();
    for (Object spellObjectElement : spellsElement.elements(TAG_SPELL)) {
      Element spellElement = (Element) spellObjectElement;
      boolean experienceLearned = ElementUtilities.getBooleanAttribute(spellElement, ATTRIB_EXPERIENCE_LEARNED, false);
      if (experienceLearned) {
        experienceSpellList.add(spells.getSpellById(spellElement.attributeValue(ATTRIB_NAME)));
      }
      else {
        creationSpellList.add(spells.getSpellById(spellElement.attributeValue(ATTRIB_NAME)));
      }
    }
    spells.addSpells(creationSpellList.toArray(new ISpell[creationSpellList.size()]), false);
    spells.addSpells(experienceSpellList.toArray(new ISpell[experienceSpellList.size()]), true);
  }
}