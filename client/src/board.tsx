import * as React from 'react';
import {ChangeEvent, useState} from 'react';
import {TaskCreateDTO, TaskDTO, TasksApi} from '../api/generated';
import styled from 'styled-components';
import CheckBox from './molecules/checkbox';
import {addDays, format} from 'date-fns'

// Propsの型定義
type IProps = {
    name: string;
}

const Board = (props: IProps) => {

    const [taskList, setTaskList] = useState<TaskDTO[]>([]);
    const [targetDate, setTargetDate] = useState<Date>(new Date());
    const [newTask, setNewTask] = useState<string>("");
    const [checkedList, setCheckedList] = useState(new Set<number>());
    const showList = async () => {
        console.log('クリックされました');
        const taskDTOs = await getTaskList();
        setTaskList(taskDTOs);
        const targetDate = format(new Date(), 'yyyy-MM-dd'); // todo implements
        const taskChallengeResultDTOs = await fetchTaskChallengeResults(targetDate);

        console.log(taskChallengeResultDTOs);
        console.log('targetDate === ');
        taskChallengeResultDTOs.forEach(value => console.log(value.targetDate));



        const completedTaskIds = taskChallengeResultDTOs
            .filter(value => value.targetDate === targetDate)
            .filter(value => value.completed)
            .reduce((prev, current) => {
                console.log(current);
                prev.add(current.taskId);
                return prev;
            }, new Set<number>());

        console.log(completedTaskIds);

        setCheckedList(completedTaskIds);
    }

    // const showTaskProgressList = (targetDate: Date) => {
    //     setTargetDate(targetDate);
    //     fetchTaskChallengeResults(targetDate).then(taskProgressDTO => {
    //         setTaskList(taskProgressDTO.tasks);
    //         setCheckedList(new Set(taskProgressDTO.completedTaskIds));
    //     });
    // }

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

    const listTask = () => {
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
            <ul>{listTask()}</ul>
            <button onClick={() => showList()}>今日</button>
            <button onClick={() => fetchTaskChallengeResults(format(addDays(new Date(), -1), 'yyyy-MM-dd'))}>昨日</button>
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

const fetchTaskChallengeResults = async (targetDate: string) => {
    const response = await new TasksApi().getTaskChallengeResult(targetDate);
    return response.data;
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
    new TasksApi().putTaskChallengeResult(taskId, format(new Date(), 'yyyy-MM-dd'), isCompleted); // todo implement
}

const StyledDiv = styled.label`
    display:flex
`
export default Board;
