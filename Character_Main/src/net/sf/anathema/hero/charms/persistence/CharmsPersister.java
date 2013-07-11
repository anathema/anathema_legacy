package net.sf.anathema.hero.charms.persistence;

import net.sf.anathema.character.main.magic.model.combos.ICombo;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.combos.persistence.ComboPto;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.persistence.HeroModelPersisterCollected;
import net.sf.anathema.lib.util.Identifier;

//@HeroModelPersisterCollected
public class CharmsPersister extends AbstractModelJsonPersister<CharmListPto, CharmsModel> {

  public CharmsPersister() {
    super("charms", CharmListPto.class);
  }

  @Override
  public Identifier getModelId() {
    return CharmsModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, CharmsModel model, CharmListPto pto) {
    CharmsModel charms = CharmsModelFetcher.fetch(hero);
//    for (ComboPto comboPto : pto.combos) {
//      loadCombo(charms, model, comboPto);
//    }
  }

  @Override
  protected CharmListPto saveModelToPto(CharmsModel model) {
    CharmListPto pto = new CharmListPto();
//    for (ICombo combo : model.getAllCombos()) {
//      ComboPto comboPto = new ComboPto();
//      saveCombo(combo, comboPto);
//      pto.combos.add(comboPto);
//    }
    return pto;
  }
}
