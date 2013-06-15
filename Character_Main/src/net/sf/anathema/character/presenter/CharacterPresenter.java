package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalInitializer;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.initializers.CoreModelInitializer;
import net.sf.anathema.character.presenter.initializers.Initializers;
import net.sf.anathema.character.view.CharacterView;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.generic.framework.CharacterGenericsExtractor.getGenerics;
import static net.sf.anathema.character.model.CharacterModelGroup.Magic;
import static net.sf.anathema.character.model.CharacterModelGroup.Miscellaneous;
import static net.sf.anathema.character.model.CharacterModelGroup.NaturalTraits;
import static net.sf.anathema.character.model.CharacterModelGroup.Outline;
import static net.sf.anathema.character.model.CharacterModelGroup.SpiritualTraits;

public class CharacterPresenter implements Presenter {

  private final ICharacter character;
  private final CharacterView characterView;
  private final IApplicationModel applicationModel;
  private final Resources resources;
  private final PointPresentationStrategy pointPresentation;
  private final Initializers initializers;

  public CharacterPresenter(ICharacter character, CharacterView view, Resources resources, IApplicationModel applicationModel,
                            PointPresentationStrategy pointPresentation) {
    this.character = character;
    this.characterView = view;
    this.resources = resources;
    this.applicationModel = applicationModel;
    this.pointPresentation = pointPresentation;
    this.initializers = new Initializers(applicationModel);
  }

  @Override
  public void initPresentation() {
    initializeSection("CardView.Outline.Title", Outline);
    initializeSection("CardView.NaturalTraits.Title", NaturalTraits);
    initializeSection("CardView.SpiritualTraits.Title", SpiritualTraits);
    initializeSection("CardView.CharmConfiguration.Title", Magic);
    SectionView sectionView = addSection("CardView.MiscellaneousConfiguration.Title");
    initializeGroup(Miscellaneous, sectionView);
    pointPresentation.initPresentation(sectionView);
  }

  private void initializeSection(String titleKey, CharacterModelGroup group) {
    SectionView sectionView = addSection(titleKey);
    initializeGroup(group, sectionView);
  }

  private void initializeGroup(CharacterModelGroup group, SectionView sectionView) {
    for (CoreModelInitializer initializer : initializers.getInOrderFor(group)) {
      initializer.initialize(sectionView, character, resources);
    }
    initializeAdditionalModels(sectionView, group);
  }

  private SectionView addSection(String titleKey) {
    String sectionTitle = getString(titleKey);
    return characterView.addSection(sectionTitle);
  }

  public void initializeAdditionalModels(SectionView sectionView, CharacterModelGroup group) {
    IRegistry<String, IAdditionalInitializer> factoryRegistry = getGenerics(applicationModel).getAdditionalInitializerRegistry();
    for (IAdditionalModel model : character.getExtendedConfiguration().getAdditionalModels(group)) {
      IAdditionalInitializer initializer = factoryRegistry.get(model.getTemplateId());
      if (initializer == null) {
        continue;
      }
      initializer.initialize(model, resources, characterType(), sectionView);
    }
  }

  private String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }

  private ICharacterType characterType() {
    return character.getCharacterTemplate().getTemplateType().getCharacterType();
  }
}