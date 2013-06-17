package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.main.concept.model.CharacterConceptFetcher;
import net.sf.anathema.character.main.experience.model.ExperienceModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.view.concept.CasteView;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.gui.ui.ObjectUiListCellRenderer;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.ListCellRenderer;

public class CastePresenter {

  private final CasteView view;
  private final ICharacter character;
  private final Resources resources;

  public CastePresenter(ICharacter character, CasteView view, Resources resources) {
    this.character = character;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    HeroTemplate template = character.getHeroTemplate();
    String casteLabelResourceKey = template.getPresentationProperties().getCasteLabelResource();
    ObjectUi<Object> casteUi = new CasteSelectObjectUi(resources, template.getPresentationProperties());
    ICasteType[] casteTypes = template.getCasteCollection().getAllCasteTypes(character.getHeroTemplate().getTemplateType());
    ListCellRenderer renderer = new ObjectUiListCellRenderer(casteUi);
    final IObjectSelectionView<ICasteType> casteView =
            view.addObjectSelectionView(resources.getString(casteLabelResourceKey), casteTypes, renderer, false);
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
    casteView.setEnabled(!ExperienceModelFetcher.fetch(character).isExperienced());
  }
}