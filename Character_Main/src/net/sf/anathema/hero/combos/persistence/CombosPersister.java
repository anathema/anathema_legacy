package net.sf.anathema.hero.combos.persistence;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.combos.ICombo;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.combos.model.CombosModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.util.Identifier;

//@HeroModelPersisterCollected
public class CombosPersister extends AbstractModelJsonPersister<ComboListPto, CombosModel> {

  public CombosPersister() {
    super("combos", ComboListPto.class);
  }

  @Override
  public Identifier getModelId() {
    return CombosModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, CombosModel model, ComboListPto pto) {
    CharmsModel charms = CharmsModelFetcher.fetch(hero);
    for (ComboPto comboPto : pto.combos) {
      loadCombo(charms, model, comboPto);
    }
  }

  private void loadCombo(CharmsModel charms, CombosModel model, ComboPto comboPto) {
    ICombo combo = model.getEditCombo();
    combo.getName().setText(comboPto.name);
    combo.getDescription().setText(comboPto.description);
    loadCharms(charms, model, comboPto);
    model.finalizeCombo();
  }

  private void loadCharms(CharmsModel charms, CombosModel model, ComboPto comboPto) {
    for (ComboCharmPto charm : comboPto.charms) {
      try {
        Charm comboCharm = charms.getCharmById(charm.charmId);
        model.addCharmToCombo(comboCharm, charm.experienceLearned);
      } catch (IllegalArgumentException e) {
        messaging.addMessage("CharmPersistence.NoCharmFound", MessageType.WARNING, charm.charmId);
      }
    }
  }

  @Override
  protected ComboListPto saveModelToPto(CombosModel model) {
    ComboListPto pto = new ComboListPto();
    for (ICombo combo : model.getAllCombos()) {
      ComboPto comboPto = new ComboPto();
      saveCombo(combo, comboPto);
      pto.combos.add(comboPto);
    }
    return pto;
  }

  private void saveCombo(ICombo combo, ComboPto pto) {
    pto.name = combo.getName().getText();
    pto.description = combo.getDescription().getText();
    saveCharms(pto, combo.getCreationCharms(), false);
    saveCharms(pto, combo.getExperiencedCharms(), true);
  }

  private void saveCharms(ComboPto pto, Charm[] charms, boolean experiencedLearned) {
    for (Charm charm : charms) {
      ComboCharmPto charmPto = new ComboCharmPto();
      charmPto.charmId = charm.getId();
      charmPto.type = charm.getCharacterType().getId();
      charmPto.experienceLearned = experiencedLearned;
      pto.charms.add(charmPto);
    }
  }
}
