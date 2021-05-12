package cleanarch.poc.external.services.spring.jpa.entity;

public interface ConvertableEntity <E, M>{
    void loadFromModel(M model);
    M toModel();
}
