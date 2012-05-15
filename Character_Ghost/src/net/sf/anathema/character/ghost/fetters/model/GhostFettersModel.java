package net.sf.anathema.character.ghost.fetters.model;

import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.ghost.fetters.GhostFettersTemplate;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.lang.StringUtilities;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public class GhostFettersModel extends AbstractAdditionalModelAdapter implements IGhostFettersModel {
  private final List<Fetter> fetters;
  IIntValueChangedListener listener;
  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private final ICharacterModelContext context;
  private final GhostFettersTemplate template;
  private String currentName;

  private final int[] baseFreeFetterDotsPerAge = {5, 4, 4, 3, 3, 2};

  private static final int FETTER_XP_MULTIPLIER = 3;
  private static final int FETTER_BONUS_POINT_COST = 3;
  private final int maxFreeRating;

  @Override
  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Miscellaneous;
  }

  public GhostFettersModel(GhostFettersTemplate template, ICharacterModelContext context) {
    this.context = context;
    this.template = template;
    fetters = new ArrayList<Fetter>();

    maxFreeRating = 3;
  }

  @Override
  public String getTemplateId() {
    return template.getId();
  }

  @Override
  public void setCurrentFetterName(String newFetterName) {
    this.currentName = newFetterName;
    control.announce().changeOccurred();
  }

  private int getAge() {
    IGenericTrait ageTrait = context.getTraitCollection().getTrait(new CustomizedBackgroundTemplate("Age"));
    return ageTrait == null ? 0 : ageTrait.getCurrentValue();
  }

  @Override
  public int getMaxFetterDots() {
    IGenericTrait essence = context.getTraitCollection().getTrait(OtherTraitType.Essence);
    IGenericTrait will = context.getTraitCollection().getTrait(OtherTraitType.Willpower);
    return essence.getCurrentValue() + will.getCurrentValue() - getAge();
  }

  @Override
  public int getCurrentFetterDots() {
    int total = 0;
    for (Fetter fetter : fetters)
      total += fetter.getCurrentValue();

    return total;
  }

  @Override
  public int getFreeDotAllotment() {
    return baseFreeFetterDotsPerAge[getAge()];
  }

  @Override
  public int getFreeDotsSpent() {
    int spent = 0;
    for (Fetter fetter : fetters)
      spent += Math.min(maxFreeRating, fetter.getCreationValue());
    return Math.min(spent, getFreeDotAllotment());
  }

  @Override
  public int getBonusPointsSpent() {
    int freeDotsRemaining = getFreeDotAllotment();
    int bonusPointDots = 0;
    for (Fetter fetter : fetters) {
      int dots = fetter.getCreationValue();
      int freeDotsSpent = Math.min(freeDotsRemaining, Math.min(fetter.getCreationValue(), maxFreeRating));
      freeDotsRemaining -= freeDotsSpent;
      dots -= freeDotsSpent;
      bonusPointDots += dots;
    }
    return FETTER_BONUS_POINT_COST * bonusPointDots;
  }

  @Override
  public int getXPSpent() {
    int xpMult = 0;
    for (Fetter fetter : fetters)
      xpMult += getXPMultiplier(fetter);
    return FETTER_XP_MULTIPLIER * xpMult;
  }

  @Override
  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new FetterModelBonusPointCalculator(this);
  }

  @Override
  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new FetterModelExperiencePointCalculator(this);
  }

  private int getXPMultiplier(Fetter fetter) {
    int total = 1;
    for (int i = fetter.getCreationValue(); i < fetter.getExperiencedValue(); i++)
      total *= i;
    return total == 1 ? 0 : total;
  }

  @Override
  public Fetter[] getFetters() {
    Fetter[] fetterSet = new Fetter[fetters.size()];
    fetters.toArray(fetterSet);
    return fetterSet;
  }

  @Override
  public Fetter commitSelection() {
    Fetter newFetter = new Fetter(currentName, context.getTraitContext(), this);
    fetters.add(newFetter);
    if (listener != null) newFetter.addCurrentValueListener(listener);
    newFetter.addCurrentValueListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        control.announce().changeOccurred();
      }
    });
    newFetter.setCurrentValue(1);
    return newFetter;
  }

  @Override
  public void removeFetter(Fetter fetter) {
    fetters.remove(fetter);
  }

  @Override
  public void clear() {
    currentName = null;
    control.announce().changeOccurred();
  }

  @Override
  public boolean isEntryComplete() {
    return !StringUtilities.isNullOrEmpty(currentName);
  }

  @Override
  public boolean isExperienced() {
    return context.getBasicCharacterContext().isExperienced();
  }

  @Override
  public void setValueChangedListener(IIntValueChangedListener listener) {
    this.listener = listener;
    for (Fetter fetter : fetters)
      fetter.addCurrentValueListener(listener);
  }

  @Override
  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }

  @Override
  public void addSelectionChangeListener(IChangeListener listener) {
    control.addListener(listener);
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    control.addListener(listener);

  }
}