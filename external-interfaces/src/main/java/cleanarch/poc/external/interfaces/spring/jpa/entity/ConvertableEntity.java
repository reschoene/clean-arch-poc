package cleanarch.poc.external.interfaces.spring.jpa.entity;

public interface ConvertableEntity <E, M>{
    void loadFromModel(M model);
    M toModel();
}
