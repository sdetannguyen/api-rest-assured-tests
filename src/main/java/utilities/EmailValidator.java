package utilities;

import exceptions.AutomationTestRunException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(final String email) {
        if (!isEmail(email)) {
            throw new AutomationTestRunException("Invalid email format. please check your email again!!! \n" +
                    "Your inputted email is: " + email);
        }
        return true;
    }

    /**
     * Validate email with regular expression
     *
     * @param email for validation
     * @return true valid email, false invalid email
     */
    public boolean isEmail(final String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

