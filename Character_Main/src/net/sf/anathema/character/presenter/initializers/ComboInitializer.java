package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.generic.framework.magic.view.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.magic.combo.ComboConfigurationModel;
import net.sf.anathema.character.presenter.magic.combo.ComboConfigurationPresenter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.character.view.magic.IComboConfigurationView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.model.CharacterModelGroup.Magic;

@RegisteredInitializer(Magic)
@Weight(weight = 100)
public class ComboInitializer implements CharacterModelInitializer {

  private IApplicationModel model;

  public ComboInitializer(IApplicationModel model) {
    this.model = model;
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    boolean canLearnCharms = character.getCharacterTemplate().getMagicTemplate().getCharmTemplate().canLearnCharms();
    if (!canLearnCharms) {
      return;
    }
    String header = resources.getString("CardView.CharmConfiguration.ComboCreation.Title");
    IComboConfigurationView comboView = sectionView.addView(header, IComboConfigurationView.class, character.getCharacterType());
    MagicDescriptionProvider magicDescriptionProvider = CharmDescriptionProviderExtractor.CreateFor(model, resources);
    ComboConfigurationModel comboModel = new ComboConfigurationModel(character, magicDescriptionProvider);
    new ComboConfigurationPresenter(resources, comboModel, comboView).initPresentation();

  }
}
