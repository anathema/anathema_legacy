package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.ObjectValueListener;

public interface SpecialtyCreationView {

  void addSelectionChangedListener(ObjectValueListener<TraitType> name);

  void addEditChangedListener(ObjectValueListener<String> name);

  void whenAddButtonIsClicked(Command command);

  void clear();

  void setButtonEnabled(boolean enabled);

  void setObjects(TraitType[] objects);

  void enterName(String currentName);

  void selectTrait(TraitType currentTrait);
}