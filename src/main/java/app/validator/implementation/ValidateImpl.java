package app.validator.implementation;

import app.validator.Validate;

public class ValidateImpl implements Validate<String> {
    @Override
    public boolean validateEmail(String entity) {
        String regex = "^[a-z0-9]+(?!.*(?:\\+{2,}|\\-{2,}|\\.{2,}))(?:[\\.+\\-]{0,1}[a-z0-9])*@(?:gmail\\.com|yahoo\\.com|proton\\.com)$";
        return entity.matches(regex);
    }

    @Override
    public boolean validatePassword(String entity) {
        String regex = "^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]).{6,})\\S$";
        return entity.matches(regex);
    }

    @Override
    public boolean validateTokens(String entity) {
        String regex = "^1\\d{2}|[2-9]\\d{2,}$";
        return entity.matches(regex);
    }

    @Override
    public boolean validateName(String entity) {
        String regex = "^(?=[a-zA-Z\\s]{2,25}$)(?=[a-zA-Z\\s])(?:([\\w\\s*?])\\1?(?!\\1))+$";
        return entity.matches(regex);
    }

}


