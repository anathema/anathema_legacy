package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.stats.IArmourStatisticsModel;
import net.sf.anathema.character.main.health.HealthType;
import net.sf.anathema.character.main.health.IHealthTypeVisitor;
import net.sf.anathema.lib.data.Range;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.RangedIntValueModel;

public class ArmourStatsticsModel extends EquipmentStatisticsModel implements IArmourStatisticsModel {

  private final IIntValueModel bashingHardness = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel bashingSoak = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel fatigue = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel lethalHardness = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel lethalSoak = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel aggravatedSoak = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel mobilityPenalty = new RangedIntValueModel(new Range(Integer.MIN_VALUE, 0), 0);

  @Override
  public IIntValueModel getBashingHardnessModel() {
    return bashingHardness;
  }

  @Override
  public IIntValueModel getBashingSoakModel() {
    return bashingSoak;
  }

  @Override
  public IIntValueModel getFatigueModel() {
    return fatigue;
  }

  @Override
  public IIntValueModel getLethalHardnessModel() {
    return lethalHardness;
  }

  @Override
  public IIntValueModel getLethalSoakModel() {
    return lethalSoak;
  }

  @Override
  public IIntValueModel getMobilityPenaltyModel() {
    return mobilityPenalty;
  }

  @Override
  public IIntValueModel getHardnessModel(HealthType healthType) {
    final IIntValueModel[] model = new IIntValueModel[1];
    healthType.accept(new IHealthTypeVisitor() {
      @Override
      public void visitAggravated(HealthType aggrevated) {
        model[0] = getLethalHardnessModel();
      }

      @Override
      public void visitBashing(HealthType bashing) {
        model[0] = getBashingHardnessModel();
      }

      @Override
      public void visitLethal(HealthType lethal) {
        model[0] = getLethalHardnessModel();
      }
    });
    return model[0];
  }

  @Override
  public IIntValueModel getSoakModel(HealthType healthType) {
    final IIntValueModel[] model = new IIntValueModel[1];
    healthType.accept(new IHealthTypeVisitor() {
      @Override
      public void visitAggravated(HealthType aggrevated) {
        model[0] = getAggravatedSoakModel();
      }

      @Override
      public void visitBashing(HealthType bashing) {
        model[0] = getBashingSoakModel();
      }

      @Override
      public void visitLethal(HealthType lethal) {
        model[0] = getLethalSoakModel();
      }
    });
    return model[0];
  }

  @Override
  public IIntValueModel getAggravatedSoakModel() {
    return aggravatedSoak;
  }
}