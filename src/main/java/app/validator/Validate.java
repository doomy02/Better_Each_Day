package app.validator;

public interface Validate<T> {

    boolean validateEmail (T entity);
    boolean validatePassword (T entity);
    boolean validateTokens (T entity);
    boolean validateName (T entity);

}
