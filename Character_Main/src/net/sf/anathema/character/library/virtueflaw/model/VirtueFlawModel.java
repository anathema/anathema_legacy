package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.character.main.model.experience.ExperienceChange;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.lib.control.GlobalChangeAdapter;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.IChangeListener;

public abstract class VirtueFlawModel extends AbstractAdditionalModelAdapter implements IVirtueFlawModel {

  private final String templateId;
  private final IVirtueFlaw virtueFlaw;
  private final Hero hero;

  public VirtueFlawModel(Hero hero, IAdditionalTemplate additionalTemplate) {
    this.hero = hero;
    this.templateId = additionalTemplate.getId();
    virtueFlaw = new VirtueFlaw(hero);
  }

  @Override
  public boolean isVirtueFlawChangable() {
    return !ExperienceModelFetcher.fetch(hero).isExperienced();
  }

  @Override
  public IVirtueFlaw getVirtueFlaw() {
    return virtueFlaw;
  }

  @Override
  public String getTemplateId() {
    return templateId;
  }

  @Override
  public HeroModelGroup getAdditionalModelType() {
    return HeroModelGroup.SpiritualTraits;
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    GlobalChangeAdapter<String> adapter = new GlobalChangeAdapter<>(listener);
    getVirtueFlaw().addRootChangeListener(listener);
    getVirtueFlaw().getName().addTextChangedListener(adapter);
    getVirtueFlaw().getLimitTrait().addCurrentValueListener(adapter);
  }

  protected Hero getHero() {
    return hero;
  }

  @Override
  public void addVirtueFlawChangableListener(final IBooleanValueChangedListener listener) {
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
          listener.valueChanged(isVirtueFlawChangable());
        }
      }
    });
  }

}