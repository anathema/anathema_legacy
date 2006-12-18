package net.sf.anathema.character.impl.module;

import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.impl.module.repository.CharacterCreationWizardPageFactory;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.view.AbstractItemTypeViewProperties;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class CharacterViewProperties extends AbstractItemTypeViewProperties {

  private final CharacterCreationWizardPageFactory factory;

  public CharacterViewProperties(
      IItemType type,
      IResources resources,
      CharacterCreationWizardPageFactory factory,
      CharacterPrintNameFileScanner scanner) {
    super(type, new CharacterUI(resources).getCharacterDescriptionTabIcon(), new CharacterTypeUi(resources, scanner));
    this.factory = factory;
  }

  public IWizardFactory getNewItemWizardFactory() {
    return factory;
  }
}