package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.presenter.ExtensibleTraitView;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public interface SpecialtiesConfigurationView {

  void initGui(ICharacterType type);

  ExtensibleTraitView addSpecialtyView(String abilityName, String specialtyName, RelativePath deleteIcon, int value, int maxValue);

  SpecialtyCreationView addSpecialtyCreationView(AgnosticUIConfiguration<TraitType> configuration, RelativePath addIcon);
}