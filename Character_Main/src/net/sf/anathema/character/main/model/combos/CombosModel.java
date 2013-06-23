package net.sf.anathema.character.main.model.combos;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfigurationListener;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface CombosModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("Combos");

  void addCharmToCombo(ICharm charm, boolean experienced);

  void addComboModelListener(IChangeListener listener);

  void removeCharmsFromCombo(ICharm[] charms);

  void finalizeCombo();

  ICombo getEditCombo();

  void addComboConfigurationListener(IComboConfigurationListener listener);

  boolean isComboLegal(ICharm charm);

  void deleteCombo(ICombo combo);

  void clearCombo();

  void beginComboEdit(ICombo combo);

  ICombo[] getAllCombos();

  void setCrossPrerequisiteTypeComboAllowed(boolean allowed);
}