/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jee.wallet.controller.bean;

import java.util.List;
import java.util.Map;
import jee.wallet.model.entities.Company;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Quentin
 */
public class LazyExchangeModel extends LazyDataModel<Company> {
    
    private final List<Company> datasource;
    
    public LazyExchangeModel(List<Company> companies) {
        datasource = companies;
        setRowCount(datasource.size());
    }
    
    @Override  
    public Company getRowData(String rowKey) {  
        for(Company company : datasource) {  
            if(company.getCode().equals(rowKey))  
                return company;  
        }  
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Company company) {  
        return company.getCode();  
    } 
    
    @Override
    public List<Company> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        System.out.println("first : " + first);
        System.out.println("pageSize : " + pageSize);
        setRowCount(datasource.size());  
        return datasource.subList(first, first + pageSize);
    }

    @Override
    public List<Company> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, String> filters) {
        return load(first, pageSize, "", SortOrder.UNSORTED, filters);
    }
}
