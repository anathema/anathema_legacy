package net.sf.anathema.character.presenter.magic.spells;

import net.sf.anathema.character.generic.framework.magic.view.IMagicViewListener;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.main.model.spells.SpellsModelFetcher;
import net.sf.anathema.character.model.IMagicLearnListener;
import net.sf.anathema.character.view.magic.ISpellView;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.compare.I18nedIdentificateComparator;
import net.sf.anathema.lib.compare.I18nedIdentificateSorter;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SpellPresenter {

  private final net.sf.anathema.character.main.model.spells.SpellModel spellConfiguration;
  private net.sf.anathema.character.presenter.magic.spells.SpellModel spellModel;
  private final Hero hero;
  private final Resources resources;
  private CircleType circle;
  private final ISpellView view;

  public SpellPresenter(net.sf.anathema.character.presenter.magic.spells.SpellModel spellModel, Hero hero, Resources resources, ISpellView view,
                        MagicDescriptionProvider magicDescriptionProvider) {
    this.spellModel = spellModel;
    this.hero  = hero;
    SpellViewProperties properties = new SpellViewProperties(resources, hero, magicDescriptionProvider);
    this.resources = resources;
    this.spellConfiguration = SpellsModelFetcher.fetch(hero);
    this.view = view;
    view.prepare(properties);
    circle = spellModel.getCircles()[0];
  }

  public void initPresentation() {
    Identifier[] allowedCircles = spellModel.getCircles();
    view.initGui(allowedCircles);
    view.addMagicViewListener(new IMagicViewListener() {
      @Override
      public void magicRemoved(Object[] removedSpells) {
        List<ISpell> spellList = new ArrayList<>();
        for (Object spellObject : removedSpells) {
          spellList.add((ISpell) spellObject);
        }
        spellConfiguration.removeSpells(spellList.toArray(new ISpell[spellList.size()]));
      }

      @Override
      public void magicAdded(Object[] addedSpells) {
        List<ISpell> spellList = new ArrayList<>();
        for (Object spellObject : addedSpells) {
          if (spellConfiguration.isSpellAllowed((ISpell) spellObject)) {
            spellList.add((ISpell) spellObject);
          }
        }
        spellConfiguration.addSpells(spellList.toArray(new ISpell[spellList.size()]));
      }
    });
    view.addCircleSelectionListener(new ObjectValueListener<CircleType>() {
      @Override
      public void valueChanged(CircleType circleType) {
        circle = circleType;
        view.setMagicOptions(getSpellsToShow());
      }
    });
    spellConfiguration.addMagicLearnListener(new IMagicLearnListener<ISpell>() {
      @Override
      public void magicForgotten(ISpell[] magic) {
        forgetSpellListsInView(view, magic);
      }

      @Override
      public void magicLearned(ISpell[] magic) {
        learnSpellListsInView(view, magic);
      }
    });
    initSpellListsInView(view);
    ExperienceModelFetcher.fetch(hero).addStateChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        view.clearSelection();
      }
    });
   }

  private void initSpellListsInView(ISpellView spellView) {
    spellView.setLearnedMagic(getCircleFilteredSpellList(spellConfiguration.getLearnedSpells()));
    spellView.setMagicOptions(getSpellsToShow());
  }

  private void forgetSpellListsInView(ISpellView spellView, ISpell[] spells) {
    spellView.removeLearnedMagic(spells);
    ISpell[] supportedSpells = getSpellsOfCurrentCircle(spells);
    spellView.addMagicOptions(supportedSpells, new I18nedIdentificateComparator(resources));
  }

  private void learnSpellListsInView(ISpellView spellView, ISpell[] spells) {
    ISpell[] supportedSpells = getSpellsOfCurrentCircle(spells);
    spellView.addLearnedMagic(supportedSpells);
    spellView.removeMagicOptions(supportedSpells);
  }

  private ISpell[] getSpellsOfCurrentCircle(ISpell[] spells) {
    List<ISpell> supportedSpells = new ArrayList<>();
    for (ISpell spell : spells) {
      if (circle == spell.getCircleType()) {
        supportedSpells.add(spell);
      }
    }
    return supportedSpells.toArray(new ISpell[supportedSpells.size()]);
  }

  private ISpell[] getSpellsToShow() {
    List<ISpell> showSpells = new ArrayList<>();
    Collections.addAll(showSpells, spellConfiguration.getSpellsByCircle(circle));
    showSpells.removeAll(Arrays.asList(spellConfiguration.getLearnedSpells()));
    int count = showSpells.size();
    ISpell[] sortedSpells = new ISpell[count];
    sortedSpells = new I18nedIdentificateSorter<ISpell>().sortAscending(showSpells.toArray(new ISpell[count]),
            sortedSpells, resources);
    return sortedSpells;
  }

  private ISpell[] getCircleFilteredSpellList(ISpell[] spells) {
    List<ISpell> spellList = new ArrayList<>();
    for (ISpell spell : spells) {
      if (ArrayUtils.contains(spellModel.getCircles(), spell.getCircleType())) {
        spellList.add(spell);
      }
    }
    return spellList.toArray(new ISpell[spellList.size()]);
  }
}