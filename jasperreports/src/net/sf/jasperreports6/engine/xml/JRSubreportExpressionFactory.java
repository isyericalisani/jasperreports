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

import net.sf.jasperreports6.engine.JasperReport;
import net.sf.jasperreports6.engine.design.JRDesignExpression;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @deprecated To be removed.
 */
public class JRSubreportExpressionFactory extends JRBaseFactory
{

	@Override
	public Object createObject(Attributes atts)
	{
		JRDesignExpression expression = new JRDesignExpression();

		String value = atts.getValue(JRXmlConstants.ATTRIBUTE_class);
		
		if (value != null)
		{
			// being backward compatible
			if(value.equals("dori.jasper.engine.JasperReport"))
			{
				value = JasperReport.class.getName();
			}
			expression.setValueClassName(value);
		}
		else
		{
			expression.setValueClass(java.lang.String.class);
		}

		return expression;
	}
			

}
