package net.sf.anathema.hero.combos;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.charm.ICombo;
import net.sf.anathema.character.main.charm.IComboConfigurationListener;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface CombosModel extends HeroModel {

  Identifier ID = new SimpleIdentifier("Combos");

  void addCharmToCombo(ICharm charm, boolean experienced);

  void addComboModelListener(ChangeListener listener);

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