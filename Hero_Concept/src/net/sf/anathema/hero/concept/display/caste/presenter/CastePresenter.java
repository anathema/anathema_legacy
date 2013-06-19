package net.sf.anathema.hero.concept.display.caste.presenter;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.main.model.concept.HeroConceptFetcher;
import net.sf.anathema.character.main.model.experience.ExperienceModel;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.presenter.CasteSelectObjectUi;
import net.sf.anathema.hero.concept.display.caste.view.CasteView;
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
    HeroTemplate template = character.getTemplate();
    String casteLabelResourceKey = template.getPresentationProperties().getCasteLabelResource();
    ObjectUi<Object> casteUi = new CasteSelectObjectUi(resources, template.getPresentationProperties());
    CasteType[] casteTypes = template.getCasteCollection().getAllCasteTypes(character.getTemplate().getTemplateType());
    ListCellRenderer renderer = new ObjectUiListCellRenderer(casteUi);
    final IObjectSelectionView<CasteType> casteView =
            view.addObjectSelectionView(resources.getString(casteLabelResourceKey), casteTypes, renderer, false);
    final ITypedDescription<CasteType> caste = HeroConceptFetcher.fetch(character).getCaste();
    casteView.setSelectedObject(caste.getType());
    casteView.addObjectSelectionChangedListener(new ObjectValueListener<CasteType>() {
      @Override
      public void valueChanged(CasteType newValue) {
        caste.setType(newValue);
      }
    });
    caste.addChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        casteView.setSelectedObject(caste.getType());
      }
    });
    initExperienceListening(casteView);
  }

  private void initExperienceListening(final IObjectSelectionView<CasteType> casteView) {
    final ExperienceModel experienceModel = ExperienceModelFetcher.fetch(character);
    experienceModel.addStateChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        casteView.setEnabled(!experienceModel.isExperienced());
      }
    });
    casteView.setEnabled(!experienceModel.isExperienced());
  }
}