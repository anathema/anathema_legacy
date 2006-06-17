package net.sf.anathema.character.library.quality.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.library.quality.presenter.IQuality;
import net.sf.anathema.character.library.quality.presenter.IQualityPredicate;
import net.sf.anathema.character.library.quality.presenter.IQualitySelection;
import net.sf.anathema.character.library.quality.presenter.IQualityType;
import net.sf.anathema.lib.util.Identificate;

public abstract class Quality extends Identificate implements IQuality {

  private final List<IQualityPredicate> prerequisites = new ArrayList<IQualityPredicate>();
  private final IQualityType type;

  public Quality(String id, IQualityType type) {
    super(id);
    this.type = type;
  }

  protected final List<IQualityPredicate> getPrerequisiteList() {
    return prerequisites;
  }

  public IQualityType getType() {
    return type;
  }

  public void addCondition(IQualityPredicate prerequisite) {
    prerequisites.add(prerequisite);
  }

  public boolean prerequisitesFulfilled(IQualitySelection<? extends IQuality>[] selectedQualities) {
    for (IQualityPredicate prerequisite : prerequisites) {
      if (!prerequisite.isFulfilled(selectedQualities)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Quality)) {
      return false;
    }
    Quality otherQuality = (Quality) obj;
    return super.equals(obj) && this.type == otherQuality.type;
  }

  @Override
  public int hashCode() {
    return super.hashCode() + type.hashCode();
  }
}