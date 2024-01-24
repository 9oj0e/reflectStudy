package ex03;

@Controller
public class UserController {
    @RequestMapping(uri = "/login")
    public void login() {
        System.out.println("로그인 호출됨");
    }

    @RequestMapping(uri = "/join")
    public void join() {
        System.out.println("회원가입 호출됨");
    }

    @RequestMapping(uri = "/userinfo")
    public void userinfo() {
        System.out.println("유저정보 보기");
    }

    @RequestMapping(uri = "/updatePassword")
    public void updatePassword(){
        System.out.println("패스워드 수정하기");
    }
}
