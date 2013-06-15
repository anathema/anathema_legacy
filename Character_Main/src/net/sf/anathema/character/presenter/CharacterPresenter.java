package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalInitializer;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.initializers.AbilitiesInitializer;
import net.sf.anathema.character.presenter.initializers.AdvantagesInitializer;
import net.sf.anathema.character.presenter.initializers.AttributesInitializer;
import net.sf.anathema.character.presenter.initializers.CharmInitializer;
import net.sf.anathema.character.presenter.initializers.ComboInitializer;
import net.sf.anathema.character.presenter.initializers.ConceptInitializer;
import net.sf.anathema.character.presenter.initializers.DescriptionInitializer;
import net.sf.anathema.character.presenter.initializers.NecromancyInitializer;
import net.sf.anathema.character.presenter.initializers.SorceryInitializer;
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

  public CharacterPresenter(ICharacter character, CharacterView view, Resources resources, IApplicationModel applicationModel,
                            PointPresentationStrategy pointPresentation) {
    this.character = character;
    this.characterView = view;
    this.resources = resources;
    this.applicationModel = applicationModel;
    this.pointPresentation = pointPresentation;
  }

  @Override
  public void initPresentation() {
    initOutline();
    initPhysicalTraits();
    initSpiritualTraits();
    initMagic();
    initMiscellaneous();
  }

  private void initOutline() {
    SectionView sectionView = addSection("CardView.Outline.Title");
    new DescriptionInitializer().initialize(sectionView, character, resources);
    new ConceptInitializer().initialize(sectionView, character, resources);
    initializeAdditionalModels(sectionView, Outline);
  }

  private void initPhysicalTraits() {
    SectionView sectionView = addSection("CardView.NaturalTraits.Title");
    new AttributesInitializer().initialize(sectionView, character, resources);
    new AbilitiesInitializer().initialize(sectionView, character, resources);
    initializeAdditionalModels(sectionView, NaturalTraits);
  }

  private void initSpiritualTraits() {
    SectionView sectionView = addSection("CardView.SpiritualTraits.Title");
    new AdvantagesInitializer().initialize(sectionView, character, resources);
    initializeAdditionalModels(sectionView, SpiritualTraits);
  }

  private void initMagic() {
    SectionView sectionView = addSection("CardView.CharmConfiguration.Title");
    new CharmInitializer(applicationModel).initialize(sectionView, character, resources);
    new ComboInitializer(applicationModel).initialize(sectionView, character, resources);
    new SorceryInitializer(applicationModel).initialize(sectionView, character, resources);
    new NecromancyInitializer(applicationModel).initialize(sectionView, character, resources);
    initializeAdditionalModels(sectionView, Magic);
  }

  private void initMiscellaneous() {
    SectionView sectionView = addSection("CardView.MiscellaneousConfiguration.Title");
    initializeAdditionalModels(sectionView, Miscellaneous);
    pointPresentation.initPresentation(sectionView);
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
      String viewName = getString("AdditionalTemplateView.TabName." + model.getTemplateId());
      Object view = sectionView.addView(viewName, initializer.getViewClass(), characterType());
      initializer.initialize(model, resources, characterType(), view);
    }
  }

  private String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }

  private ICharacterType characterType() {
    return character.getCharacterTemplate().getTemplateType().getCharacterType();
  }
}