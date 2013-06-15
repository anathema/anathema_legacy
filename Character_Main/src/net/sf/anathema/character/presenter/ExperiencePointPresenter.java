package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.advance.ExperienceConfigurationPresenter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.lib.resources.Resources;

public class ExperiencePointPresenter {

  private Resources resources;
  private ICharacter character;

  public ExperiencePointPresenter(Resources resources, ICharacter character) {
    this.resources = resources;
    this.character = character;
  }

  public void initPresentation(final SectionView section) {
    initExperiencePointPresentation(character.getExperienceModel().isExperienced(), section);
    character.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        initExperiencePointPresentation(experienced, section);
      }
    });
  }

  private void initExperiencePointPresentation(boolean experienced, SectionView section) {
    if (experienced) {
      ICharacterType characterType = character.getCharacterTemplate().getTemplateType().getCharacterType();
      String header = resources.getString("CardView.ExperienceConfiguration.Title");
      IExperienceConfigurationView experienceView = section.addView(header, IExperienceConfigurationView.class, characterType);
      new ExperienceConfigurationPresenter(resources, character.getExperienceModel().getExperiencePoints(), experienceView).initPresentation();
    }
  }
}