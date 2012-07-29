package net.sf.anathema.characterengine.persona;

import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.Type;
import net.sf.anathema.characterengine.quality.TypeClosure;

public class ExecuteQualityClosureIfTypeMatches implements TypeClosure {
  private final Type type;
  private final QualityClosure closure;
  private final Quality quality;

  public ExecuteQualityClosureIfTypeMatches(Type type, Quality quality, QualityClosure closure) {
    this.type = type;
    this.closure = closure;
    this.quality = quality;
  }

  @Override
  public void execute(Type actualType) {
    if (actualType.equals(type)) {
      closure.execute(quality);
    }
  }
}