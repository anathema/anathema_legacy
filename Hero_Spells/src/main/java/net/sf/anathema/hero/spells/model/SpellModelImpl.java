package net.sf.anathema.hero.spells.model;

import net.sf.anathema.character.main.UnspecifiedChangeListener;
import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.character.main.magic.model.spells.ISpellMapper;
import net.sf.anathema.character.main.magic.model.spells.SpellMapper;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.magic.ISpellMagicTemplate;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.hero.charms.CharmsModelFetcher;
import net.sf.anathema.hero.experience.ExperienceChange;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.magic.model.MagicModel;
import net.sf.anathema.hero.magic.model.MagicModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.hero.spells.SpellModel;
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

public class SpellModelImpl implements SpellModel {

  private final ProxySpellLearnStrategy strategy = new ProxySpellLearnStrategy(new CreationSpellLearnStrategy());
  private final List<ISpell> creationLearnedList = new ArrayList<>();
  private final List<ISpell> experiencedLearnedList = new ArrayList<>();
  private final Announcer<ChangeListener> changeControl = Announcer.to(ChangeListener.class);
  private final Map<CircleType, List<ISpell>> spellsByCircle = new HashMap<>();
  private final ISpellMapper spellMapper = new SpellMapper();
  private CharmsModel charms;
  private HeroTemplate heroTemplate;
  private ExperienceModel experience;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    this.charms = CharmsModelFetcher.fetch(hero);
    this.experience = ExperienceModelFetcher.fetch(hero);
    this.heroTemplate = hero.getTemplate();
    for (CircleType type : CircleType.values()) {
      spellsByCircle.put(type, new ArrayList<ISpell>());
    }
    for (ISpell spell : context.getSpellCache().getSpells()) {
      spellsByCircle.get(spell.getCircleType()).add(spell);
    }
  }

  //TODO (Sandra) will investigate if we still need this
  @SuppressWarnings("UnusedDeclaration")
  private void addPrintSpells(Hero hero) {
    MagicModel magicModel = MagicModelFetcher.fetch(hero);
    if (magicModel == null) {
      return;
    }
    magicModel.addPrintMagicProvider(new PrintSpellsProvider(hero));
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
  public void removeSpells(List<ISpell> removedSpells) {
    strategy.removeSpells(this, removedSpells);
  }

  @Override
  public void removeSpells(List<ISpell> removedSpells, boolean experienced) {
    for (ISpell spell : removedSpells) {
      if (experienced) {
        experiencedLearnedList.remove(spell);
      } else {
        creationLearnedList.remove(spell);
      }
    }
    fireChangeEvent();
  }

  @Override
  public void addSpells(List<ISpell> addedSpells) {
    List<ISpell> allowedSpells = new ArrayList<>();
    for (ISpell spell : addedSpells) {
      if (isSpellAllowed(spell)) {
        allowedSpells.add(spell);
      }
    }
    strategy.addSpells(this, allowedSpells);
  }

  @Override
  public void addSpells(List<ISpell> addedSpells, boolean experienced) {
    for (ISpell spell : addedSpells) {
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
  public boolean isSpellAllowed(ISpell spell) {
    return strategy.isSpellAllowed(this, spell);
  }

  @Override
  public boolean isSpellAllowed(ISpell spell, boolean experienced) {
    if (creationLearnedList.contains(spell) || (experienced && experiencedLearnedList.contains(spell))) {
      return false;
    }
    ISpellMagicTemplate template = heroTemplate.getMagicTemplate().getSpellMagic();
    return template.canLearnSpell(spell, charms.getLearnedCharms(true));
  }

  @Override
  public ISpell[] getLearnedSpells() {
    return strategy.getLearnedSpells(this);
  }

  @Override
  public ISpell[] getLearnedSpells(boolean experienced) {
    List<ISpell> list = new ArrayList<>();
    list.addAll(creationLearnedList);
    if (experienced) {
      list.addAll(experiencedLearnedList);
    }
    return list.toArray(new ISpell[list.size()]);
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    changeControl.addListener(listener);
  }

  @Override
  public ISpell[] getSpellsByCircle(CircleType circle) {
    List<ISpell> spells = spellsByCircle.get(circle);
    if (spells != null) {
      return spells.toArray(new ISpell[spells.size()]);
    }
    return new ISpell[0];
  }

  @Override
  public ISpell getSpellById(String id) {
    String correctedId = spellMapper.getId(id);
    for (ISpell spell : getAllSpells()) {
      if (spell.getId().equals(correctedId)) {
        return spell;
      }
    }
    throw new IllegalArgumentException("No Spell for id: " + id);
  }

  private Iterable<ISpell> getAllSpells() {
    List<ISpell> allSpells = new ArrayList<>();
    for (List<ISpell> circleSpells : spellsByCircle.values()) {
      allSpells.addAll(circleSpells);
    }
    return allSpells;
  }

  @Override
  public boolean isLearnedOnCreation(ISpell spell) {
    return creationLearnedList.contains(spell);
  }

  @Override
  public boolean isLearnedOnCreationOrExperience(ISpell spell) {
    return experiencedLearnedList.contains(spell) || isLearnedOnCreation(spell);
  }

  @Override
  public boolean isLearned(ISpell spell) {
    return strategy.isLearned(this, spell);
  }

  @Override
  public List<ISpell> getAvailableSpellsInCircle(CircleType circle) {
    List<ISpell> showSpells = new ArrayList<>();
    Collections.addAll(showSpells, getSpellsByCircle(circle));
    showSpells.removeAll(Arrays.asList(getLearnedSpells()));
    return showSpells;
  }

  @Override
  public List<ISpell> getLearnedSpellsInCircles(CircleType[] eligibleCircles) {
    List<ISpell> spellList = new ArrayList<>();
    for (ISpell spell : getLearnedSpells()) {
      if (ArrayUtils.contains(eligibleCircles, spell.getCircleType())) {
        spellList.add(spell);
      }
    }
    return spellList;
  }
}