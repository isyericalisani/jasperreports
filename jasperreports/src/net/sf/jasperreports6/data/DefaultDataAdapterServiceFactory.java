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
package net.sf.jasperreports6.data;

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports6.data.bean.BeanDataAdapter;
import net.sf.jasperreports6.data.bean.BeanDataAdapterService;
import net.sf.jasperreports6.data.csv.CsvDataAdapter;
import net.sf.jasperreports6.data.csv.CsvDataAdapterService;
import net.sf.jasperreports6.data.ds.DataSourceDataAdapter;
import net.sf.jasperreports6.data.ds.DataSourceDataAdapterService;
import net.sf.jasperreports6.data.ejbql.EjbqlDataAdapter;
import net.sf.jasperreports6.data.ejbql.EjbqlDataAdapterService;
import net.sf.jasperreports6.data.empty.EmptyDataAdapter;
import net.sf.jasperreports6.data.empty.EmptyDataAdapterService;
import net.sf.jasperreports6.data.excel.ExcelDataAdapter;
import net.sf.jasperreports6.data.excel.ExcelDataAdapterService;
import net.sf.jasperreports6.data.hibernate.HibernateDataAdapter;
import net.sf.jasperreports6.data.hibernate.HibernateDataAdapterService;
import net.sf.jasperreports6.data.hibernate.spring.SpringHibernateDataAdapter;
import net.sf.jasperreports6.data.hibernate.spring.SpringHibernateDataAdapterService;
import net.sf.jasperreports6.data.jdbc.JdbcDataAdapter;
import net.sf.jasperreports6.data.jdbc.JdbcDataAdapterContributorFactory;
import net.sf.jasperreports6.data.jdbc.JdbcDataAdapterService;
import net.sf.jasperreports6.data.jndi.JndiDataAdapter;
import net.sf.jasperreports6.data.jndi.JndiDataAdapterService;
import net.sf.jasperreports6.data.json.JsonDataAdapter;
import net.sf.jasperreports6.data.json.JsonDataAdapterService;
import net.sf.jasperreports6.data.mondrian.MondrianDataAdapter;
import net.sf.jasperreports6.data.mondrian.MondrianDataAdapterService;
import net.sf.jasperreports6.data.provider.DataSourceProviderDataAdapter;
import net.sf.jasperreports6.data.provider.DataSourceProviderDataAdapterService;
import net.sf.jasperreports6.data.qe.QueryExecuterDataAdapter;
import net.sf.jasperreports6.data.qe.QueryExecuterDataAdapterService;
import net.sf.jasperreports6.data.random.RandomDataAdapter;
import net.sf.jasperreports6.data.random.RandomDataAdapterService;
import net.sf.jasperreports6.data.xls.XlsDataAdapter;
import net.sf.jasperreports6.data.xls.XlsDataAdapterService;
import net.sf.jasperreports6.data.xlsx.XlsxDataAdapter;
import net.sf.jasperreports6.data.xlsx.XlsxDataAdapterService;
import net.sf.jasperreports6.data.xml.XmlDataAdapter;
import net.sf.jasperreports6.data.xml.XmlDataAdapterService;
import net.sf.jasperreports6.data.xmla.XmlaDataAdapter;
import net.sf.jasperreports6.data.xmla.XmlaDataAdapterService;
import net.sf.jasperreports6.engine.JasperReportsContext;
import net.sf.jasperreports6.engine.ParameterContributorContext;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class DefaultDataAdapterServiceFactory implements DataAdapterContributorFactory
{

	/**
	 *
	 */
	private static final DefaultDataAdapterServiceFactory INSTANCE = new DefaultDataAdapterServiceFactory();

	/**
	 *
	 */
	private DefaultDataAdapterServiceFactory()
	{
	}

	/**
	 *
	 */
	public static DefaultDataAdapterServiceFactory getInstance()
	{
		return INSTANCE;
	}
	
	@Override
	public DataAdapterService getDataAdapterService(ParameterContributorContext context, DataAdapter dataAdapter)
	{
		//JasperReportsContext jasperReportsContext = context.getJasperReportsContext();
		DataAdapterService dataAdapterService = null;
		
		if (dataAdapter instanceof BeanDataAdapter)
		{
			dataAdapterService = new BeanDataAdapterService(context, (BeanDataAdapter)dataAdapter);
		}
		else if (dataAdapter instanceof CsvDataAdapter)
		{
			dataAdapterService = new CsvDataAdapterService(context, (CsvDataAdapter)dataAdapter);
		}
		else if (dataAdapter instanceof DataSourceDataAdapter)
		{
			dataAdapterService = new DataSourceDataAdapterService(context, (DataSourceDataAdapter)dataAdapter);
		}
		else if (dataAdapter instanceof EmptyDataAdapter)
		{
			dataAdapterService = new EmptyDataAdapterService(context, (EmptyDataAdapter)dataAdapter);
		}
		else if (dataAdapter instanceof RandomDataAdapter)
		{
			dataAdapterService = new RandomDataAdapterService(context, (RandomDataAdapter)dataAdapter);
		}
		else if (dataAdapter instanceof JndiDataAdapter)
		{
			dataAdapterService = new JndiDataAdapterService(context, (JndiDataAdapter)dataAdapter);//FIXME maybe want some cache here
		}
		else if (dataAdapter instanceof DataSourceProviderDataAdapter)
		{
			dataAdapterService = new DataSourceProviderDataAdapterService(context, (DataSourceProviderDataAdapter)dataAdapter);
		}
		else if (dataAdapter instanceof QueryExecuterDataAdapter)
		{
			dataAdapterService = new QueryExecuterDataAdapterService(context, (QueryExecuterDataAdapter)dataAdapter);
		}
		
		// these following three adapters must be kept in order of inheritance hierarchy
		else if (dataAdapter instanceof ExcelDataAdapter)
		{
			dataAdapterService = new ExcelDataAdapterService(context, (ExcelDataAdapter)dataAdapter);
		}
		else if (dataAdapter instanceof XlsxDataAdapter)
		{
			dataAdapterService = new XlsxDataAdapterService(context, (XlsxDataAdapter)dataAdapter);
		}
		else if (dataAdapter instanceof XlsDataAdapter)
		{
			dataAdapterService = new XlsDataAdapterService(context, (XlsDataAdapter)dataAdapter);
		}
		// end excel

		else if (dataAdapter instanceof XmlDataAdapter)
		{
			dataAdapterService = new XmlDataAdapterService(context, (XmlDataAdapter)dataAdapter);
		}
		else if (dataAdapter instanceof JsonDataAdapter)
		{
			dataAdapterService = new JsonDataAdapterService(context, (JsonDataAdapter)dataAdapter);
		}
		else if (dataAdapter instanceof HibernateDataAdapter)
		{
			dataAdapterService = new HibernateDataAdapterService(context, (HibernateDataAdapter)dataAdapter);
		}
		else if (dataAdapter instanceof SpringHibernateDataAdapter)
		{
			dataAdapterService = new SpringHibernateDataAdapterService(context, (SpringHibernateDataAdapter)dataAdapter);
		}
		else if (dataAdapter instanceof EjbqlDataAdapter)
		{
			dataAdapterService = new EjbqlDataAdapterService(context, (EjbqlDataAdapter)dataAdapter);
		}
		else if (dataAdapter instanceof MondrianDataAdapter)
		{
			dataAdapterService = new MondrianDataAdapterService(context, (MondrianDataAdapter)dataAdapter);
		}
		else if (dataAdapter instanceof XmlaDataAdapter)
		{
			dataAdapterService = new XmlaDataAdapterService(context, (XmlaDataAdapter)dataAdapter);
		}
		else if (dataAdapter instanceof JdbcDataAdapter)
		{
			JasperReportsContext jasperReportsContext = context.getJasperReportsContext();
			
			List<JdbcDataAdapterContributorFactory> bundles = jasperReportsContext.getExtensions(
					JdbcDataAdapterContributorFactory.class);
			for (Iterator<JdbcDataAdapterContributorFactory> it = bundles.iterator(); it.hasNext();)
			{
				JdbcDataAdapterContributorFactory factory = it.next();
				DataAdapterService service = factory.getDataAdapterService(context, (JdbcDataAdapter)dataAdapter);
				if (service != null)
				{
					dataAdapterService = service;
					break;
				}
			}

			if (dataAdapterService == null)
			{
				dataAdapterService = new JdbcDataAdapterService(context, (JdbcDataAdapter)dataAdapter);
			}
		}
		
		return dataAdapterService;
	}
  
}
