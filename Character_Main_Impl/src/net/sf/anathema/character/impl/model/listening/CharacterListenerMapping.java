package net.sf.anathema.character.impl.model.listening;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.lib.control.GenericControl;

public class CharacterListenerMapping {

  private final Map<ITrait, TraitListener> traitListeners = new HashMap<ITrait, TraitListener>();
  private final GenericControl<ICharacterChangeListener> control;

  public CharacterListenerMapping(GenericControl<ICharacterChangeListener> control) {
    this.control = control;
  }

  public void addTraitListening(ITrait trait) {
    if (traitListeners.containsKey(trait)) {
      return;
    }
    TraitListener traitListener = new TraitListener(control, trait.getType());
    traitListeners.put(trait, traitListener);
    trait.addCurrentValueListener(traitListener);
  }

  public void removeTraitListening(ITrait trait) {
    if (!traitListeners.containsKey(trait)) {
      return;
    }
    TraitListener traitListener = traitListeners.get(trait);
    trait.removeCurrentValueListener(traitListener);
    traitListeners.remove(trait);
  }
}