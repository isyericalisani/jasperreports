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
package net.sf.jasperreports6.components.spiderchart;

import org.xml.sax.Attributes;

import net.sf.jasperreports6.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports6.engine.xml.JRBaseFactory;
import net.sf.jasperreports6.engine.xml.JRXmlConstants;

/**
 * 
 * @author Sanda Zaharia (shertage@users.sourceforge.net)
 */
public class SpiderChartXmlFactory extends JRBaseFactory
{

	@Override
	public Object createObject(Attributes attrs) throws Exception
	{
		SpiderChartComponent chartComponent = new SpiderChartComponent();
		
		EvaluationTimeEnum evaluationTime = EvaluationTimeEnum.getByName(attrs.getValue(JRXmlConstants.ATTRIBUTE_evaluationTime));
		if (evaluationTime != null)
		{
			chartComponent.setEvaluationTime(evaluationTime);
		}
		else
		{
			chartComponent.setEvaluationTime(EvaluationTimeEnum.NOW);
		}
		
		if (chartComponent.getEvaluationTime() == EvaluationTimeEnum.GROUP)
		{
			String groupName = attrs.getValue(JRXmlConstants.ATTRIBUTE_evaluationGroup);
			chartComponent.setEvaluationGroup(groupName);
		}
		
		return chartComponent;
	}

}
