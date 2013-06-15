package net.sf.anathema.namegenerator.exalted;

import com.google.common.base.Objects;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.namegenerator.domain.INameGenerator;
import net.sf.anathema.namegenerator.domain.realm.RealmNameGenerator;
import net.sf.anathema.namegenerator.exalted.domain.ThresholdNameGenerator;
import net.sf.anathema.namegenerator.presenter.model.INameGeneratorModel;
import org.jmock.example.announcer.Announcer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ExaltedNameGeneratorModel implements INameGeneratorModel {

  private Identifier selectedGeneratorType;
  private final Announcer<IChangeListener> selectedGeneratorTypeListeners = Announcer.to(IChangeListener.class);
  private final Map<Identifier, INameGenerator> generatorsByIdentificate = new LinkedHashMap<>();

  public ExaltedNameGeneratorModel() {
    generatorsByIdentificate.put(new SimpleIdentifier("Namegenerator.Realm"), new RealmNameGenerator());
    generatorsByIdentificate.put(new SimpleIdentifier("Namegenerator.Threshold"), new ThresholdNameGenerator());
    selectedGeneratorType = generatorsByIdentificate.keySet().iterator().next();
  }

  @Override
  public Set<Identifier> getGeneratorTypes() {
    return generatorsByIdentificate.keySet();
  }

  @Override
  public void addGeneratorTypeChangeListener(IChangeListener listener) {
    selectedGeneratorTypeListeners.addListener(listener);
  }

  @Override
  public Identifier getSelectedGeneratorType() {
    return selectedGeneratorType;
  }

  @Override
  public void setGeneratorType(Identifier selectedGeneratorType) {
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