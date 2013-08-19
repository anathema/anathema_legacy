package net.sf.anathema.hero.specialties.display.view;

import net.sf.anathema.character.main.library.util.CssSkinner;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.fx.hero.perspective.AbstractBridgingView;
import net.sf.anathema.hero.display.ExtensibleTraitView;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtiesConfigurationView;
import net.sf.anathema.hero.specialties.display.presenter.SpecialtyCreationView;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public class BridgingSpecialtiesView extends AbstractBridgingView implements SpecialtiesConfigurationView {
  private final FxSpecialtiesView fxView;

  public BridgingSpecialtiesView(FxSpecialtiesView fxView, CharacterType type) {
    this.fxView = fxView;
    String[] skins = new CssSkinner().getSkins(type);
    init(fxView, skins);
  }

  @Override
  public ExtensibleTraitView addSpecialtyView(String abilityName, String specialtyName, RelativePath deleteIcon, int value, int maxValue) {
    return fxView.addSpecialtyView(abilityName, specialtyName, deleteIcon, value, maxValue);
  }

  @Override
  public SpecialtyCreationView addSpecialtyCreationView(AgnosticUIConfiguration<TraitType> configuration, RelativePath addIcon) {
    return fxView.addSpecialtyCreationView(configuration, addIcon);
  }
}
