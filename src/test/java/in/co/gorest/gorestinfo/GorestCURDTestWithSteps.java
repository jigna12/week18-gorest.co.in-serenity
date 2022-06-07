package in.co.gorest.gorestinfo;


import in.co.gorest.testbase.TestBase;
import in.co.gorest.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class GorestCURDTestWithSteps extends TestBase {
    static String name = "Rudra Embranthiri" + TestUtils.getRandomValue();
    static String email = TestUtils.getRandomValue() + "rudra_embranthiri@veum-schamberger.info";
    static String gender = "male";
    static String status = "active";
    static int userId;

    @Steps
    GorestSteps userSteps;

    @Title("This will create a new user")
    @Test
    public void test001() {
        userSteps.createUser(name,email,gender,status).statusCode(201).log().all();
    }

    @Title("Verify if the user was added to the application for name=Jagadish Somayaji")
    @Test
    public void test002() {
        name = "Jagadish Somayaji";
            HashMap<String, Object> userMap = userSteps.getUserInfoByFirstName(name);
            Assert.assertThat(userMap, hasValue(name));
            userId = (int) userMap.get("id");
            System.out.println(userId);
        }
    @Title("Update the user information and verify the updated information for ID=6142")
    @Test
    public void test003() {
        name = "Shah";
        gender="female";
        email="tena@gmail.com";
        status="active";
        userId = 6142;
        userSteps.updateUser(userId,name,email,gender,status).statusCode(200).log().body().body("name", equalTo(name), "email", equalTo(email));
    }
    @Title("Delete the user and verify if the user is deleted! for ID=6114")
    @Test
    public void test004() {
        userId = 6142;
        userSteps.deleteUser(userId).statusCode(204).log().status();
        userSteps.getUserById(userId).statusCode(404).log().status();
    }

}
