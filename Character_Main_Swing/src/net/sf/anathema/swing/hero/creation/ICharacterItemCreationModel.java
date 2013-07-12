package net.sf.anathema.swing.hero.creation;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.repository.ITemplateTypeAggregation;
import net.sf.anathema.lib.control.ChangeListener;

public interface ICharacterItemCreationModel {

  CharacterType[] getAvailableCharacterTypes();

  void setCharacterType(CharacterType type);

  void addListener(ChangeListener listener);

  ITemplateTypeAggregation[] getAvailableTemplates();

  ITemplateTypeAggregation getSelectedTemplate();

  void setSelectedTemplate(ITemplateTypeAggregation newValue);

  boolean isSelectionComplete();
}