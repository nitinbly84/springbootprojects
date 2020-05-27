package com.nitinagrawal.healthEndPoints;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
//One can use @Endpoint, @WebEndpoint, or @WebEndpointExtension or @RestControllerEndpoint also depending on the requirement.
//Also this way you can expose some other set of webservices in your application but on different port.
//Using @RestControllerEndpoint to access this end point in Restful way.
@RestControllerEndpoint(id = "features")
public class FeaturesEndpoint {
 
    private Map<String, Feature> features = new HashMap<>();
    
    {
    	features.put("feature1", new Feature("feature1", "FeatureName"));
    	features.put("feature2", new Feature("feature2", "AdvancedFeatureName"));
    }
 
    //Such operations are supported for the @Endpoint annotation used but here we are need
    // to use Rest based operations
    //@ReadOperation
    @GetMapping("/all")
    public Map<String, Feature> features() {
        return features;
    }
 
    //@ReadOperation & in method parameter one can use @Selector to receive the Path parameter
    // and while using @Selector one can give path parameter like - http://localhost:9000/courseActuator/features/:feature1
    // As key-value is not required then.
    @GetMapping
    // for below way one need to use like - http://localhost:9000/courseActuator/features?name=feature1
    public Feature feature(@RequestParam String name) {
        return features.get(name);
    }
 
    //@WriteOperation
    @PostMapping("/{name}")
    public String configureFeature(@PathVariable String name, Feature feature) {
        features.put(name, feature);
        return "Success";
    }
 
    //@DeleteOperation
    @DeleteMapping("/{name}")
    public ResponseEntity<Feature> deleteFeature(@PathVariable String name) {
    	return createResponse(features.remove(name));
    }
    
    private <T> ResponseEntity<T> createResponse(T data) {
    	ResponseEntity<T> response;
    	if(data != null) {
    		response = new ResponseEntity<>(data, HttpStatus.OK);
    	}
    	else {
    		response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	return response;
    }
    
}