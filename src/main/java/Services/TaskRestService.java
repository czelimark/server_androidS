package Services;

import Data.Request;
import Data.Task;
import Reposiory.IRepository;
import Reposiory.TaskFileRepository;
import Security.Security;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.function.Consumer;

@RequestMapping("/tasks")
@RestController("/tasksController")
public class TaskRestService
{
    private IRepository<Task,Integer> taskRepository;

    public TaskRestService()
    {
        taskRepository = new TaskFileRepository();
    }

    @RequestMapping("/get")
    public Task getTask(@RequestParam Integer id)
    {
        return taskRepository.get(id);
    }

    @PostMapping("saveSafe")
    public Task saveSafe(@RequestBody Request<Task>requestTask)
    {
        if(Security.checkToken(requestTask.getToken()))
            return taskRepository.add(requestTask.getT());
        return requestTask.getT();
    }
    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable("id") Request<Integer> requestId)
    {
        System.out.println("delete "+requestId.getT());
        if(Security.checkToken(requestId.getToken()))
            return taskRepository.remove(requestId.getT());
        return false;
    }
    @GetMapping("/all")
    public List<Task> getAll()
    {
        System.out.println("get AllTasks!");
        return taskRepository.getAll();
    }

    @GetMapping("/allSafe")
    public List<Task> getAllSafe(@RequestParam String token)
    {
        System.out.println("get AllTasks!");
        if(Security.checkToken(token))
            return taskRepository.getAll();
        return null;
    }

    @PutMapping("/update")
    public Task updateTask(@RequestBody Request<Task> requestTask)
    {
        System.out.println("update"+requestTask.getT().toString());
        if(Security.checkToken(requestTask.getToken()))
            return taskRepository.update(requestTask.getT());
        return requestTask.getT();
    }

}
