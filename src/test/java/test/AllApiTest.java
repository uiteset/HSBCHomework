package test;

import com.yosha.homework.Server;
import com.yosha.homework.conf.ConstValues;
import com.yosha.homework.controller.AuthController;
import com.yosha.homework.controller.RoleController;
import com.yosha.homework.controller.UserController;
import com.yosha.homework.controller.UserRoleController;
import com.yosha.homework.util.CommonResponse;
import com.yosha.homework.vo.auth.AuthInputVO;
import com.yosha.homework.vo.role.RoleCodeInputVO;
import com.yosha.homework.vo.role.RoleInputVO;
import com.yosha.homework.vo.user.UserInputVO;
import com.yosha.homework.vo.user.UserNameAuthInputVO;
import com.yosha.homework.vo.user.UserNameInputVO;
import com.yosha.homework.vo.userRole.UserRoleAuthInputVO;
import com.yosha.homework.vo.userRole.UserRoleInputVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Server.class})
@WebAppConfiguration
public class AllApiTest {

    @Autowired
    private UserController userController;
    @Autowired
    private RoleController roleController;
    @Autowired
    private UserRoleController userRoleController;
    @Autowired
    private AuthController authController;

    @Test
    public void test001() {
        //add new user
        CommonResponse result = this.addUser("user1", "password1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
    }

    @Test
    public void test002() {
        //Get User1
        CommonResponse result = this.getUser("user1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
    }

    @Test
    public void test003() {
        //Add User1 Again
        CommonResponse result = this.addUser("user1", "password1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test004() {
        //delete User1
        CommonResponse result = this.deleteUser("user1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
    }

    @Test
    public void test005() {
        //Get User1
        CommonResponse result = this.getUser("user1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test006() {
        //delete User1 again
        CommonResponse result = this.deleteUser("user1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test007() {
        //add user null username
        CommonResponse result = this.addUser(null, "password1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test008() {
        //add user long username
        String name = "useraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1";
        CommonResponse result = this.addUser(name, "password1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test009() {
        //add user chinese username
        CommonResponse result = this.addUser("user哈哈", "password1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test010() {
        //add user null password
        CommonResponse result = this.addUser("user1", null);
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test011() {
        //add user long username
        String password = "useraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1";
        CommonResponse result = this.addUser("user1", password);
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test012() {
        //add user chinese username
        CommonResponse result = this.addUser("user1", "password1哈哈");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test013() {
        //add too man user
        int i;
        for(i = 0; i < ConstValues.MAX_USER_STORAGE_SIZE; i++)
        {
            CommonResponse result = this.addUser("u" + i, "p" + i);
            Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
        }
        CommonResponse result = this.addUser("u" + i, "p" + i);
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test014() {
        //get one user with time limit
        UserNameInputVO input = new UserNameInputVO();
        input.setUsername("u"+(ConstValues.MAX_USER_STORAGE_SIZE-1));
        long start = System.currentTimeMillis();
        CommonResponse result = userController.get(input);
        long end = System.currentTimeMillis();
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
        Assert.assertTrue(end-start<100);
    }

    @Test
    public void test015() {
        //add role
        CommonResponse result = this.addRole("code1", "name");
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
    }

    @Test
    public void test016() {
        //Get role
        CommonResponse result = this.getRole("code1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
    }

    @Test
    public void test017() {
        //Add role Again
        CommonResponse result = this.addRole("code1", "name");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test018() {
        //delete User1
        CommonResponse result = this.deleteRole("code1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
    }

    @Test
    public void test019() {
        //Get role code1
        CommonResponse result = this.getRole("code1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test020() {
        //delete role again
        CommonResponse result = this.deleteRole("code1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test021() {
        //add role null code=
        CommonResponse result = this.addRole(null, "name");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test022() {
        //add role long code
        String code = "codeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1";
        CommonResponse result = this.addRole(code, "name");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test023() {
        //add role chinese code
        CommonResponse result = this.addRole("user哈哈", "name");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test024() {
        //add role null name
        CommonResponse result = this.addRole("test24", null);
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test025() {
        //add role long name
        String name = "nameaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1";
        CommonResponse result = this.addRole("test25", name);
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test026() {
        //add role chinese name
        CommonResponse result = this.addRole("test26", "user哈哈");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test027() {
        //add too man role
        int i;
        for(i = 0; i < ConstValues.MAX_ROLE_STORAGE_SIZE; i++)
        {
            CommonResponse result = this.addRole("r"+i, "n"+i);
            Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
        }
        CommonResponse result = this.addRole("r"+i, "n"+i);
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test028() {
        //get one role with time limit
        RoleCodeInputVO input = new RoleCodeInputVO();
        input.setRoleCode("r"+(ConstValues.MAX_USER_STORAGE_SIZE-1));
        long start = System.currentTimeMillis();
        CommonResponse result = roleController.get(input);
        long end = System.currentTimeMillis();
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
        Assert.assertTrue(end-start<100);
    }

    @Test
    public void test029() {
        //add user role relation
        CommonResponse result = this.addUserRole("u0", "r0");
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
    }

    @Test
    public void test030() {
        //add user role relation again
        CommonResponse result = this.addUserRole("u0", "r0");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test031() {
        //delete user role relation
        CommonResponse result = this.deleteUserRole("u0", "r0");
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
    }

    @Test
    public void test032() {
        //delete user role relation again
        CommonResponse result = this.deleteUserRole("u5", "r5");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test033() {
        //add too manay role relation
        int i;
        for(i = 0; i < ConstValues.MAX_ROLE_STORAGE_SIZE; i++)
        {
            CommonResponse result = this.addUserRole("u1", "r"+i);
            Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
        }
        CommonResponse result = this.addUserRole("u1", "r"+i);
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test034() {
        //do auth username null
        CommonResponse result = this.deleteUserRole(null, "p5");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test035() {
        //do auth null password
        CommonResponse result = this.doAuth("u3", null);
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test036() {
        //do auth
        CommonResponse result = this.doAuth("u1", "p1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
    }

    @Test
    public void test037() {
        //do auth and invalidate
        CommonResponse result = this.doAuth("u1", "p1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
        String token = (String)result.getData();
        result = this.invalidate(token);
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
    }

    @Test
    public void test038() {
        //do auth and invalidate twice
        CommonResponse result = this.doAuth("u1", "p1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
        String token = (String)result.getData();
        result = this.invalidate(token);
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
        result = this.invalidate(token);
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test039() {
        //check role with wrong token1
        CommonResponse result = this.checkRole("u1", "r1", "abc");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test040() {
        //check role with wrong token1
        CommonResponse result = this.checkRole("u1", "r1", "abc###123");
        Assert.assertEquals(result.getErrorCode(), ConstValues.FAILURE);
    }

    @Test
    public void test041() {
        //check role
        CommonResponse result = this.doAuth("u1", "p1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
        String token = (String)result.getData();
        result = this.checkRole("u1", "r1", token);
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
    }

    @Test
    public void test042() {
        //check no such role
        CommonResponse result = this.doAuth("u1", "p1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
        String token = (String)result.getData();
        result = this.checkRole("u20", "r12", token);
        Assert.assertFalse((boolean) result.getData());
    }

    @Test
    public void test043() {
        //get all roles
        CommonResponse result = this.doAuth("u1", "p1");
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
        String token = (String)result.getData();
        result = this.allRoles("u1", token);
        Assert.assertEquals(result.getErrorCode(), ConstValues.SUCCESS);
    }

    private CommonResponse addUser(String username, String password){
        UserInputVO input = new UserInputVO();
        input.setUsername(username);
        input.setPassword(password);
        return userController.add(input);
    }

    private CommonResponse deleteUser(String username){
        UserNameInputVO input = new UserNameInputVO();
        input.setUsername(username);
        return userController.delete(input);
    }

    private CommonResponse getUser(String username){
        UserNameInputVO input = new UserNameInputVO();
        input.setUsername(username);
        return userController.get(input);
    }

    private CommonResponse addRole(String roleCode, String roleName){
        RoleInputVO input = new RoleInputVO();
        input.setRoleCode(roleCode);
        input.setRoleName(roleName);
        return roleController.add(input);
    }

    private CommonResponse deleteRole(String roleCode){
        RoleCodeInputVO input = new RoleCodeInputVO();
        input.setRoleCode(roleCode);
        return roleController.delete(input);
    }

    private CommonResponse getRole(String roleCode){
        RoleCodeInputVO input = new RoleCodeInputVO();
        input.setRoleCode(roleCode);
        return roleController.get(input);
    }

    private CommonResponse addUserRole(String username, String roleCode){
        UserRoleInputVO input = new UserRoleInputVO();
        input.setUsername(username);
        input.setRoleCode(roleCode);
        return userRoleController.add(input);
    }

    private CommonResponse deleteUserRole(String username, String roleCode){
        UserRoleInputVO input = new UserRoleInputVO();
        input.setUsername(username);
        input.setRoleCode(roleCode);
        return userRoleController.delete(input);
    }

    private CommonResponse doAuth(String username, String password){
        UserInputVO input = new UserInputVO();
        input.setUsername(username);
        input.setPassword(password);
        return authController.auth(input);
    }

    private CommonResponse invalidate(String token){
        AuthInputVO input = new AuthInputVO();
        input.setToken(token);
        return authController.invalidate(input);
    }

    private CommonResponse allRoles(String username, String token){
        UserNameAuthInputVO input = new UserNameAuthInputVO();
        input.setUsername(username);
        input.setToken(token);
        return userRoleController.getAllRoles(input);
    }

    private CommonResponse checkRole(String username, String roleCode, String token){
        UserRoleAuthInputVO input = new UserRoleAuthInputVO();
        input.setUsername(username);
        input.setRoleCode(roleCode);
        input.setToken(token);
        return userRoleController.checkUserRoleExists(input);
    }

}
