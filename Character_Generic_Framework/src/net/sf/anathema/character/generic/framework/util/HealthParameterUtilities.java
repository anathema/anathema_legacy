package net.sf.anathema.character.generic.framework.util;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.health.IHealthTypeVisitor;

public class HealthParameterUtilities {

  private HealthParameterUtilities() {
    throw new UnreachableCodeReachedException();
  }

  public static int getSoakValue(HealthType healthType, final int staminaValue) {
    final int[] soak = new int[1];
    healthType.accept(new IHealthTypeVisitor() {
      public void visitBashing(HealthType type) {
        soak[0] = staminaValue;
      }

      public void visitLethal(HealthType type) {
        soak[0] = (int) Math.floor(staminaValue / 2);
      }

      public void visitAggravated(HealthType type) {
        soak[0] = 0;
      }
    });
    return soak[0];
  }
}