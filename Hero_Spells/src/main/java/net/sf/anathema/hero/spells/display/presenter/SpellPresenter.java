package net.sf.anathema.hero.spells.display.presenter;

import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.character.main.magic.model.spells.Spell;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.magic.display.MagicLearnPresenter;
import net.sf.anathema.hero.magic.display.MagicLearnView;
import net.sf.anathema.hero.magic.display.MagicViewListener;
import net.sf.anathema.hero.spells.model.CircleModel;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.lib.compare.I18nedIdentificateSorter;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class SpellPresenter {

  private final SpellsModel spellConfiguration;
  private final CombinedSpellAndMagicProperties properties;
  private final CircleModel circleModel;
  private final Resources resources;
  private final SpellView view;

  public SpellPresenter(CircleModel circleModel, Resources resources, SpellView view,
                        MagicDescriptionProvider magicDescriptionProvider, ExperienceModel experienceModel, SpellsModel spellsModel) {
    this.circleModel = circleModel;
    this.spellConfiguration = spellsModel;
    this.properties = new CombinedSpellAndMagicProperties(resources, magicDescriptionProvider, spellConfiguration, experienceModel);
    this.resources = resources;
    this.view = view;
  }

  public void initPresentation() {
    view.addCircleSelection(circleModel.getCircles(), properties);
    addMagicLearnView(view);
    view.addCircleSelectionListener(new ObjectValueListener<CircleType>() {
      @Override
      public void valueChanged(CircleType circleType) {
        circleModel.selectCircle(circleType);
      }
    });
  }

  private void addMagicLearnView(final SpellView view) {
    final MagicLearnView magicLearnView = view.addMagicLearnView(properties);
    MagicLearnPresenter learnPresenter = new MagicLearnPresenter(magicLearnView);
    learnPresenter.initPresentation(properties);
    learnPresenter.addChangeListener(new MagicViewListener() {
      @Override
      public void removeMagicRequested(Object[] removedSpells) {
        List<Spell> spellList = convertToList(removedSpells);
        spellConfiguration.removeSpells(spellList);
      }

      @Override
      public void addMagicRequested(Object[] addedSpells) {
        List<Spell> spellList = convertToList(addedSpells);
        spellConfiguration.addSpells(spellList);
      }
    });
    circleModel.addSelectionListener(new ObjectValueListener<CircleType>() {
      @Override
      public void valueChanged(CircleType newValue) {
        showAvailableSpells(magicLearnView);
        updateCircleInView(newValue, view);
      }
    });
    spellConfiguration.addChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        refreshSpellListsInView(magicLearnView);
      }
    });
    refreshSpellListsInView(magicLearnView);
    updateCircleInView(circleModel.getSelectedCircle(), view);
  }

  private void updateCircleInView(CircleType newValue, SpellView view) {
    view.showSelectedCircle(newValue);
  }

  private List<Spell> convertToList(Object[] addedSpells) {
    List<Spell> spellList = new ArrayList<>();
    for (Object spellObject : addedSpells) {
      spellList.add((Spell) spellObject);
    }
    return spellList;
  }

  private void refreshSpellListsInView(MagicLearnView magicLearnView) {
    showLearnedSpells(magicLearnView);
    showAvailableSpells(magicLearnView);
  }

  private void showAvailableSpells(MagicLearnView magicLearnView) {
    List<Spell> availableSpells = spellConfiguration.getAvailableSpellsInCircle(circleModel.getSelectedCircle());
    List<Spell> sortedSpells = new I18nedIdentificateSorter<Spell>().sortAscending(availableSpells, resources);
    magicLearnView.setAvailableMagic(sortedSpells);
  }

  private void showLearnedSpells(MagicLearnView magicLearnView) {
    List<Spell> learnedSpells = spellConfiguration.getLearnedSpellsInCircles(circleModel.getCircles());
    magicLearnView.setLearnedMagic(learnedSpells);
  }
}