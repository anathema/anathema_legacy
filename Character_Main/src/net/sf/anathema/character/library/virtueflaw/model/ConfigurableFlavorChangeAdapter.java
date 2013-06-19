package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.character.change.ChangeFlavor;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ConfigurableCharacterChangeListener;
import net.sf.anathema.character.main.hero.change.FlavoredChangeListener;
import net.sf.anathema.character.main.model.traits.TraitChangeFlavor;

public class ConfigurableFlavorChangeAdapter implements FlavoredChangeListener {
  private final ConfigurableCharacterChangeListener listener;

  public ConfigurableFlavorChangeAdapter(ConfigurableCharacterChangeListener listener) {
    this.listener = listener;
  }

  @Override
  public void changeOccurred(ChangeFlavor flavor) {
    if (flavor instanceof TraitChangeFlavor) {
      listener.traitChanged(((TraitChangeFlavor) flavor).getTraitType());
    }
  }
}
