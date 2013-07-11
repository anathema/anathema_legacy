package net.sf.anathema.hero.combos.model;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.combos.ICombo;
import net.sf.anathema.character.main.magic.model.combos.IComboConfigurationListener;
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

  ICombo getEditCombo();

  void addComboConfigurationListener(IComboConfigurationListener listener);

  boolean isComboLegal(Charm charm);

  void deleteCombo(ICombo combo);

  void clearCombo();

  void beginComboEdit(ICombo combo);

  ICombo[] getAllCombos();

  void setCrossPrerequisiteTypeComboAllowed(boolean allowed);
}