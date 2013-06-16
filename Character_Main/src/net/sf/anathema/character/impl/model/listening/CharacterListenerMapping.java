package net.sf.anathema.character.impl.model.listening;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.library.trait.Trait;
import org.jmock.example.announcer.Announcer;

import java.util.HashMap;
import java.util.Map;

public class CharacterListenerMapping {

  private final Map<Trait, TraitListener> traitListeners = new HashMap<>();
  private final Announcer<ICharacterChangeListener> control;

  public CharacterListenerMapping(Announcer<ICharacterChangeListener> control) {
    this.control = control;
  }

  public void addTraitListening(Trait trait) {
    if (traitListeners.containsKey(trait)) {
      return;
    }
    TraitListener traitListener = new TraitListener(control, trait.getType());
    traitListeners.put(trait, traitListener);
    trait.addCurrentValueListener(traitListener);
  }
}