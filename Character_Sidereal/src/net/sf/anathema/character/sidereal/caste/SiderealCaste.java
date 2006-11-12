package net.sf.anathema.character.sidereal.caste;

import net.sf.anathema.character.generic.caste.ICasteType;

public enum SiderealCaste implements ICasteType {

  Journeys {
    @Override
    public void accept(ISiderealCasteVisitor visitor) {
      visitor.visitJourneys(this);
    }
  },
  Serenity {
    @Override
    public void accept(ISiderealCasteVisitor visitor) {
      visitor.visitSerenity(this);
    }
  },
  Battles {
    @Override
    public void accept(ISiderealCasteVisitor visitor) {
      visitor.visitBattles(this);
    }
  },
  Secrets {
    @Override
    public void accept(ISiderealCasteVisitor visitor) {
      visitor.visitSecrets(this);
    }
  },
  Endings {
    @Override
    public void accept(ISiderealCasteVisitor visitor) {
      visitor.visitEndings(this);
    }
  };

  public abstract void accept(ISiderealCasteVisitor visitor);

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return name();
  }
}