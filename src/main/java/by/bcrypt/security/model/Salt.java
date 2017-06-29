package by.bcrypt.security.model;

import by.bcrypt.security.controller.WebController;

import java.util.Properties;

/**
 * Created by Katy on 29.06.2017.
 */
public class Salt {


    public String getPostfix() {
        Properties property = new Properties();
        String postfix = null;
        try {
            ClassLoader myCL = WebController.class.getClassLoader();
            property.load(
                    myCL.getResourceAsStream(
                            "auth.properties"));
            postfix = property.getProperty("pass.postfix");
        } catch (Exception x) {
            x.printStackTrace();
        }
        return postfix;
    }
}
