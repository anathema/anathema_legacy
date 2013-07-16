package net.sf.anathema.hero.spells.persistence;

import com.google.common.base.Predicate;
import net.sf.anathema.character.main.magic.spells.Spell;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.persistence.AbstractModelJsonPersister;
import net.sf.anathema.hero.persistence.RegisteredHeroModelPersister;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Collections2.filter;

@RegisteredHeroModelPersister
public class SpellsPersister extends AbstractModelJsonPersister<SpellListPto, SpellsModel> {

  public SpellsPersister() {
    super("spells", SpellListPto.class);
  }

  @Override
  public Identifier getModelId() {
    return SpellsModel.ID;
  }

  @Override
  protected void loadModelFromPto(Hero hero, SpellsModel model, SpellListPto pto) {
    List<Spell> creationSpellList = collectSpells(model, pto, new CreationLearned());
    model.addSpells(creationSpellList, false);
    List<Spell> experienceSpellList = collectSpells(model, pto, new ExperienceLearned());
    model.addSpells(experienceSpellList, true);
  }

  private List<Spell> collectSpells(SpellsModel model, SpellListPto pto, Predicate<AttributedPto> predicate) {
    Collection<AttributedPto> creationLearned = filter(pto.spells, predicate);
    return collectSpells(model, creationLearned);
  }

  private List<Spell> collectSpells(SpellsModel model, Iterable<AttributedPto> matchingPtoList) {
    List<Spell> found = new ArrayList<>();
    for (AttributedPto spellPto : matchingPtoList) {
      Spell spell = model.getSpellById(spellPto.id);
      found.add(spell);
    }
    return found;
  }

  @Override
  protected SpellListPto saveModelToPto(SpellsModel model) {
    SpellListPto pto = new SpellListPto();
    for (Spell spell : model.getLearnedSpells()) {
      AttributedPto spellPto = createSpellPto(model, spell);
      pto.spells.add(spellPto);
    }
    return pto;
  }

  private AttributedPto createSpellPto(SpellsModel model, Spell spell) {
    AttributedPto spellPto = new AttributedPto();
    spellPto.id = spell.getId();
    spellPto.isExperienceLearned = !model.isLearnedOnCreation(spell);
    return spellPto;
  }
}
