package Reposiory;

import Data.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskFileRepository implements IRepository<Task,Integer>
{
    private ArrayList<Task> tasks;
    private Integer id;
    public TaskFileRepository()
    {
        readFile();
    }
    private void readFile()
    {
        try(BufferedReader reader = new BufferedReader(new FileReader("tasks.txt")))
        {
            String line;
            tasks = new ArrayList<>();
            while ((line=reader.readLine())!=null)
            {
                String[] split= line.split(";");
                if(split.length>3)
                {
                    Task task = new Task();
                    task.setId(Integer.valueOf(split[0]));
                    task.setName(split[1]);
                    task.setDescription(split[2]);
                    task.setDificulty(Byte.valueOf(split[3]));
                    tasks.add(task);
                    id= task.getId()+1;
                    System.out.println(task);

                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void writeFile()
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt")))
        {
            for (Task task:tasks)
            {
                writer.write(task.getId()+";"+task.getName()+";"+task.getDescription()+";"+task.getDificulty());
                writer.newLine();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Task get(Integer integer)
    {
        for (Task task:tasks)
            if(task.getId()==integer)
                return task;
        return null;
    }

    @Override
    public Task add(Task task)
    {
        task.setId(id);
        id++;
        tasks.add(task);
        writeFile();
        return task;
    }

    @Override
    public boolean remove(Integer integer)
    {
        for (int i=0;i<tasks.size();i++)
            if(tasks.get(i).getId().compareTo(integer)==0)
            {
                tasks.remove(i);
                writeFile();
                return  true;
            }
        return false;
    }

    @Override
    public Task update(Task task)
    {
        if(remove(task.getId()))
        {
            tasks.add(task);
            writeFile();
            return task;
        }
        else
        {
            return get(task.getId());
        }
    }

    @Override
    public List<Task> getAll()
    {
        return tasks;
    }


}
