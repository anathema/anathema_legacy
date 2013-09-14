package net.sf.anathema.lib.workflow.wizard.selection;

import net.sf.anathema.hero.framework.HeroEnvironment;

public interface CharacterTemplateCreator {

  void createTemplate(HeroEnvironment generics, IItemOperator itemCreator);
}