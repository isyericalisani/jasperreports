/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2023 Cloud Software Group, Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports6.engine.xml;

import org.xml.sax.Attributes;

import net.sf.jasperreports6.engine.JRVariable;
import net.sf.jasperreports6.engine.design.JRDesignDataset;
import net.sf.jasperreports6.engine.design.JRDesignSubreportReturnValue;
import net.sf.jasperreports6.engine.design.JRValidationException;
import net.sf.jasperreports6.engine.design.JasperDesign;
import net.sf.jasperreports6.engine.type.CalculationEnum;

/**
 * Factory class for {@link net.sf.jasperreports6.engine.JRSubreportReturnValue JRSubreportReturnValue}
 * creation.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 */
public class JRSubreportReturnValueFactory extends JRBaseFactory
{

	/**
	 * Creates an object from a subreport copied value XML element.
	 * 
	 * @param atts the element attributes
	 * @return a {@link JRDesignSubreportReturnValue JRDesignSubreportReturnValue} object
	 */
	@Override
	public Object createObject(Attributes atts)
	{
		JRXmlLoader xmlLoader = (JRXmlLoader) digester.peek(digester.getCount() - 1);
		JasperDesign design = (JasperDesign) digester.peek(digester.getCount() - 2);
		
		JRDesignSubreportReturnValue returnValue = new JRDesignSubreportReturnValue();

		String variableName = atts.getValue(JRXmlConstants.ATTRIBUTE_toVariable);
		
		XmlLoaderReportContext reportContext = xmlLoader.getReportContext();
		String subdatasetName = reportContext == null ? null : reportContext.getSubdatesetName();
		JRDesignDataset dataset = subdatasetName == null ? design.getMainDesignDataset() 
				: (JRDesignDataset) design.getDatasetMap().get(subdatasetName);
		JRVariable variable = dataset == null ? null : dataset.getVariablesMap().get(variableName);
		if (variable == null)
		{
			//TODO do we need this check here?  JRVerifier.verifySubreport also validates return values
			xmlLoader.addError(new JRValidationException("Unknown variable " + variableName, returnValue));
		}
		
		returnValue.setSubreportVariable(atts.getValue(JRXmlConstants.ATTRIBUTE_subreportVariable));
		returnValue.setToVariable(variableName);

		CalculationEnum calculation = CalculationEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_calculation));
		if (calculation != null)
		{
			returnValue.setCalculation(calculation);
		}
		
		String incrementerFactoryClass = atts.getValue(JRXmlConstants.ATTRIBUTE_incrementerFactoryClass);
		if (incrementerFactoryClass != null)
		{
			returnValue.setIncrementerFactoryClassName(incrementerFactoryClass);
		}

		return returnValue;
	}

}
