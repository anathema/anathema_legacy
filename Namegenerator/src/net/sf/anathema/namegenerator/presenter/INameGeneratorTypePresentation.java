package net.sf.anathema.namegenerator.presenter;

import net.sf.anathema.lib.util.Identified;

import javax.swing.JComponent;

public interface INameGeneratorTypePresentation {

  JComponent initGeneratorTypePresentation(Identified generatorType);
}