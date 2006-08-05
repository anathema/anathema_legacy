package net.sf.anathema.character.impl.module;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.impl.model.CharacterStatisticsConfiguration;
import net.sf.anathema.framework.item.repository.creation.IItemCreationTemplate;
import net.sf.anathema.framework.presenter.IWizardFactory;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.resources.IResources;

public class CharacterCreationWizardPageFactory implements IWizardFactory {

  private final IResources resources;
  private final ICharacterGenerics generics;
  private final IExaltedRuleSet preferredRuleset;

  public CharacterCreationWizardPageFactory(
      ICharacterGenerics generics,
      IExaltedRuleSet preferredRuleset,
      IResources resources) {
    this.generics = generics;
    this.preferredRuleset = preferredRuleset;
    this.resources = resources;
  }

  public IAnathemaWizardPage createPage(IItemCreationTemplate template) {
    if (!(template instanceof CharacterStatisticsConfiguration)) {
      throw new IllegalArgumentException("Bad template type for character creation wizard"); //$NON-NLS-1$
    }
    CharacterItemCreationModel model = new CharacterItemCreationModel(
        generics,
        preferredRuleset,
        (CharacterStatisticsConfiguration) template);
    CharacterItemCreationView view = new CharacterItemCreationView();
    return new CharacterCreationWizardPage(model, view, resources);
  }

  public IItemCreationTemplate createTemplate() {
    return new CharacterStatisticsConfiguration();
  }
}