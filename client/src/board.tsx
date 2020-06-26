import * as React from 'react';
import {ChangeEvent, useState} from 'react';
import {TaskCreateDTO, TaskDTO, TasksApi} from '../api/generated';
import styled from 'styled-components';
import {addDays, format} from 'date-fns'
import TaskLi from "./molecules/TaskLi";

// Propsの型定義
type IProps = {
    name: string;
}

const Board = ({name}: IProps) => {

    const [taskList, setTaskList] = useState<TaskDTO[]>([]);
    const [newTask, setNewTask] = useState<string>("");
    const [selectedDate, setSelectedDate] = useState<string>(format(new Date(), 'yyyy-MM-dd'))
    const [checkedList, setCheckedList] = useState(new Set<number>());
    const handleShowList = async (targetDate: string) => {
        setSelectedDate(targetDate);
        const taskDTOs = await getTaskList();
        setTaskList(taskDTOs);
        const taskChallengeResultDTOs = await fetchTaskChallengeResults(targetDate);

        const completedTaskIds = taskChallengeResultDTOs
            .filter(value => value.targetDate === targetDate)
            .filter(value => value.completed)
            .reduce((prev, current) => {
                prev.add(current.taskId);
                return prev;
            }, new Set<number>());

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

    const handleRegister = () => {
        createTask({title: newTask}).then(() => {
            // todo show toast?
        });
    }

    const handleCheck = (checked: boolean, id: number) => {
        console.log(selectedDate);
        updateProgress(id, checked, selectedDate).catch((e) => {
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

    const deleteTask = async (id: number) => {
        try {
            await new TasksApi().taskDelete(id);
        } catch (e) {
            console.log(e);
        }
    };

    const listTask = () => {
        return taskList.map(task => {
            return <TaskLi
                key={task.id}
                taskTitle={task.title}
                checked={checkedList.has(task.id)}
                onCheck={(checked => handleCheck(checked, task.id))}/>
        });
    }

    console.log('render: board');
    return (
        <StyledDiv>
            <h2>{name}</h2>
            <ButtonArea>
                <button onClick={() => handleShowList(format(new Date(), 'yyyy-MM-dd'))}>今日</button>
                <button onClick={() => handleShowList(format(addDays(new Date(), -1), 'yyyy-MM-dd'))}>昨日</button>
            </ButtonArea>
            <TaskArea>
                {taskList.length != 0 && <TaskUl>{listTask()}</TaskUl>}
            </TaskArea>
            <CreateNewArea>
                <input type="text" onChange={e => textChange(e)}/>
                <button onClick={() => handleRegister()}>登録</button>
            </CreateNewArea>
        </StyledDiv>
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

const updateProgress = async (taskId: number, isCompleted: boolean, targetDate: string) => {
    new TasksApi().putTaskChallengeResult(taskId, targetDate, isCompleted);
}

const StyledDiv = styled.div`
    margin-top:12px;
`;
const ButtonArea = styled.div`
    margin-top:12px;
    button:not(:first-child){
        margin-left:12px
    }
`;
const CreateNewArea = styled.div`
    margin-top:12px;
`;
const TaskArea = styled.div`
`
const TaskUl = styled.ul`
    display:flex;
    flex-wrap:wrap;
    justify-content: space-between;
`;
export default Board;
