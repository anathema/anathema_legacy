package net.sf.anathema.lib.workflow.wizard.selection;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.creation.ICharacterItemCreationModel;

public interface CharacterTemplateCreator {

  void createTemplate(final IItemOperator operator, final ICharacterItemCreationModel creationModel);

  void useResources(Resources resources);
}