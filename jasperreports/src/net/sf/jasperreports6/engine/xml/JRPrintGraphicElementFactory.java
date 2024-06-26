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

import net.sf.jasperreports6.engine.base.JRBasePrintGraphicElement;
import net.sf.jasperreports6.engine.type.FillEnum;
import net.sf.jasperreports6.engine.type.PenEnum;
import net.sf.jasperreports6.engine.util.JRPenUtil;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class JRPrintGraphicElementFactory extends JRBaseFactory
{
	private static final Log log = LogFactory.getLog(JRPrintGraphicElementFactory.class);

	@Override
	public Object createObject(Attributes atts)
	{
		JRBasePrintGraphicElement graphicElement = (JRBasePrintGraphicElement)digester.peek();
		
		PenEnum pen = PenEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_pen));
		if (pen != null)
		{
			if (log.isWarnEnabled())
			{
				log.warn("The 'pen' attribute is deprecated. Use the <pen> tag instead.");
			}
			JRPenUtil.setLinePenFromPen(pen, graphicElement.getLinePen());
		}

		FillEnum fill = FillEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_fill));
		if (fill != null)
		{
			graphicElement.setFill(fill);
		}

		return graphicElement;
	}
	

}
