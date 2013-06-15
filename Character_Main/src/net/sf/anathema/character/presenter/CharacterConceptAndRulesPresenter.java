package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.main.concept.model.CharacterConceptFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesViewProperties;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.gui.ui.ObjectUiListCellRenderer;
import net.sf.anathema.lib.resources.Resources;

public class CharacterConceptAndRulesPresenter {

  private final ICharacterConceptAndRulesView view;
  private final ICharacter character;
  private final Resources resources;

  public CharacterConceptAndRulesPresenter(ICharacter character, ICharacterConceptAndRulesView view, Resources resources) {
    this.character = character;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    initRulesPresentation();
    initCastePresentation();
    initGui();
  }

  private void initGui() {
    view.initGui(new ICharacterConceptAndRulesViewProperties() {
      @Override
      public String getConceptTitle() {
        return resources.getString("CardView.CharacterConcept.Concept");
      }

      @Override
      public String getRulesTitle() {
        return resources.getString("CardView.CharacterConcept.Rules");
      }
    });
  }

  private void initRulesPresentation() {
    String newActionResource = character.getCharacterTemplate().getPresentationProperties().getNewActionResource();
    view.addRulesLabel(resources.getString("Rules.CharacterType.Message", resources.getString(newActionResource)));
  }

  private boolean initCastePresentation() {
    ICharacterTemplate template = character.getCharacterTemplate();
    if (template.getCasteCollection().isEmpty()) {
      return false;
    }
    String casteLabelResourceKey = template.getPresentationProperties().getCasteLabelResource();
    ObjectUi<Object> casteUi = new CasteSelectObjectUi(resources, template.getPresentationProperties());
    ICasteType[] casteTypes = template.getCasteCollection().getAllCasteTypes(character.getCharacterTemplate().getTemplateType());
    final IObjectSelectionView<ICasteType> casteView =
            view.addObjectSelectionView(resources.getString(casteLabelResourceKey), casteTypes, new ObjectUiListCellRenderer(casteUi), false);
    final ITypedDescription<ICasteType> caste = CharacterConceptFetcher.fetch(character).getCaste();
    casteView.setSelectedObject(caste.getType());
    casteView.addObjectSelectionChangedListener(new ObjectValueListener<ICasteType>() {
      @Override
      public void valueChanged(ICasteType newValue) {
        caste.setType(newValue);
      }
    });
    caste.addChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        casteView.setSelectedObject(caste.getType());
      }
    });
    character.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        casteView.setEnabled(!experienced);
      }
    });
    casteView.setEnabled(!character.isExperienced());
    return true;
  }
}