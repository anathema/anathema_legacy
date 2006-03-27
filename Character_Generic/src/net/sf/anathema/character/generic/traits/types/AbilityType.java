package net.sf.anathema.character.generic.traits.types;

import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IRuleSetVisitor;
import net.sf.anathema.character.generic.traits.ITraitType;

public enum AbilityType implements ITraitType {
  Archery {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitArchery();
    }
  },
  Brawl {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitBrawl();
    }
  },
  MartialArts {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitMartialArts();
    }
  },
  Melee {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitMelee();
    }
  },
  Thrown {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitThrown();
    }
  },
  War {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitWar();
    }
  },
  Endurance {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitEndurance();
    }
  },
  Integrity {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitIntegrity();
    }
  },
  Performance {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitPerformance();
    }
  },
  Presence {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitPresence();
    }
  },
  Resistance {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitResistance();
    }
  },
  Survival {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitSurvival();
    }
  },
  Craft {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitCraft();
    }
  },
  Investigation {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitInvestigation();
    }
  },
  Lore {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitLore();
    }
  },
  Medicine {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitMedicine();
    }
  },
  Occult {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitOccult();
    }
  },
  Athletics {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitAthletics();
    }
  },
  Awareness {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitAwareness();
    }
  },
  Dodge {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitDodge();
    }
  },
  Larceny {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitLarceny();
    }
  },
  Stealth {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitStealth();
    }
  },
  Bureaucracy {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitBureaucracy();
    }
  },
  Linguistics {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitLinguistics();
    }
  },
  Ride {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitRide();
    }
  },
  Sail {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitSail();
    }
  },
  Socialize {
    @Override
    public void accept(IAbilityTypeVisitor visitor) {
      visitor.visitSocialize();
    }
  };

  public void accept(ITraitTypeVisitor visitor) {
    visitor.visitAbility(this);
  }

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }

  public abstract void accept(IAbilityTypeVisitor visitor);

  private static AbilityType[] getFirstEditionAbilities() {
    return new AbilityType[] {
        Archery,
        Brawl,
        MartialArts,
        Melee,
        Thrown,
        Endurance,
        Performance,
        Presence,
        Resistance,
        Survival,
        Craft,
        Investigation,
        Lore,
        Medicine,
        Occult,
        Athletics,
        Awareness,
        Dodge,
        Larceny,
        Stealth,
        Bureaucracy,
        Linguistics,
        Ride,
        Sail,
        Socialize };
  }

  private static AbilityType[] getSecondEditionAbilities() {
    return new AbilityType[] {
        Archery,
        MartialArts,
        Melee,
        Thrown,
        War,
        Integrity,
        Performance,
        Presence,
        Resistance,
        Survival,
        Craft,
        Investigation,
        Lore,
        Medicine,
        Occult,
        Athletics,
        Awareness,
        Dodge,
        Larceny,
        Stealth,
        Bureaucracy,
        Linguistics,
        Ride,
        Sail,
        Socialize };
  }

  public static AbilityType[] getAbilityTypes(IExaltedRuleSet rules) {
    final AbilityType[][] types = new AbilityType[1][];
    rules.accept(new IRuleSetVisitor() {
      public void visitCoreRules(IExaltedRuleSet set) {
        types[0] = getFirstEditionAbilities();
      }

      public void visitPowerCombat(IExaltedRuleSet set) {
        types[0] = getFirstEditionAbilities();
      }

      public void visitSecondEdition(IExaltedRuleSet set) {
        types[0] = getSecondEditionAbilities();
      }
    });
    return types[0];
  }
}