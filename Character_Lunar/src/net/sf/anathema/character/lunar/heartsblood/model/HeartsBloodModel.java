package net.sf.anathema.character.lunar.heartsblood.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeListener;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.additionaltemplate.NullAdditionalModelExperienceCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodTemplate;
import net.sf.anathema.character.lunar.heartsblood.presenter.IAnimalForm;
import net.sf.anathema.character.lunar.heartsblood.presenter.IHeartsBloodModel;
import net.sf.anathema.character.lunar.heartsblood.presenter.IHeartsBloodModelListener;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;

public class HeartsBloodModel implements IHeartsBloodModel {

  private String currentName;
  private int currentStrength;
  private int currentStamina;
  private final GenericControl<IHeartsBloodModelListener> control = new GenericControl<IHeartsBloodModelListener>();
  private final List<IAnimalForm> forms = new ArrayList<IAnimalForm>();
  private final ICharacterModelContext context;

  public HeartsBloodModel(ICharacterModelContext context) {
    this.context = context;
  }
  
  public String getTemplateId() {
    return HeartsBloodTemplate.TEMPLATE_ID;
  }

  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Miscellaneous;
  }

  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new NullAdditionalModelBonusPointCalculator();
  }

  public void addBonusPointsChangeListener(ChangeListener listener) {
    //Nothing to do
  }

  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new NullAdditionalModelExperienceCalculator();
  }

  public void setCurrentName(String newValue) {
    this.currentName = newValue;
    control.forAllDo(new IClosure<IHeartsBloodModelListener>() {
      public void execute(IHeartsBloodModelListener input) {
        input.entryComplete(currentName != null);
      }
    });
  }

  public void setCurrentStrength(int newValue) {
    this.currentStrength = newValue;
  }

  public void setCurrentStamina(int newValue) {
    this.currentStamina = newValue;
  }

  public void commitSelection() {
    final IAnimalForm form = addAnimalForm(currentName, currentStrength, currentStamina);
    control.forAllDo(new IClosure<IHeartsBloodModelListener>() {
      public void execute(IHeartsBloodModelListener input) {
        input.fireSelectionAdded(form);
      }
    });
  }

  public void addModelChangeListener(IHeartsBloodModelListener listener) {
    control.addListener(listener);
  }

  public void removeSelection(final IAnimalForm form) {
    forms.remove(form);
    control.forAllDo(new IClosure<IHeartsBloodModelListener>() {
      public void execute(IHeartsBloodModelListener input) {
        input.fireSelectionRemoved(form);
      }
    });
  }

  public IAnimalForm[] getAnimalForms() {
    return forms.toArray(new IAnimalForm[forms.size()]);
  }

  public IAnimalForm addAnimalForm(String name, int strength, int stamina) {
    final IAnimalForm form = new AnimalForm(name, strength, stamina, context.getBasicCharacterContext().isExperienced());
    forms.add(form);
    return form;
  }

  public void addCharacterChangeListener(ICharacterChangeListener listener) {
    context.getCharacterListening().addChangeListener(listener);
  }

}