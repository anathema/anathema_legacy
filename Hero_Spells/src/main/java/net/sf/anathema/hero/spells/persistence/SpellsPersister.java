package net.sf.anathema.hero.spells.persistence;

import com.google.common.base.Predicate;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.persistence.RegisteredHeroModelPersister;
import net.sf.anathema.hero.spells.model.SpellModel;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Collections2.filter;

@RegisteredHeroModelPersister
public class SpellsPersister extends AbstractModelJsonPersister<SpellListPto, SpellModel> {

  public SpellsPersister() {
    super("spells", SpellListPto.class);
  }

  @Override
  public Identifier getModelId() {
    return SpellModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, SpellModel model, SpellListPto pto) {
    List<ISpell> creationSpellList = collectSpells(model, pto, new CreationLearned());
    model.addSpells(creationSpellList, false);
    List<ISpell> experienceSpellList = collectSpells(model, pto, new ExperienceLearned());
    model.addSpells(experienceSpellList, true);
  }

  private List<ISpell> collectSpells(SpellModel model, SpellListPto pto, Predicate<AttributedPto> predicate) {
    Collection<AttributedPto> creationLearned = filter(pto.spells, predicate);
    return collectSpells(model, creationLearned);
  }

  private List<ISpell> collectSpells(SpellModel model, Iterable<AttributedPto> matchingPtoList) {
    List<ISpell> found = new ArrayList<>();
    for (AttributedPto spellPto : matchingPtoList) {
      ISpell spell = model.getSpellById(spellPto.id);
      found.add(spell);
    }
    return found;
  }

  @Override
  protected SpellListPto saveModelToPto(SpellModel model) {
    SpellListPto pto = new SpellListPto();
    for (ISpell spell : model.getLearnedSpells()) {
      AttributedPto spellPto = createSpellPto(model, spell);
      pto.spells.add(spellPto);
    }
    return pto;
  }

  private AttributedPto createSpellPto(SpellModel model, ISpell spell) {
    AttributedPto spellPto = new AttributedPto();
    spellPto.id = spell.getId();
    spellPto.isExperienceLearned = !model.isLearnedOnCreation(spell);
    return spellPto;
  }
}
