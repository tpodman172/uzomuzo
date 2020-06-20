import * as React from 'react';
import {ChangeEvent, useState} from 'react';
import {TaskCreateDTO, TaskDTO, TaskProgressDTO, TasksApi} from "../api/generated";
import styled from "styled-components";
import CheckBox from "./molecules/checkbox";
import {Simulate} from "react-dom/test-utils";

// Propsの型定義
type IProps = {
    name: string;
}

type IState = {
    taskList: Array<Task>;
    newTask: string
}

type Task = {
    id: number,
    title: string,
}

const Board = (props: IProps) => {

    const [taskList, setTaskList] = useState<TaskDTO[]>([]);
    const [taskProgressList, setTaskProgressList] = useState<TaskProgressDTO[]>([]);
    const [newTask, setNewTask] = useState<string>("");
    const [checkedList, setCheckedList] = useState(new Set<number>());
    const showList = () => {
        console.log('クリックされました');
        getTaskList().then(list => {
            if (list) {
                list.forEach(value => console.log(value.id + ":" + value.title));
                setTaskList(list);
            }
        }).catch(err => {
            console.log(err.message);
        });
    }

    const showTaskProgressList = () => {
        // todo specify yesterday(using date-fns)
        fetchTaskProgress(new Date()).then(list => {
            if (list) {
                list.forEach(value => console.log(value.id + ":" + value.taskTitle));
                setTaskProgressList(list);
            }
        });
    }

    const textChange = (e: ChangeEvent<HTMLInputElement>) => {
        setNewTask(e.currentTarget.value);
    }

    const register = () => {
        createTask({title: newTask}).then(() => {
            // todo show toast?
        });
    }

    const handleCheck = (checked: boolean, id: number) => {
        updateProgress(id, checked).catch((e) => {
            console.log(e);
            // todo checkを元に戻す...？
        });
        if (checked) {
            setCheckedList((prev) => new Set(prev.add(id)));
        } else {
            setCheckedList((prev) => {
                prev.delete(id);
                return new Set(prev);
            });
        }
    }

    const listTask = (taskList: Array<Task>) => {
        return taskList.map(task => {

            return <StyledDiv>
                <li key={task.id}>
                    <CheckBox key={task.id} checked={checkedList.has(task.id)}
                              onCheck={(checked) => handleCheck(checked, task.id)}/>
                    {task.title}
                </li>
            </StyledDiv>
        });
    }

    const listTaskProgress = (taskList: Array<TaskProgressDTO>) => {
        return taskList.map(taskProgress => {

            return <StyledDiv>
                <li key={taskProgress.id}>
                    <CheckBox key={taskProgress.id} checked={checkedList.has(taskProgress.id)}
                              onCheck={(checked) => handleCheck(checked, taskProgress.id)}/>
                    {taskProgress.taskTitle}
                </li>
            </StyledDiv>
        });
    }
    const deleteTask = async (id: number) => {
        try {
            await new TasksApi().taskDelete(id);
        } catch (e) {
            console.log(e);
        }
    };

    console.log('render: board');
    return (
        <div>
            <h2>{props.name}</h2>
            <ul>{listTaskProgress(taskProgressList)}</ul>
            <button onClick={() => showList()}>表示</button>
            <button onClick={() => showTaskProgressList()}>昨日</button>
            <div>
                <input type="text" onChange={e => textChange(e)}/>
                <button onClick={() => register()}>登録</button>
                <button onClick={() => showList()}>削除</button>
            </div>
        </div>
    );
}

async function getTaskList() {
    try {
        var options = {
            //headers: {'X-SPECIAL-TOKEN': 'aaaaaa'}
        }
        //const response = await new PetsApi().listPets(10);
        const response = await new TasksApi().getTasks();
        return response.data;
    } catch (error) {
        throw new Error(`Error! HTTP Status: ${error.response}`);
    }
}

const fetchTaskProgress = async (targetDate: Date) => {
    try {
        // todo format date (using date-fns)
        const response = await new TasksApi().getTaskProgress('2020-06-20');
        return response.data;
    } catch (e) {
        console.log(e);
    }
}

// todo move...
async function createTask(taskCreateDTO: TaskCreateDTO) {
    try {
        const response = await new TasksApi().postTask(taskCreateDTO);
        //const response = await new TasksApi().taskOptions();
        return response.data;
    } catch (error) {
        console.log(error);
        //throw new Error(`Error! HTTP Status: ${error.response}`);
    }
}

const updateProgress = async (taskId: number, isCompleted: boolean) => {
    new TasksApi().putTaskProgress(taskId, isCompleted);
}

const StyledDiv = styled.label`
    display:flex
`
export default Board;
