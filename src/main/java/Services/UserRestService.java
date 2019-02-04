package Services;

import Data.Request;
import Data.User;
import Reposiory.UserFileRepository;
import Reposiory.IRepository;
import Security.Security;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController("/usersController")
public class UserRestService
{

    private IRepository<User,String> userRepository;
    public UserRestService()
    {
        userRepository = new UserFileRepository();
    }

    @RequestMapping(value="/get")
    public User get(@RequestParam String username)
    {
        return userRepository.get(username);
    }


    @RequestMapping(value = "/login")
    public Boolean login(@RequestParam(value = "user") String username,@RequestParam(value = "pass") String password)
    {
        System.out.println(username);
        System.out.println(password);
        User user = userRepository.get(username);
        if(user!=null)
        {
            if(user.getPassword().compareTo(password)==0)
                return  true;
        }
        return false;
    }

    @RequestMapping(value = "/loginSafe")
    public Request<Boolean> loginSafe(@RequestParam(value = "user") String username, @RequestParam(value = "pass") String password)
    {
        System.out.println(username);
        System.out.println(password);
        User user = userRepository.get(username);
        if(user!=null)
        {
            if(user.getPassword().compareTo(password)==0)
            {
                String token = Security.createToken(Security.secret,username);
                Security.useToken(token);
                return  new Request<>(true, token);
            }
        }
        return new Request<Boolean>(false,null);
    }
}
