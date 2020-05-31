import * as React from 'react';
import {ChangeEvent, useState} from 'react';
import {TaskApi, TaskCreateDTO, TaskDTO} from "../api/generated/api";
import styled from "styled-components";

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
    const [newTask, setNewTask] = useState<string>("");
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

    const textChange = (e: ChangeEvent<HTMLInputElement>) => {
        setNewTask(e.currentTarget.value);
    }

    const registerButtonClick = () => {
        createTask({title: newTask});
    }

    const listTask = (taskList: Array<Task>) => {
        return taskList.map(task => <StyledDiv><input type="checkbox"/>
            <li key={task.id}>{task.title}</li>
        </StyledDiv>);
    }

    const deleteTask = async (id: number) => {
        try {
            await new TaskApi().taskDelete(id);
        } catch (e) {
            console.log(e);
        }
    };

    return (
        <div>
            <h2>{props.name}</h2>
            <ul>{listTask(taskList)}</ul>
            <button onClick={() => showList()}>表示</button>
            <div>
                <input type="text" onChange={e => textChange(e)}/>
                <button onClick={() => registerButtonClick()}>登録</button>
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
        const response = await new TaskApi().tasksGet();
        return response.data;
    } catch (error) {
        throw new Error(`Error! HTTP Status: ${error.response}`);
    }
}

async function createTask(taskCreateDTO: TaskCreateDTO) {
    try {
        const response = await new TaskApi().taskPost(taskCreateDTO);
        //const response = await new TaskApi().taskOptions();
        return response.data;
    } catch (error) {
        console.log(error);
        //throw new Error(`Error! HTTP Status: ${error.response}`);
    }
}

const StyledDiv = styled.label`
    display:flex
`
export default Board;
