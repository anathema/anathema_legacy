package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.main.experience.model.ExperienceModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ISpellConfiguration;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SpellRemoveListener implements ListSelectionListener {
  private final JList list;
  private final JButton button;
  private final ICharacter character;
  private final ISpellConfiguration spellConfiguration;

  public SpellRemoveListener(JList list, JButton button, ICharacter character, ISpellConfiguration spellConfiguration) {
    this.list = list;
    this.button = button;
    this.character = character;
    this.spellConfiguration = spellConfiguration;
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    boolean enabled = !list.isSelectionEmpty();
    if (enabled && ExperienceModelFetcher.fetch(character).isExperienced()) {
      for (Object spellObject : list.getSelectedValuesList()) {
        ISpell spell = (ISpell) spellObject;
        if (spellConfiguration.isLearnedOnCreation(spell)) {
          enabled = false;
          break;
        }
      }
    }
    button.setEnabled(enabled);
  }
}