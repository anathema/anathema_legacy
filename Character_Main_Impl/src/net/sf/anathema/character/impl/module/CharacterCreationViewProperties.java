package net.sf.anathema.character.impl.module;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.module.repository.CharacterCreationWizardPageFactory;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.view.AbstractItemTypeCreationViewProperties;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileScanner;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class CharacterCreationViewProperties extends AbstractItemTypeCreationViewProperties {

  private final CharacterCreationWizardPageFactory factory;
  private final CharacterPrintNameFileScanner scanner;
  private final IObjectUi ui;

  public CharacterCreationViewProperties(
      IItemType type,
      IResources resources,
      CharacterCreationWizardPageFactory factory,
      IRegistry<CharacterType, ICasteCollection> registry) {
    super(type, new CharacterUI(resources).getCharacterDescriptionTabIcon());
    this.scanner = new CharacterPrintNameFileScanner(registry);
    this.factory = factory;
    this.ui = new CharacterTypeUi(resources, scanner);
  }

  public IWizardFactory getNewItemWizardFactory() {
    return factory;
  }

  public IObjectUi getItemTypeUI() {
    return ui;
  }

  public IPrintNameFileScanner getScanner() {
    return scanner;
  }
}