import * as React from "react";
import {ChangeEvent, useCallback, useEffect, useMemo, useState} from "react";
import {TasksApi} from "../../../../api";
import {TaskCreateDTO, TaskDTO} from "../../../../api/generated";
import TaskCard from "../../../task/component/atoms/TaskCard";
import {TaskList} from "../../../task/component/molecules/TaskList";
import EditableTaskCard from "../atoms/EditableTaskCard";
import styled from "styled-components";
import {Link} from "react-router-dom";

export const BoardAdminPage = () => {
    const [taskList, setTaskList] = useState<TaskDTO[]>([]);
    const [editingTaskId, setEditingTaskId] = useState<number>();
    const [newTask, setNewTask] = useState<string>("");

    useEffect(() => {
        fetchTasks().then(result => {
            setTaskList(result.data);
        });
    }, [])

    const handleCheck = useCallback((id: number, checked: boolean) => {
        setEditingTaskId(id);
    }, [setEditingTaskId]);

    const handleDelete = useCallback(async (id: number) => {
        await TasksApi().deleteTask(id);
        setEditingTaskId(undefined);
        fetchTasks().then(result => setTaskList(result.data));
    }, [setEditingTaskId]);

    const handleUpdate = useCallback(async (id: number, title: string) => {
        await TasksApi().putTask(id, {title: title});
        setEditingTaskId(undefined);
        fetchTasks().then(result => setTaskList(result.data));
    }, [setEditingTaskId, setTaskList]);

    const taskCards = useMemo(() => {
        return taskList.map(task => {
            if (task.id !== editingTaskId) {
                return <TaskCard key={task.id} taskId={task.id} taskTitle={task.title} checked={false}
                                 onCheck={handleCheck}/>
            } else {
                return <EditableTaskCard
                    key={task.id}
                    taskId={task.id}
                    taskTitle={task.title}
                    onCancel={() => {
                        setEditingTaskId(undefined)
                    }}
                    onUpdate={handleUpdate}
                    onDelete={handleDelete}/>
            }
        })
    }, [taskList, handleUpdate, handleCheck, handleDelete, editingTaskId]);

    const handleCreateTextChange = (e: ChangeEvent<HTMLInputElement>) => {
        setNewTask(e.currentTarget.value);
    };

    const handleRegister = () => {
        createTask({title: newTask}).then(() => {
            fetchTasks().then(result => setTaskList(result.data));
        });
    };

    return <StyledDiv>
        <TaskList taskCards={taskCards}/>
        <CreateNewArea>
            <input type="text" onChange={handleCreateTextChange}/>
            <button onClick={handleRegister}>登録</button>
        </CreateNewArea>
        <div>
            <Link to='/board'>やることリスト</Link>
        </div>
    </StyledDiv>;
};

const createTask = async (taskCreateDTO: TaskCreateDTO) => {
    try {
        const response = await TasksApi().postTask(taskCreateDTO);
        return response.data;
    } catch (error) {
        console.log(error); //todo
    }
}

const fetchTasks = async () => {
    const result = await TasksApi().getTasks();
    return result;
}

const StyledDiv = styled.div`
    > *:not(:first-child){
        margin-top:12px; 
    }
`

const CreateNewArea = styled.div`
    *:not(:first-child){
        margin-left: 12px;
    }
`;

