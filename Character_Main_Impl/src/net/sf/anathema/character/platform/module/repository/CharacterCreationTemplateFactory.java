package net.sf.anathema.character.platform.module.repository;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.impl.model.CharacterStatisticsConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.wizard.selection.DialogBasedTemplateFactory;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;

public class CharacterCreationTemplateFactory implements DialogBasedTemplateFactory {

  private final IResources resources;
  private final ICharacterGenerics generics;

  public CharacterCreationTemplateFactory(ICharacterGenerics generics, IResources resources) {
    this.generics = generics;
    this.resources = resources;
  }

  @Override
  public IDialogPage createPage(IDialogModelTemplate template) {
    if (!(template instanceof CharacterStatisticsConfiguration)) {
      throw new IllegalArgumentException("Bad template type for character creation wizard"); //$NON-NLS-1$
    }
    CharacterItemCreationModel model = new CharacterItemCreationModel(generics, (CharacterStatisticsConfiguration) template);
    CharacterItemCreationView view = new CharacterItemCreationView();
    CharacterCreationPageProperties properties = new CharacterCreationPageProperties(resources);
    return new CharacterCreationDialogPage(model, view, properties);
  }

  @Override
  public IDialogModelTemplate createTemplate() {
    return new CharacterStatisticsConfiguration();
  }

  @Override
  public boolean needsFurtherDetails() {
    return true;
  }
}