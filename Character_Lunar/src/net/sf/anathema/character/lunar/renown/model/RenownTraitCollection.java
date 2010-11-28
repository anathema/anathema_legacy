package net.sf.anathema.character.lunar.renown.model;

import net.sf.anathema.character.library.trait.AbstractTraitCollection;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.lib.control.change.GlobalChangeAdapter;
import net.sf.anathema.lib.control.change.IChangeListener;

public class RenownTraitCollection extends AbstractTraitCollection {

  public void addRenownTrait(IDefaultTrait trait) {
    super.addTrait(trait);
  }

  public void addChangeListener(IChangeListener listener) {
    for (ITrait trait : getAllTraits()) {
      trait.addCurrentValueListener(new GlobalChangeAdapter<Object>(listener));
    }
  }
}