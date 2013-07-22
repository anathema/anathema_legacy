package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.hero.charms.display.model.CharacterCharmTypes;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.util.Identifier;

public class CharacterAlienCharmPresenter implements AlienCharmPresenter {

  private CharmDisplayModel model;

  public CharacterAlienCharmPresenter(CharmDisplayModel model) {
    this.model = model;
  }

  @Override
  public void initPresentation(final ObjectSelectionView<Identifier> typeSelector) {
    model.addCasteChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        boolean alienCharms = model.isAllowedAlienCharms();
        CharmsModel charmConfiguration = model.getCharmModel();
        if (!alienCharms) {
          charmConfiguration.unlearnAllAlienCharms();
        }
        Identifier[] currentCharmTypes = new CharacterCharmTypes(model).getCurrentCharmTypes();
        typeSelector.setObjects(currentCharmTypes);
      }
    });
  }
}
