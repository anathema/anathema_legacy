package net.sf.anathema.character.presenter.magic;

import com.google.common.collect.Iterables;
import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.magic.view.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.magic.charm.CharacterCharmTreePresenter;
import net.sf.anathema.character.presenter.magic.combo.ComboConfigurationModel;
import net.sf.anathema.character.presenter.magic.combo.ComboConfigurationPresenter;
import net.sf.anathema.character.presenter.magic.detail.MagicAndDetailPresenter;
import net.sf.anathema.character.presenter.magic.detail.MagicDetailPresenter;
import net.sf.anathema.character.presenter.magic.detail.MagicDetailPresenterFactory;
import net.sf.anathema.character.presenter.magic.detail.NullMagicDetailPresenter;
import net.sf.anathema.character.presenter.magic.detail.RegisteredMagicDetailPresenterFactory;
import net.sf.anathema.character.presenter.magic.spells.SpellContentPresenter;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.charmtree.presenter.view.CharmDisplayPropertiesMap;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.presenter.view.ContentView;
import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MagicPresenter implements IContentPresenter {

  private final Logger logger = Logger.getLogger(MagicPresenter.class);
  private final List<IContentPresenter> subPresenters = new ArrayList<>();
  private IAnathemaModel anathemaModel;
  private IResources resources;

  public MagicPresenter(ICharacter character, IMagicViewFactory factory, IResources resources, IAnathemaModel anathemaModel) {
    this.resources = resources;
    ITemplateRegistry templateRegistry = CharacterGenericsExtractor.getGenerics(anathemaModel).getTemplateRegistry();
    this.anathemaModel = anathemaModel;
    ICharacterTemplate characterTemplate = character.getCharacterTemplate();
    ICharmTemplate charmTemplate = characterTemplate.getMagicTemplate().getCharmTemplate();
    if (charmTemplate.canLearnCharms()) {
      subPresenters.add(createCharmPresenter(character, factory, resources, templateRegistry));
      subPresenters.add(new ComboConfigurationPresenter(resources, createComboModel(character), factory));
    }
    addSpellPresenter(character, factory, resources);
  }

  private void addSpellPresenter(ICharacter character, IMagicViewFactory factory, IResources resources) {
    ISpellMagicTemplate spellMagic = character.getCharacterTemplate().getMagicTemplate().getSpellMagic();
    if (spellMagic.canLearnSorcery()) {
      subPresenters.add(SpellContentPresenter.ForSorcery(createMagicDetailPresenter(), character, resources, factory, getMagicDescriptionProvider()));
    }
    if (spellMagic.canLearnNecromancy()) {
      subPresenters
              .add(SpellContentPresenter.ForNecromancy(createMagicDetailPresenter(), character, resources, factory, getMagicDescriptionProvider()));
    }
  }

  private ComboConfigurationModel createComboModel(ICharacter character) {
    return new ComboConfigurationModel(character, getMagicDescriptionProvider());
  }

  private MagicDescriptionProvider getMagicDescriptionProvider() {
    return CharmDescriptionProviderExtractor.CreateFor(anathemaModel, resources);
  }

  private MagicAndDetailPresenter createCharmPresenter(ICharacter character, IMagicViewFactory factory, IResources resources,
                                                       ITemplateRegistry templateRegistry) {
    CharacterCharmModel model = new CharacterCharmModel(character, getMagicDescriptionProvider());
    ITreePresentationProperties presentationProperties =
            character.getCharacterTemplate().getPresentationProperties().getCharmPresentationProperties();
    CharmDisplayPropertiesMap propertiesMap = new CharmDisplayPropertiesMap(templateRegistry);
    CharacterCharmTreePresenter treePresenter = new CharacterCharmTreePresenter(resources, factory, model, presentationProperties, propertiesMap);
    MagicDetailPresenter detailPresenter = createMagicDetailPresenter();
    String tabTitle = resources.getString("CardView.CharmConfiguration.CharmSelection.Title");
    return new MagicAndDetailPresenter(tabTitle, detailPresenter, treePresenter);
  }

  private MagicDetailPresenter createMagicDetailPresenter() {
    try {
      Instantiater instantiater = getGenerics().getInstantiater();
      Collection<MagicDetailPresenterFactory> factories = instantiater.instantiateAll(RegisteredMagicDetailPresenterFactory.class);
      if (!factories.isEmpty()) {
        return Iterables.get(factories, 0).create(anathemaModel, resources);
      }
    } catch (Throwable e) {
      logger.error("Error initializing charm details.", e);
    }
    return new NullMagicDetailPresenter();
  }

  private ICharacterGenerics getGenerics() {
    return CharacterGenericsExtractor.getGenerics(anathemaModel);
  }

  @Override
  public void initPresentation() {
    for (IContentPresenter presenter : subPresenters) {
      presenter.initPresentation();
    }
  }

  @Override
  public ContentView getTabContent() {
    return new ContentView() {
      @Override
      public void addTo(MultipleContentView view) {
        for (IContentPresenter presenter : subPresenters) {
          presenter.getTabContent().addTo(view);
        }
      }
    };
  }
}
