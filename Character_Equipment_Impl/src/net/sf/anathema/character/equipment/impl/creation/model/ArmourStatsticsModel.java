package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.equipment.creation.model.stats.IArmourStatisticsModel;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.health.IHealthTypeVisitor;
import net.sf.anathema.lib.data.Range;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.RangedIntValueModel;

public class ArmourStatsticsModel extends EquipmentStatisticsModel implements IArmourStatisticsModel {

  private final IIntValueModel bashingHardness = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel bashingSoak = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel fatigue = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel lethalHardness = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel lethalSoak = new RangedIntValueModel(new Range(0, Integer.MAX_VALUE), 0);
  private final IIntValueModel mobilityPenalty = new RangedIntValueModel(new Range(Integer.MIN_VALUE, 0), 0);

  public IIntValueModel getBashingHardnessModel() {
    return bashingHardness;
  }

  public IIntValueModel getBashingSoakModel() {
    return bashingSoak;
  }

  public IIntValueModel getFatigueModel() {
    return fatigue;
  }

  public IIntValueModel getLethalHardnessModel() {
    return lethalHardness;
  }

  public IIntValueModel getLethalSoakModel() {
    return lethalSoak;
  }

  public IIntValueModel getMobilityPenaltyModel() {
    return mobilityPenalty;
  }

  public IIntValueModel getHardnessModel(HealthType healthType) {
    final IIntValueModel[] model = new IIntValueModel[1];
    healthType.accept(new IHealthTypeVisitor() {
      public void visitAggravated(HealthType aggrevated) {
        model[0] = getLethalHardnessModel();
      }

      public void visitBashing(HealthType bashing) {
        model[0] = getBashingHardnessModel();
      }

      public void visitLethal(HealthType lethal) {
        model[0] = getLethalHardnessModel();
      }
    });
    return model[0];
  }

  public IIntValueModel getSoakModel(HealthType healthType) {
    final IIntValueModel[] model = new IIntValueModel[1];
    healthType.accept(new IHealthTypeVisitor() {
      public void visitAggravated(HealthType aggrevated) {
        model[0] = getLethalSoakModel();
      }

      public void visitBashing(HealthType bashing) {
        model[0] = getBashingSoakModel();
      }

      public void visitLethal(HealthType lethal) {
        model[0] = getLethalSoakModel();
      }
    });
    return model[0];
  }
}