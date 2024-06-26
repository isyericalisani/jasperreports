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

import net.sf.jasperreports6.charts.design.JRDesignTimeSeriesDataset;
import net.sf.jasperreports6.charts.type.TimePeriodEnum;
import net.sf.jasperreports6.engine.design.JRDesignChart;
import net.sf.jasperreports6.engine.xml.JRBaseFactory;

/**
 * @author Flavius Sana (flavius_sana@users.sourceforge.net)
 */
public class JRTimeSeriesDatasetFactory extends JRBaseFactory {
	
	private static final String ATTRIBUTE_timePeriod = "timePeriod";
	
	@Override
	public Object createObject( Attributes attrs ){	
		JRDesignChart chart = (JRDesignChart)digester.peek();
		
		JRDesignTimeSeriesDataset dataset = null; 
		
		if( chart.getDataset() == null ){
			dataset = new JRDesignTimeSeriesDataset( chart.getDataset() );
		}
		else {
			dataset = (JRDesignTimeSeriesDataset)chart.getDataset();
		}
		

		TimePeriodEnum timePeriod = TimePeriodEnum.getByName(attrs.getValue(ATTRIBUTE_timePeriod));
		if( timePeriod != null)
		{
			dataset.setTimePeriod(timePeriod);
		}
		
		
		chart.setDataset( dataset );
		
		return dataset;
	}
}
