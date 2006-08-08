package net.sf.anathema.character.impl.model.listening;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.lib.control.GenericControl;

public class CharacterListenerMapping {

  private final Map<ITraitType, TraitListener> traitListeners = new HashMap<ITraitType, TraitListener>();
  private final GenericControl<ICharacterChangeListener> control;
  
  public CharacterListenerMapping(GenericControl<ICharacterChangeListener> control) {
    this.control = control;
  }
  
  public void addTraitListening(ITrait trait) {
    ITraitType traitType = trait.getType();
    if (traitListeners.containsKey(traitType)) {
      return;
    }
    TraitListener traitListener = new TraitListener(control, traitType);
    traitListeners.put(traitType, traitListener);
    trait.addCurrentValueListener(traitListener);
  }

  public void removeTraitListening(ITrait trait) {
    ITraitType traitType = trait.getType();
    if (!traitListeners.containsKey(traitType)) {
      return;
    }
    TraitListener traitListener = traitListeners.get(traitType);
    trait.removeCurrentValueListener(traitListener);
    traitListeners.remove(traitType);
  }
}