package com.sportyshoes.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.sportyshoes.entity.Country;
import com.sportyshoes.entity.Order;
import com.sportyshoes.entity.Product;
import com.sportyshoes.entity.ProductCategory;
import com.sportyshoes.entity.State;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

	private EntityManager entityManager;
	
	@Autowired
	public MyDataRestConfig(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
    	
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};

        disableHttpMethods(Order.class, config, theUnsupportedActions);
        disableHttpMethods(Product.class, config, theUnsupportedActions);
        disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);
        disableHttpMethods(Country.class, config, theUnsupportedActions);
        disableHttpMethods(State.class, config, theUnsupportedActions);
        
        //call an internal helper method
        exposeIds(config);
        
    }
    
    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }
    
    private void exposeIds(RepositoryRestConfiguration config) {
    	
    	Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
    	
    	List<Class> entityClasses  = new ArrayList<>(); 
    	
    	for(EntityType tempEntityType : entities) {
    		entityClasses.add(tempEntityType.getJavaType());    	
    	}
    	
    	Class[] domainTypes = entityClasses.toArray(new Class[0]);
    	
    	config.exposeIdsFor(domainTypes);
    	
    }
    
    
}
