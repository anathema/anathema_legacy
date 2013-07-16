package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface CombosModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("Combos");

  void addCharmToCombo(Charm charm, boolean experienced);

  void addComboModelListener(ChangeListener listener);

  void removeCharmsFromCombo(Charm[] charms);

  void finalizeCombo();

  Combo getEditCombo();

  void addComboConfigurationListener(ComboConfigurationListener listener);

  boolean isComboLegal(Charm charm);

  void deleteCombo(Combo combo);

  void clearCombo();

  void beginComboEdit(Combo combo);

  Combo[] getAllCombos();

  void setCrossPrerequisiteTypeComboAllowed(boolean allowed);
}