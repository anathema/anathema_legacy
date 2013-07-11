package net.sf.anathema.hero.spells.display;

import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.model.spells.CircleType;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.hero.experience.ExperienceModel;
import net.sf.anathema.hero.magic.display.MagicViewListener;
import net.sf.anathema.hero.spells.SpellModel;
import net.sf.anathema.hero.spells.model.CircleModel;
import net.sf.anathema.lib.compare.I18nedIdentificateSorter;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class SpellPresenter {

  private final net.sf.anathema.hero.spells.SpellModel spellConfiguration;
  private final SpellViewProperties properties;
  private final CircleModel circleModel;
  private final Resources resources;
  private final SpellView view;

  public SpellPresenter(CircleModel circleModel, Resources resources, SpellView view,
                        MagicDescriptionProvider magicDescriptionProvider, ExperienceModel experienceModel, SpellModel spellModel) {
    this.circleModel = circleModel;
    this.spellConfiguration = spellModel;
    this.properties = new SpellViewProperties(resources, magicDescriptionProvider, spellConfiguration, experienceModel);
    this.resources = resources;
    this.view = view;
  }

  public void initPresentation() {
    Identifier[] allowedCircles = circleModel.getCircles();
    view.initGui(allowedCircles, properties);
    view.addMagicViewListener(new MagicViewListener() {
      @Override
      public void magicRemoved(Object[] removedSpells) {
        List<ISpell> spellList = convertToList(removedSpells);
        spellConfiguration.removeSpells(spellList);
      }

      @Override
      public void magicAdded(Object[] addedSpells) {
        List<ISpell> spellList = convertToList(addedSpells);
        spellConfiguration.addSpells(spellList);
      }
    });
    view.addCircleSelectionListener(new ObjectValueListener<CircleType>() {
      @Override
      public void valueChanged(CircleType circleType) {
        circleModel.selectCircle(circleType);
      }
    });
    circleModel.addSelectionListener(new ObjectValueListener<CircleType>() {
      @Override
      public void valueChanged(CircleType newValue) {
        showAvailableSpells(view);
      }
    });
    spellConfiguration.addChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        refreshSpellListsInView(view);
      }
    });
    refreshSpellListsInView(view);
  }

  private List<ISpell> convertToList(Object[] addedSpells) {
    List<ISpell> spellList = new ArrayList<>();
    for (Object spellObject : addedSpells) {
      spellList.add((ISpell) spellObject);
    }
    return spellList;
  }

  private void refreshSpellListsInView(SpellView spellView) {
    showLearnedSpells(spellView);
    showAvailableSpells(spellView);
  }

  private void showAvailableSpells(SpellView spellView) {
    List<ISpell> availableSpells = spellConfiguration.getAvailableSpellsInCircle(circleModel.getSelectedCircle());
    List<ISpell> sortedSpells = new I18nedIdentificateSorter<ISpell>().sortAscending(availableSpells, resources);
    spellView.setAvailableMagic(sortedSpells);
  }

  private void showLearnedSpells(SpellView spellView) {
    List<ISpell> learnedSpells = spellConfiguration.getLearnedSpellsInCircles(circleModel.getCircles());
    spellView.setLearnedMagic(learnedSpells);
  }
}