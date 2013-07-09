package net.sf.anathema.hero.magic.display.tree;

import net.sf.anathema.character.main.magic.display.view.charmtree.CharmView;
import net.sf.anathema.hero.magic.model.CharacterCharmModel;
import net.sf.anathema.hero.magic.model.charms.CharacterCharmTypes;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;

public class CharacterAlienCharmPresenter implements AlienCharmPresenter {

  private CharmView view;
  private CharacterCharmModel model;

  public CharacterAlienCharmPresenter(CharacterCharmModel model, CharmView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    model.addCasteChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        boolean alienCharms = model.isAllowedAlienCharms();
        CharmsModel charmConfiguration = model.getCharmConfiguration();
        if (!alienCharms) {
          charmConfiguration.unlearnAllAlienCharms();
        }
        Identifier[] currentCharmTypes = new CharacterCharmTypes(model).getCurrentCharmTypes();
        view.fillCharmTypeBox(currentCharmTypes);
      }
    });
  }
}
