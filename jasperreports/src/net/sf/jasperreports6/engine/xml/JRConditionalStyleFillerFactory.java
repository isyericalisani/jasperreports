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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;

import net.sf.jasperreports6.engine.design.JRDesignConditionalStyle;
import net.sf.jasperreports6.engine.design.JRDesignStyle;

/**
 * @author Ionut Nedelcu (ionutned@users.sourceforge.net)
 */
public class JRConditionalStyleFillerFactory extends JRAbstractStyleFactory
{
	private static final Log log = LogFactory.getLog(JRConditionalStyleFillerFactory.class);


	@Override
	public Object createObject(Attributes atts)
	{
		JRDesignConditionalStyle style = (JRDesignConditionalStyle) digester.peek();

		if (log.isWarnEnabled())
		{
			if (atts.getValue(JRXmlConstants.ATTRIBUTE_name) != null)
			{
				log.warn("Conditional style should not have a '" + JRXmlConstants.ATTRIBUTE_name + "' attribute.");
			}

			if (atts.getValue(JRXmlConstants.ATTRIBUTE_isDefault) != null)
			{
				log.warn("Conditional style should not have an '" + JRXmlConstants.ATTRIBUTE_isDefault + "' attribute.");
			}

			if (atts.getValue(JRXmlConstants.ATTRIBUTE_style) != null)
			{
				log.warn("Conditional style cannot have a '" + JRXmlConstants.ATTRIBUTE_style + "' attribute.");
			}
		}

		// set common style attributes
		setCommonStyle(style, atts);
		
		return style;
	}

	@Override
	protected void setParentStyle(JRDesignStyle currentStyle, String parentStyleName)
	{
		//nothing to do
	}
}
