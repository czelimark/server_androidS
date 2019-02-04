package Reposiory;

import Data.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class UserFileRepository implements IRepository<User,String>
{
    private ArrayList<User> users;

    public UserFileRepository()
    {
        readFile();
    }
    private void readFile()
    {
        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt")))
        {
            String line;
            users = new ArrayList<>();
            while ((line=reader.readLine())!=null)
            {
                String[] split= line.split(";");
                if(split.length>1)
                {
                    User user = new User(split[0],split[1]);
                    users.add(user);
                    System.out.println(user);
                }
            }
            users.add(new User("",""));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void writeFile()
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt")))
        {
            for (User user:users)
            {
                writer.write(user.getUserId()+";"+user.getPassword());
                writer.newLine();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public User get(String s)
    {
        for (User u :users)
        {
            if(u.getUserId().compareTo(s)==0)
            {
                return u;
            }
        }
        return null;
    }

    @Override
    public User add(User user)
    {
        users.add(user);
        writeFile();
        return user;
    }

    @Override
    public boolean remove(String s) {
        for (int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getUserId().compareTo(s)==0)
            {
                users.remove(i);
                return  true;
            }
        }
        return false;
    }

    @Override
    public User update(User user)
    {

        return user;
    }

    @Override
    public List<User> getAll() {
        return null;
    }


}
