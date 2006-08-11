package net.sf.anathema.character.impl.module;

import javax.swing.Icon;

import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.module.repository.CharacterCreationWizardPageFactory;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.view.AbstractItemTypeCreationViewProperties;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileScanner;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class CharacterCreationViewProperties extends AbstractItemTypeCreationViewProperties {

  private final CharacterCreationWizardPageFactory factory;
  private final CharacterPrintNameFileScanner scanner;
  private final IResources resources;

  public CharacterCreationViewProperties(
      IItemType type,
      IResources resources,
      CharacterCreationWizardPageFactory factory,
      IRegistry<CharacterType, ICasteCollection> registry) {
    super(type, new CharacterUI(resources).getCharacterDescriptionTabIcon());
    this.scanner = new CharacterPrintNameFileScanner(registry);
    this.resources = resources;
    this.factory = factory;
  }

  public IWizardFactory getNewItemWizardFactory() {
    return factory;
  }

  public Icon getPrintNameFileIcon(PrintNameFile file) {
    return new CharacterUI(resources).getSmallTypeIcon(scanner.getCharacterType(file.getRepositoryId()));
  }

  public String getPrintNameFileLabel(PrintNameFile file) {
    String id = file.getRepositoryId();
    String printName = file.getPrintName();
    CharacterType characterType = scanner.getCharacterType(id);
    String characterString = resources.getString("CharacterGenerator.NewCharacter." + characterType.getId() + ".Name"); //$NON-NLS-1$//$NON-NLS-2$
    IIdentificate casteType = scanner.getCasteType(id);
    if (casteType == null) {
      return resources.getString("LoadCharacter.PrintNameFile.ShortMessage", new Object[] { //$NON-NLS-1$
          printName, characterString });
    }
    String casteTypeString = resources.getString("Caste." + casteType.getId()); //$NON-NLS-1$
    String casteString = resources.getString(characterType.getId() + ".Caste.Label"); //$NON-NLS-1$
    return resources.getString("LoadCharacter.PrintNameFile.Message", new Object[] { //$NON-NLS-1$
        printName, characterString, casteTypeString, casteString });
  }

  public IPrintNameFileScanner getScanner() {
    return scanner;
  }
}