package net.sf.anathema.namegenerator.exalted;

import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.namegenerator.domain.INameGenerator;
import net.sf.anathema.namegenerator.domain.realm.RealmNameGenerator;
import net.sf.anathema.namegenerator.exalted.domain.ThresholdNameGenerator;
import net.sf.anathema.namegenerator.presenter.model.IGeneratorTypeModel;
import net.sf.anathema.namegenerator.presenter.model.INameGeneratorModel;

public class ExaltedNameGeneratorModel implements INameGeneratorModel {

  public static final Identificate THRESHOLD_ID = new Identificate("Threshold"); //$NON-NLS-1$
  private final IIdentificate[] nameGeneratorTypes = new IIdentificate[] { new Identificate("Realm"), THRESHOLD_ID }; //$NON-NLS-1$
  private final Map<IIdentificate, IGeneratorTypeModel> typeModelsByType = new HashMap<IIdentificate, IGeneratorTypeModel>();
  private IIdentificate selectedGeneratorType = nameGeneratorTypes[1];
  private final ChangeControl selectedGeneratorTypeListeners = new ChangeControl();
  private final Map<IIdentificate, INameGenerator> generatorsByIdentificate = new HashMap<IIdentificate, INameGenerator>();

  public ExaltedNameGeneratorModel() {
    generatorsByIdentificate.put(nameGeneratorTypes[0], new RealmNameGenerator());
    generatorsByIdentificate.put(nameGeneratorTypes[1], new ThresholdNameGenerator());
  }

  public IIdentificate[] getGeneratorTypes() {
    return nameGeneratorTypes;
  }

  public IGeneratorTypeModel getGeneratorTypeModel(IIdentificate type) {
    return typeModelsByType.get(type);
  }

  public void addGeneratorTypeChangeListener(IChangeListener listener) {
    selectedGeneratorTypeListeners.addChangeListener(listener);
  }

  public IIdentificate getSelectedGeneratorType() {
    return selectedGeneratorType;
  }

  public void setGeneratorType(IIdentificate selectedGeneratorType) {
    if (ObjectUtilities.equals(selectedGeneratorType, this.selectedGeneratorType)) {
      return;
    }
    this.selectedGeneratorType = selectedGeneratorType;
    selectedGeneratorTypeListeners.fireChangedEvent();
  }

  public String[] generateNames(int count) {
    INameGenerator generator = generatorsByIdentificate.get(getSelectedGeneratorType());
    return generator.createNames(count);
  }
}