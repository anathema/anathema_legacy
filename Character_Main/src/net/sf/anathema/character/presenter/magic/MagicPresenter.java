package net.sf.anathema.character.presenter.magic;

import com.google.common.collect.Iterables;
import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.magic.view.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;
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
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.character.view.magic.IComboConfigurationView;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.charmtree.presenter.view.CharmDisplayPropertiesMap;
import net.sf.anathema.charmtree.presenter.view.ICharmView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

import java.util.Collection;

public class MagicPresenter {

  private final Logger logger = Logger.getLogger(MagicPresenter.class);
  private final IApplicationModel anathemaModel;
  private final ICharacter character;
  private final SectionView sectionView;
  private Resources resources;

  public MagicPresenter(ICharacter character, SectionView sectionView, Resources resources, IApplicationModel anathemaModel) {
    this.character = character;
    this.sectionView = sectionView;
    this.resources = resources;
    this.anathemaModel = anathemaModel;
  }

  public void initPresentation(){
    ICharacterTemplate characterTemplate = character.getCharacterTemplate();
    ITemplateRegistry templateRegistry = CharacterGenericsExtractor.getGenerics(anathemaModel).getTemplateRegistry();
    initCharms(templateRegistry);
    initCombos(characterTemplate);
    initSpells();
  }

  private void initCombos(ICharacterTemplate characterTemplate) {
    String header = resources.getString("CardView.CharmConfiguration.ComboCreation.Title");
    IComboConfigurationView comboView = sectionView.addView(header, IComboConfigurationView.class, characterType(characterTemplate));
    new ComboConfigurationPresenter(resources, createComboModel(character), comboView).initPresentation();
  }

  private ICharacterType characterType(ICharacterTemplate characterTemplate) {
    return characterTemplate.getTemplateType().getCharacterType();
  }

  private void initSpells() {
    ISpellMagicTemplate spellMagic = character.getCharacterTemplate().getMagicTemplate().getSpellMagic();
    if (spellMagic.canLearnSorcery()) {
      String header = resources.getString("CardView.CharmConfiguration.Spells.Title");
      ISpellView view = sectionView.addView(header, ISpellView.class, characterType(character.getCharacterTemplate()));
      SpellContentPresenter.ForSorcery(createMagicDetailPresenter(), character, resources, view, getMagicDescriptionProvider()).initPresentation();
    }
    if (spellMagic.canLearnNecromancy()) {
      String header = resources.getString("CardView.CharmConfiguration.Necromancy.Title");
      ISpellView view = sectionView.addView(header, ISpellView.class, characterType(character.getCharacterTemplate()));
      SpellContentPresenter.ForNecromancy(createMagicDetailPresenter(), character, resources, view, getMagicDescriptionProvider()).initPresentation();
    }
  }

  private ComboConfigurationModel createComboModel(ICharacter character) {
    return new ComboConfigurationModel(character, getMagicDescriptionProvider());
  }

  private MagicDescriptionProvider getMagicDescriptionProvider() {
    return CharmDescriptionProviderExtractor.CreateFor(anathemaModel, resources);
  }

  private void initCharms(ITemplateRegistry templateRegistry) {
    CharacterCharmModel model = new CharacterCharmModel(character, getMagicDescriptionProvider());
    ICharacterTemplate characterTemplate = character.getCharacterTemplate();
    ITreePresentationProperties presentationProperties =
            characterTemplate.getPresentationProperties().getCharmPresentationProperties();
    CharmDisplayPropertiesMap propertiesMap = new CharmDisplayPropertiesMap(templateRegistry);
    String header = resources.getString("CardView.CharmConfiguration.CharmSelection.Title");
    ICharmView charmView = sectionView.addView(header, ICharmView.class, characterType(characterTemplate));
    CharacterCharmTreePresenter treePresenter = new CharacterCharmTreePresenter(resources, charmView, model, presentationProperties, propertiesMap);
    MagicDetailPresenter detailPresenter = createMagicDetailPresenter();
    new MagicAndDetailPresenter(detailPresenter, treePresenter).initPresentation();
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
}