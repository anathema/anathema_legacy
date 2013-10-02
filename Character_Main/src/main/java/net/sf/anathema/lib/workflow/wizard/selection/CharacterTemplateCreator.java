package net.sf.anathema.lib.workflow.wizard.selection;

import net.sf.anathema.hero.creation.ICharacterItemCreationModel;

public interface CharacterTemplateCreator {

  void createTemplate(final IItemOperator operator, final ICharacterItemCreationModel creationModel);
}