package net.sf.anathema.character.library.trait;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.library.ITraitFavorization;
import net.sf.anathema.character.library.trait.visitor.ITraitVisitor;
import net.sf.anathema.lib.control.IIntValueChangedListener;

public interface ITrait extends IGenericTrait {

  ITraitFavorization getFavorization();

  int getInitialValue();

  int getMaximalValue();

  void addCreationPointListener(IIntValueChangedListener listener);

  void removeCreationPointListener(IIntValueChangedListener listener);

  void addCurrentValueListener(IIntValueChangedListener listener);

  void removeCurrentValueListener(IIntValueChangedListener listener);

  void accept(ITraitVisitor visitor);
}