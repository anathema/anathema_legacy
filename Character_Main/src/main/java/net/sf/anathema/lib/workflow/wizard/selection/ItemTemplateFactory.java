package net.sf.anathema.lib.workflow.wizard.selection;

import net.sf.anathema.hero.framework.HeroEnvironment;

public interface ItemTemplateFactory {

  IDialogModelTemplate NO_TEMPLATE = null;

  IDialogModelTemplate createTemplate(HeroEnvironment generics);
}