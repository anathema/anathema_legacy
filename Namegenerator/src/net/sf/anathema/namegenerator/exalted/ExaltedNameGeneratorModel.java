package net.sf.anathema.namegenerator.exalted;

import com.google.common.base.Objects;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.namegenerator.domain.INameGenerator;
import net.sf.anathema.namegenerator.domain.realm.RealmNameGenerator;
import net.sf.anathema.namegenerator.exalted.domain.ThresholdNameGenerator;
import net.sf.anathema.namegenerator.presenter.model.IGeneratorTypeModel;
import net.sf.anathema.namegenerator.presenter.model.INameGeneratorModel;
import org.jmock.example.announcer.Announcer;

import java.util.HashMap;
import java.util.Map;

public class ExaltedNameGeneratorModel implements INameGeneratorModel {

  public static final Identificate THRESHOLD_ID = new Identificate("Threshold"); //$NON-NLS-1$
  private final Identified[] nameGeneratorTypes = new Identified[] { new Identificate("Realm"), THRESHOLD_ID }; //$NON-NLS-1$
  private final Map<Identified, IGeneratorTypeModel> typeModelsByType = new HashMap<>();
  private Identified selectedGeneratorType = nameGeneratorTypes[1];
  private final Announcer<IChangeListener> selectedGeneratorTypeListeners = Announcer.to(IChangeListener.class);
  private final Map<Identified, INameGenerator> generatorsByIdentificate = new HashMap<>();

  public ExaltedNameGeneratorModel() {
    generatorsByIdentificate.put(nameGeneratorTypes[0], new RealmNameGenerator());
    generatorsByIdentificate.put(nameGeneratorTypes[1], new ThresholdNameGenerator());
  }

  @Override
  public Identified[] getGeneratorTypes() {
    return nameGeneratorTypes;
  }

  @Override
  public IGeneratorTypeModel getGeneratorTypeModel(Identified type) {
    return typeModelsByType.get(type);
  }

  @Override
  public void addGeneratorTypeChangeListener(IChangeListener listener) {
    selectedGeneratorTypeListeners.addListener(listener);
  }

  @Override
  public Identified getSelectedGeneratorType() {
    return selectedGeneratorType;
  }

  @Override
  public void setGeneratorType(Identified selectedGeneratorType) {
    if (Objects.equal(selectedGeneratorType, this.selectedGeneratorType)) {
      return;
    }
    this.selectedGeneratorType = selectedGeneratorType;
    selectedGeneratorTypeListeners.announce().changeOccurred();
  }

  @Override
  public String[] generateNames(int count) {
    INameGenerator generator = generatorsByIdentificate.get(getSelectedGeneratorType());
    return generator.createNames(count);
  }
}