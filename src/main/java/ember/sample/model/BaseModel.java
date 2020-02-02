package ember.sample.model;

import com.github.jasminb.jsonapi.Links;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Meta;

public class BaseModel {
    @Id
    @org.springframework.data.annotation.Id
    protected String id;

    @Meta
    private Meta meta;

    @com.github.jasminb.jsonapi.annotations.Links
    private Links links;
}
