package net.sf.anathema.character.impl.module.repository;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.impl.model.CharacterStatisticsConfiguration;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class CharacterCreationWizardPageFactory implements IWizardFactory {

  private final IResources resources;
  private final ICharacterGenerics generics;

  public CharacterCreationWizardPageFactory(ICharacterGenerics generics, IResources resources) {
    this.generics = generics;
    this.resources = resources;
  }

  public IAnathemaWizardPage createPage(IAnathemaWizardModelTemplate template) {
    if (!(template instanceof CharacterStatisticsConfiguration)) {
      throw new IllegalArgumentException("Bad template type for character creation wizard"); //$NON-NLS-1$
    }
    CharacterItemCreationModel model = new CharacterItemCreationModel(
        generics,
        (CharacterStatisticsConfiguration) template);
    CharacterItemCreationView view = new CharacterItemCreationView();
    return new CharacterCreationWizardPage(model, view, resources);
  }

  public IAnathemaWizardModelTemplate createTemplate() {
    return new CharacterStatisticsConfiguration();
  }

  public boolean needsFurtherDetails() {
    return true;
  }
}