import * as React from "react";
import {ChangeEvent, useEffect, useState} from "react";
import {TasksApi} from "../../../../api";
import {TaskCreateDTO, TaskDTO} from "../../../../api/generated";
import TaskCard from "../../../task/component/atoms/TaskCard";
import {TaskList} from "../../../task/component/molecules/TaskList";
import EditableTaskCard from "../atoms/EditableTaskCard";
import styled from "styled-components";
import {Link} from "react-router-dom";

export const BoardAdminPage = () => {
    // useEffectでtask一覧取ってくる
    // 一覧コンポーネントを作成（カードのデザインと一覧はtaskから取ってくる）
    const [taskList, setTaskList] = useState<TaskDTO[]>([]);
    const [editingTaskId, setEditingTaskId] = useState<number>();
    const [newTask, setNewTask] = useState<string>("");

    const fetchTasks = async () => {
        const result = await TasksApi().getTasks();
        return result;
    }

    useEffect(() => {
            fetchTasks().then(result => {
                setTaskList(result.data);
            });
        }
        , [])

    const handleCheck = (id: number) => {
        // todo チェックされたコンポーネントをEditableTaskCardに置き換える
        // チェックされている状態をuseStateでもつ必要がある
        // makeTaskCardsはチェックされているものはEditableで返すという分岐処理を追加する
        setEditingTaskId(id);
        makeTaskCards(taskList);
    }

    const handleDelete = async (id: number) => {
        await TasksApi().deleteTask(id);
        setEditingTaskId(undefined);
        fetchTasks().then(result => setTaskList(result.data));
    }

    const handleUpdate = async (id: number, title: string) => {
        await TasksApi().putTask(id, {title: title});
        setEditingTaskId(undefined);
        fetchTasks().then(result => setTaskList(result.data));
    }

    const makeTaskCards = (taskList: TaskDTO[]) => {
        return taskList.map(task => {
            if (task.id !== editingTaskId) {
                return <TaskCard key={task.id} taskTitle={task.title} checked={false} onCheck={checked => {
                    handleCheck(task.id);
                }}/>
            } else {
                return <EditableTaskCard key={task.id} taskTitle={task.title} onCancel={() => {
                    setEditingTaskId(undefined)
                }} onUpdate={(title: string) => {
                    handleUpdate(task.id, title);
                }} onDelete={() => {
                    handleDelete(task.id).then(() => setEditingTaskId(undefined));
                }}/>
            }
        })
    }

    async function createTask(taskCreateDTO: TaskCreateDTO) {
        try {
            const response = await TasksApi().postTask(taskCreateDTO);
            //const response = await TasksApi.taskOptions();
            return response.data;
        } catch (error) {
            console.log(error);
            //throw new Error(`Error! HTTP Status: ${error.response}`);
        }
    }

    const handleCreateNewTextChange = (e: ChangeEvent<HTMLInputElement>) => {
        setNewTask(e.currentTarget.value);
    }

    const handleRegister = () => {
        createTask({title: newTask}).then(() => {
            fetchTasks().then(result => setTaskList(result.data));
        });
    }

    return <StyledDiv>
        <TaskList taskCards={makeTaskCards(taskList)}/>
        <CreateNewArea>
            <input type="text" onChange={handleCreateNewTextChange}/>
            <button onClick={handleRegister}>登録</button>
        </CreateNewArea>
        <div>
            <Link to='/board'>やることリスト</Link>
        </div>
    </StyledDiv>;
};

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

