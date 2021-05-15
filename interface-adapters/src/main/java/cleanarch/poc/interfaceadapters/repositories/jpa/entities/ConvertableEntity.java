package cleanarch.poc.interfaceadapters.repositories.jpa.entities;

public interface ConvertableEntity <E, M>{
    void loadFromModel(M model);
    M toModel();
}
