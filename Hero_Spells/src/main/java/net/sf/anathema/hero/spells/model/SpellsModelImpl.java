package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.character.main.magic.model.spells.ISpellMapper;
import net.sf.anathema.character.main.magic.model.spells.Spell;
import net.sf.anathema.character.main.magic.model.spells.SpellMapper;
import net.sf.anathema.character.main.magic.parser.spells.ISpellCache;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.main.template.magic.ISpellMagicTemplate;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.experience.ExperienceChange;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.change.ChangeFlavor;
import net.sf.anathema.hero.model.change.FlavoredChangeListener;
import net.sf.anathema.hero.model.change.UnspecifiedChangeListener;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.spells.advance.SpellExperienceCostCalculator;
import net.sf.anathema.hero.spells.advance.SpellExperienceModel;
import net.sf.anathema.hero.spells.sheet.content.PrintSpellsProvider;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import org.apache.commons.lang3.ArrayUtils;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellsModelImpl implements SpellsModel {

  private final ProxySpellLearnStrategy strategy = new ProxySpellLearnStrategy(new CreationSpellLearnStrategy());
  private final List<Spell> creationLearnedList = new ArrayList<>();
  private final List<Spell> experiencedLearnedList = new ArrayList<>();
  private final Announcer<ChangeListener> changeControl = Announcer.to(ChangeListener.class);
  private final Map<CircleType, List<Spell>> spellsByCircle = new HashMap<>();
  private final ISpellMapper spellMapper = new SpellMapper();
  private CharmsModel charms;
  private HeroTemplate heroTemplate;
  private ExperienceModel experience;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.charms = CharmsModelFetcher.fetch(hero);
    this.experience = ExperienceModelFetcher.fetch(hero);
    this.heroTemplate = hero.getTemplate();
    initializeSpellsByCircle(environment);
    initializeCharmsModel(hero);
    initializeExperience(hero);
  }

  private void initializeExperience(Hero hero) {
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    IExperiencePointCosts experienceCost = hero.getTemplate().getExperienceCost();
    SpellExperienceCostCalculator calculator = new SpellExperienceCostCalculator(experienceCost);
    pointsModel.addToExperienceOverview(new SpellExperienceModel(hero, calculator));
  }

  private void initializeSpellsByCircle(HeroEnvironment environment) {
    for (CircleType type : CircleType.values()) {
      spellsByCircle.put(type, new ArrayList<Spell>());
    }
    for (Spell spell : environment.getDataSet(ISpellCache.class).getSpells()) {
      spellsByCircle.get(spell.getCircleType()).add(spell);
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
    announcer.addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
          boolean experienced = experience.isExperienced();
          updateLearnStrategies(experienced);
        }
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

  @Override
  public boolean isSpellAllowed(Spell spell, boolean experienced) {
    if (creationLearnedList.contains(spell) || (experienced && experiencedLearnedList.contains(spell))) {
      return false;
    }
    ISpellMagicTemplate template = heroTemplate.getMagicTemplate().getSpellMagic();
    return template.canLearnSpell(spell, charms.getLearnedCharms(true));
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

  @Override
  public Spell[] getSpellsByCircle(CircleType circle) {
    List<Spell> spells = spellsByCircle.get(circle);
    if (spells != null) {
      return spells.toArray(new Spell[spells.size()]);
    }
    return new Spell[0];
  }

  @Override
  public Spell getSpellById(String id) {
    String correctedId = spellMapper.getId(id);
    for (Spell spell : getAllSpells()) {
      if (spell.getId().equals(correctedId)) {
        return spell;
      }
    }
    throw new IllegalArgumentException("No Spell for id: " + id);
  }

  private Iterable<Spell> getAllSpells() {
    List<Spell> allSpells = new ArrayList<>();
    for (List<Spell> circleSpells : spellsByCircle.values()) {
      allSpells.addAll(circleSpells);
    }
    return allSpells;
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
  public boolean isLearned(Spell spell) {
    return strategy.isLearned(this, spell);
  }

  @Override
  public List<Spell> getAvailableSpellsInCircle(CircleType circle) {
    List<Spell> showSpells = new ArrayList<>();
    Collections.addAll(showSpells, getSpellsByCircle(circle));
    showSpells.removeAll(Arrays.asList(getLearnedSpells()));
    return showSpells;
  }

  @Override
  public List<Spell> getLearnedSpellsInCircles(CircleType[] eligibleCircles) {
    List<Spell> spellList = new ArrayList<>();
    for (Spell spell : getLearnedSpells()) {
      if (ArrayUtils.contains(eligibleCircles, spell.getCircleType())) {
        spellList.add(spell);
      }
    }
    return spellList;
  }

}