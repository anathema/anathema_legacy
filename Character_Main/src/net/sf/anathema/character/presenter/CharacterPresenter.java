package net.sf.anathema.character.presenter;

import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.initializers.CharacterModelInitializer;
import net.sf.anathema.character.presenter.initializers.Initializers;
import net.sf.anathema.character.view.CharacterView;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.model.CharacterModelGroup.Magic;
import static net.sf.anathema.character.model.CharacterModelGroup.Miscellaneous;
import static net.sf.anathema.character.model.CharacterModelGroup.NaturalTraits;
import static net.sf.anathema.character.model.CharacterModelGroup.Outline;
import static net.sf.anathema.character.model.CharacterModelGroup.SpiritualTraits;

public class CharacterPresenter implements Presenter {

  private final Initializers initializers;
  private final ICharacter character;
  private final CharacterView characterView;
  private final Resources resources;

  public CharacterPresenter(ICharacter character, CharacterView view, Resources resources, IApplicationModel applicationModel) {
    this.initializers = new Initializers(applicationModel);
    this.character = character;
    this.characterView = view;
    this.resources = resources;
  }

  @Override
  public void initPresentation() {
    initializeSection("CardView.Outline.Title", Outline);
    initializeSection("CardView.NaturalTraits.Title", NaturalTraits);
    initializeSection("CardView.SpiritualTraits.Title", SpiritualTraits);
    initializeSection("CardView.CharmConfiguration.Title", Magic);
    initializeSection("CardView.MiscellaneousConfiguration.Title", Miscellaneous);
  }

  private void initializeSection(String titleKey, CharacterModelGroup group) {
    SectionView sectionView = addSection(titleKey);
    initializeGroup(group, sectionView);
  }

  private SectionView addSection(String titleKey) {
    String sectionTitle = resources.getString(titleKey);
    return characterView.addSection(sectionTitle);
  }

  private void initializeGroup(CharacterModelGroup group, SectionView sectionView) {
    for (CharacterModelInitializer initializer : initializers.getInOrderFor(group)) {
      initializer.initialize(sectionView, character, resources);
    }
  }
}