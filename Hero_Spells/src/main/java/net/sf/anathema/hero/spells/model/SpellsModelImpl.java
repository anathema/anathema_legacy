package net.sf.anathema.hero.spells.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.sf.anathema.character.magic.parser.charms.TraitTypeFinder;
import net.sf.anathema.character.magic.parser.spells.ISpellCache;
import net.sf.anathema.character.magic.spells.CircleType;
import net.sf.anathema.character.magic.spells.Spell;
import net.sf.anathema.hero.charms.advance.MagicPointsModelFetcher;
import net.sf.anathema.hero.charms.advance.experience.MagicExperienceCosts;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.experience.ExperienceChange;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.change.UnspecifiedChangeListener;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.spells.advance.SpellExperienceCostCalculator;
import net.sf.anathema.hero.spells.advance.SpellExperienceModel;
import net.sf.anathema.hero.spells.sheet.content.PrintSpellsProvider;
import net.sf.anathema.hero.spells.template.SpellsTemplate;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SpellsModelImpl implements SpellsModel {

  private final ProxySpellLearnStrategy strategy = new ProxySpellLearnStrategy(new CreationSpellLearnStrategy());
  private final List<Spell> creationLearnedList = new ArrayList<>();
  private final List<Spell> experiencedLearnedList = new ArrayList<>();
  private final Announcer<ChangeListener> changeControl = Announcer.to(ChangeListener.class);
  private final Multimap<CircleType, Spell> spellsByCircle = ArrayListMultimap.create();
  private CharmsModel charms;
  private ExperienceModel experience;
  private SpellsTemplate template;

  public SpellsModelImpl(SpellsTemplate template) {
    this.template = template;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.charms = CharmsModelFetcher.fetch(hero);
    this.experience = ExperienceModelFetcher.fetch(hero);
    initializeSpellsByCircle(environment);
    initializeCharmsModel(hero);
    initializeExperience(hero);
  }

  private void initializeExperience(Hero hero) {
    MagicExperienceCosts experienceCost = MagicPointsModelFetcher.fetch(hero).getExperienceCost();
    SpellExperienceCostCalculator calculator = new SpellExperienceCostCalculator(experienceCost);
    PointModelFetcher.fetch(hero).addToExperienceOverview(new SpellExperienceModel(hero, calculator));
  }

  private void initializeSpellsByCircle(HeroEnvironment environment) {
    for (Spell spell : environment.getDataSet(ISpellCache.class).getSpells()) {
      spellsByCircle.put(spell.getCircleType(), spell);
    }
  }

  private void initializeCharmsModel(Hero hero) {
    CharmsModel charmsModel = CharmsModelFetcher.fetch(hero);
    if (charmsModel == null) {
      return;
    }
    charmsModel.addPrintProvider(new PrintSpellsProvider(hero));
    charmsModel.addLearnProvider(new SpellsLearner(this));
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    announcer.addListener(flavor -> {
      if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
        boolean experienced = experience.isExperienced();
        updateLearnStrategies(experienced);
      }
    });
    addChangeListener(new UnspecifiedChangeListener(announcer));
  }

  private void updateLearnStrategies(boolean experienced) {
    if (experienced) {
      strategy.setStrategy(new ExperiencedSpellLearnStrategy());
    } else {
      strategy.setStrategy(new CreationSpellLearnStrategy());
    }
  }

  @Override
  public void removeSpells(List<Spell> removedSpells) {
    strategy.removeSpells(this, removedSpells);
  }

  @Override
  public void removeSpells(List<Spell> removedSpells, boolean experienced) {
    for (Spell spell : removedSpells) {
      if (experienced) {
        experiencedLearnedList.remove(spell);
      } else {
        creationLearnedList.remove(spell);
      }
    }
    fireChangeEvent();
  }

  @Override
  public void addSpells(List<Spell> addedSpells) {
    List<Spell> allowedSpells = new ArrayList<>();
    for (Spell spell : addedSpells) {
      if (isSpellAllowed(spell)) {
        allowedSpells.add(spell);
      }
    }
    strategy.addSpells(this, allowedSpells);
  }

  @Override
  public void addSpells(List<Spell> addedSpells, boolean experienced) {
    for (Spell spell : addedSpells) {
      if (isSpellAllowed(spell, experienced)) {
        if (experienced) {
          experiencedLearnedList.add(spell);
        } else {
          creationLearnedList.add(spell);
        }
      } else {
        throw new IllegalArgumentException("Cannot learn Spell: " + spell);
      }
    }
    fireChangeEvent();
  }

  private void fireChangeEvent() {
    changeControl.announce().changeOccurred();
  }

  @Override
  public boolean isSpellAllowed(Spell spell) {
    return strategy.isSpellAllowed(this, spell);
  }

  @SuppressWarnings("SimplifiableIfStatement")
  @Override
  public boolean isSpellAllowed(Spell spell, boolean experienced) {
    boolean alreadyKnowsSpell = creationLearnedList.contains(spell) || (experienced && experiencedLearnedList.contains(
            spell));
    if (alreadyKnowsSpell) {
      return false;
    }
    String initiationCharm = getInitiation(spell.getCircleType());
    return charms.isLearned(initiationCharm);
  }

  @Override
  public Spell[] getLearnedSpells() {
    return strategy.getLearnedSpells(this);
  }

  @Override
  public Spell[] getLearnedSpells(boolean experienced) {
    List<Spell> list = new ArrayList<>();
    list.addAll(creationLearnedList);
    if (experienced) {
      list.addAll(experiencedLearnedList);
    }
    return list.toArray(new Spell[list.size()]);
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    changeControl.addListener(listener);
  }

  private Spell[] getSpellsByCircle(CircleType circle) {
    Collection<Spell> spells = spellsByCircle.get(circle);
    return spells.toArray(new Spell[spells.size()]);
  }

  @Override
  public Spell getSpellById(String id) {
    for (Spell spell : getAllSpells()) {
      if (spell.getId().equals(id)) {
        return spell;
      }
    }
    throw new IllegalArgumentException("No Spell for id: " + id);
  }

  private Iterable<Spell> getAllSpells() {
    return new ArrayList<>(spellsByCircle.values());
  }

  @Override
  public boolean isLearnedOnCreation(Spell spell) {
    return creationLearnedList.contains(spell);
  }

  @Override
  public boolean isLearnedOnCreationOrExperience(Spell spell) {
    return experiencedLearnedList.contains(spell) || isLearnedOnCreation(spell);
  }

  @Override
  public List<Spell> getAvailableSpellsInCircle(CircleType circle) {
    List<Spell> showSpells = new ArrayList<>();
    Collections.addAll(showSpells, getSpellsByCircle(circle));
    showSpells.removeAll(Arrays.asList(getLearnedSpells()));
    return showSpells;
  }

  @Override
  public List<Spell> getLearnedSpellsInCircles(Collection<CircleType> eligibleCircles) {
    List<Spell> spellList = new ArrayList<>();
    for (Spell spell : getLearnedSpells()) {
      if (eligibleCircles.contains(spell.getCircleType())) {
        spellList.add(spell);
      }
    }
    return spellList;
  }

  @Override
  public boolean canLearnSorcery() {
    return !template.sorcery.isEmpty();
  }

  @Override
  public boolean canLearnNecromancy() {
    return !template.necromancy.isEmpty();
  }

  @Override
  public Collection<CircleType> getNecromancyCircles() {
    return template.necromancy.keySet();
  }

  @Override
  public Collection<CircleType> getSorceryCircles() {
    return template.sorcery.keySet();
  }

  @Override
  public TraitType getFavoringTraitType() {
    return new TraitTypeFinder().getTrait(template.favoringTrait);
  }

  private String getInitiation(CircleType type) {
    String charmId = template.necromancy.get(type);
    if (charmId == null) {
      charmId = template.sorcery.get(type);
    }
    return charmId;
  }
}