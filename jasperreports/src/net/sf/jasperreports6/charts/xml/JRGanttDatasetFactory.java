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
package net.sf.jasperreports6.charts.xml;

import org.xml.sax.Attributes;

import net.sf.jasperreports6.charts.design.JRDesignGanttDataset;
import net.sf.jasperreports6.engine.design.JRDesignChart;
import net.sf.jasperreports6.engine.xml.JRBaseFactory;

/**
 * @author Peter Risko (peter@risko.hu)
 */
public class JRGanttDatasetFactory extends JRBaseFactory {

	@Override
	public Object createObject(Attributes atts)
	{
		JRDesignChart chart = (JRDesignChart) digester.peek();

		JRDesignGanttDataset dataset = null;

		if( chart.getDataset() == null ){
			dataset = new JRDesignGanttDataset( chart.getDataset() );
		}
		else {
			dataset = (JRDesignGanttDataset)chart.getDataset();
		}

		chart.setDataset( dataset );
		return dataset;
	}


}
